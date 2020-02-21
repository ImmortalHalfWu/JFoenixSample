package immortal.half.wu;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.devices.PhoneStatueReader;
import immortal.half.wu.devices.interfaces.IAndroidDevice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneManager {

    private static PhoneManager instance;

    @NotNull
    private final Map<String, IAndroidDevice> androidDevices;

    @NotNull
    private final List<IPhoneConnectListener> deviceConnectListeners;

    private PhoneManager() {
        PhoneStatueReader.getInstance().setListeners(mStatueListener);
        androidDevices = new HashMap<>();
        deviceConnectListeners = new ArrayList<>();
    }

    public static PhoneManager getInstance() {
        if (instance == null) {
            synchronized (PhoneManager.class) {
                if (instance == null) {
                    instance = new PhoneManager();
                }
            }
        }
        return instance;
    }

    public void registerDeviceConnectListener(IPhoneConnectListener listener) {
        if (listener != null) {
            deviceConnectListeners.add(listener);
        }
    }

    @NotNull
    public List<IAndroidDevice> getAllAndroidDevice() {
        ArrayList<IAndroidDevice> iAndroidDevices = new ArrayList<>(androidDevices.values());
        LogUtil.i(PhoneManagerUtil.TAG,"获取所有Android设备：" + iAndroidDevices);
        return iAndroidDevices;
    }

    private PhoneStatueReader.StatueListener mStatueListener = new PhoneStatueReader.StatueListener() {
        @Override
        public void deviceConnect(String deviceId) throws Exception {
            if (!androidDevices.containsKey(deviceId)) {

                BaseAndroidDevice baseAndroidDevice = new BaseAndroidDevice(deviceId);
                androidDevices.put(deviceId, baseAndroidDevice);

                PhoneManagerUtil.initPhoneDevice(baseAndroidDevice);

                for (IPhoneConnectListener listener : deviceConnectListeners) {
                    listener.deviceConnect(deviceId, baseAndroidDevice);
                }

            }
        }

        @Override
        public void deviceDisconnect(String deviceId) throws Exception {
            if (androidDevices.containsKey(deviceId)) {

                IAndroidDevice iAndroidDevice = androidDevices.get(deviceId);
                androidDevices.remove(deviceId);

                for (IPhoneConnectListener listener : deviceConnectListeners) {
                    listener.deviceDisConnect(deviceId, iAndroidDevice);
                }

                iAndroidDevice.disconnect();

            }
        }
    };

}
