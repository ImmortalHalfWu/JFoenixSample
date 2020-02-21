package wu.device;

import immortal.half.wu.IPhoneConnectListener;
import immortal.half.wu.LogUtil;
import immortal.half.wu.PhoneManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;
import wu.device.division.UIIdleFishProductBean;
import wu.device.division.UIUserInfoBean;
import wu.device.division.UiAndroidDeviceImpl;
import wu.device.division.UiFishIdleAppImpl;
import wu.device.division.interfaces.IUiAndroidDevice;
import wu.device.division.interfaces.IUiApp;
import wu.device.division.interfaces.IUiAppCallBack;
import wu.device.interfaces.DeviceAppListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备管理器，  监听设备连接状态 + 初始化闲鱼App + 启动闲鱼 + 检测登录状态 + 接口回调
 */
public class DeviceManager {

    private static final String TAG = "DeviceManager";
    private static DeviceManager instance;
    private final Map<String, IUiAndroidDevice> deviceIdBindMap;
    private final Map<IUiAndroidDevice, IUiApp<UIIdleFishProductBean, UIUserInfoBean>> deviceAppBindMap;
    private DeviceAppListener listener;

    public static DeviceManager getInstance() {
        if (instance == null) {
            synchronized (DeviceManager.class) {
                if (instance == null) {
                    instance = new DeviceManager();
                }
            }
        }
        return instance;
    }

    private DeviceManager() {
        deviceAppBindMap = Collections.synchronizedMap(new HashMap<>());
        deviceIdBindMap = Collections.synchronizedMap(new HashMap<>());
        PhoneManager.getInstance().registerDeviceConnectListener(phoneConnectListener);
    }

    private IPhoneConnectListener phoneConnectListener = new IPhoneConnectListener() {
        @Override
        public void deviceConnect(String deviceId, IAndroidDevice androidDevice) throws Exception {

            // 生成设备bean
            UiAndroidDeviceImpl uiAndroidDevice = new UiAndroidDeviceImpl(androidDevice);
            // 异步
            uiAndroidDevice.getExecutorService().execute(new Runnable() {
                @Override
                public void run() {
                    // 初始化闲鱼App
                    UiFishIdleAppImpl uiFishIdleApp = new UiFishIdleAppImpl(androidDevice);

                    // 检测登录状态
                    uiFishIdleApp.isLogin(new IUiAppCallBack<UIUserInfoBean>() {
                        @Override
                        public void onError(Exception e) {
                            LogUtil.e(TAG, "检测登录状态失败", e);
                            // 检测登录状态失败， 回调外部
                            if (listener != null) {
                                listener.deviceConnect(
                                        uiAndroidDevice,
                                        uiFishIdleApp,
                                        UIUserInfoBean.createLogout(),
                                        false);
                            }
                        }

                        @Override
                        public void onComplete(UIUserInfoBean result) {
                            LogUtil.i(TAG, "检测登录状态完成" + result);
                            // 检测登录状态完成， 回调外部
                            if (listener == null) {
                                return;
                            }
                            listener.deviceConnect(
                                    uiAndroidDevice,
                                    uiFishIdleApp,
                                    result == null ? UIUserInfoBean.createLogout() : result,
                                    result == null
                            );
                        }
                    });

                    deviceAppBindMap.put(uiAndroidDevice, uiFishIdleApp);
                    deviceIdBindMap.put(deviceId, uiAndroidDevice);
                }
            });
        }

        @Override
        public void deviceDisConnect(String deviceId, IAndroidDevice androidDevice) throws Exception {

            IUiAndroidDevice iUiAndroidDevice = deviceIdBindMap.remove(deviceId);
            // 设备断开， 释放资源
            iUiAndroidDevice.getExecutorService().execute(new Runnable() {
                @Override
                public void run() {
                    IUiApp<UIIdleFishProductBean, UIUserInfoBean> app = deviceAppBindMap.remove(iUiAndroidDevice);

                    if (listener != null) {
                        try {
                            // 通知外部设备断开
                            listener.deviceDisConnect(iUiAndroidDevice, app);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    app.disconnect();
                    iUiAndroidDevice.disconnect();

                }
            });

        }
    };

    public void refreshDeviec(IAndroidDevice androidDevice) {

    }

    public List<IAndroidDevice> getAllAndroidDevice() {
        return PhoneManager.getInstance().getAllAndroidDevice();
    }


    /**
     * @param listener 设备状态回调器
     */
    public void setListener(DeviceAppListener listener) {
        this.listener = listener;
    }

}
