package wu.ui.models;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.FileUtils;
import wu.device.division.NullUiAndroidDevice;
import wu.device.division.NullUiApp;
import wu.device.division.UIIdleFishProductBean;
import wu.device.division.UIUserInfoBean;
import wu.device.division.interfaces.IUiAndroidDevice;
import wu.device.division.interfaces.IUiApp;
import wu.device.interfaces.DeviceAppListener;
import wu.ui.models.beans.CacheIdleFishUserInfoBean;
import wu.ui.models.beans.UIDevAppProductBindBean;
import wu.ui.models.interfaces.MainModelListener;
import wu.ui.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class MainModel implements DeviceAppListener {

    private final List<UIDevAppProductBindBean> devAppProductBindBeans;

    private UserInfoManager userInfoManager;
    private @NotNull MainModelListener listener;

    public MainModel(@NotNull MainModelListener listener) {
        this.listener = listener;
        devAppProductBindBeans = new ArrayList<>();

        ThreadUtil.runInWork(() -> {
            userInfoManager = UserInfoManager.getInstance();
            userInfoManager.getUserInfos().forEach(new Consumer<CacheIdleFishUserInfoBean>() {
                @Override
                public void accept(CacheIdleFishUserInfoBean userInfoBean) {
                    devAppProductBindBeans.add(
                            UIDevAppProductBindBean.create(
                                userInfoBean.getUserName(),
                                userInfoBean.getIdleFishProductModelBeans()
                            )
                    );
                }
            });
            listener.loadUserConfigOver(devAppProductBindBeans);
//            DeviceManager.getInstance().setListener(MainModel.this); todo 注释
        });

    }

    public List<CacheIdleFishUserInfoBean> getUserInfoCache() {
        return userInfoManager.getUserInfos();
    }

    public List<String> getAllUserName() {
        return userInfoManager.getAllUserName();
    }

    public boolean isNewUser(String userName) {
        return userInfoManager.isNewUser(userName);
    }

    public List<CacheIdleFishUserInfoBean.CacheIdleFishProductBean> getProductForUserName(String userName) {
        return userInfoManager.getProductForUserName(userName);
    }

    public void addUser(String userName) {
        userInfoManager.addUser(userName);
    }

    @Override
    public void deviceConnect(IUiAndroidDevice androidDevice,
                              IUiApp<UIIdleFishProductBean, UIUserInfoBean> app,
                              UIUserInfoBean userName,
                              boolean isLogin) {

        UIDevAppProductBindBean returnBean = null;

        // 新的设备未登录
        if (!isLogin || FileUtils.isEmpty(userName.getName())) {
            returnBean = UIDevAppProductBindBean.create(
                    androidDevice, app, userName, new ArrayList<>(0)
            );
            devAppProductBindBeans.add(returnBean);
            // 通知外部
            listener.deviceConnectLogout(devAppProductBindBeans, returnBean);
            return;
        }

        // 新的设备已登录，则查找本地缓存中是否存在相同用户
        for (UIDevAppProductBindBean bean :
                devAppProductBindBeans) {
            if (bean.getUserName().getName().equals(userName.getName())) {
                returnBean = bean;
                break;
            }
        }

        // 本地缓存中有， 则刷新设备及App
        if (returnBean != null) {
            returnBean.setApp(app);
            returnBean.setDevice(androidDevice);
            listener.deviceConnectLogin(devAppProductBindBeans, returnBean);
        }

        // 本地缓存中没有，则新建绑定器
        if (returnBean == null) {
            returnBean = UIDevAppProductBindBean.create(
                    androidDevice, app, userName, new ArrayList<>()
            );
            devAppProductBindBeans.add(returnBean);
            UserInfoManager.getInstance().addUser(userName.getName());
            listener.deviceConnectLogin(devAppProductBindBeans, returnBean);
        }

    }

    @Override
    public void deviceDisConnect(IUiAndroidDevice androidDevice, IUiApp<UIIdleFishProductBean, UIUserInfoBean> app) {

        // 设备断开，则查找当前运行的设备
        for (UIDevAppProductBindBean bean : devAppProductBindBeans) {

            if (!androidDevice.getDeviceId().equals(bean.getDeviceId())) {
                continue;
            }

            // 当前设备 未登录状态, 移除设备
            if (bean.getUserName().isLogout()) {
                devAppProductBindBeans.remove(bean);
                listener.deviceDisconnectLogout(devAppProductBindBeans, bean);
            }
            // 当前设备 登录状态, 未连接
            else {
                bean.setDevice(NullUiAndroidDevice.getInstance());
                bean.setApp(new NullUiApp<>());
                listener.deviceDisconnectLogin(
                        devAppProductBindBeans, bean
                );
            }

        }

    }

}
