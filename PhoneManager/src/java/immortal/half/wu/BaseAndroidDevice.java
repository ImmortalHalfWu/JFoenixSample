package immortal.half.wu;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;
import immortal.half.wu.executor.ExecutorManager;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;

import java.awt.*;

public class BaseAndroidDevice implements IAndroidDevice {

    private final String deviceId;
    private final Point dxSize;
    private final ITimeOutExecutorService executorService;

    BaseAndroidDevice(@NotNull String deviceId) {
        this.deviceId = deviceId;
        executorService = ExecutorManager.createTimeOutExecutorService(deviceId);
        dxSize =  ADBManager.getInstance().getDxSize(deviceId);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public String[] getAllAppsPackage() {
        return ADBManager.getInstance().adbAllAppPackage(deviceId);
    }

    @Override
    public boolean isInstallApp(@org.jetbrains.annotations.NotNull String appPackageName) {
        boolean installApp = ADBManager.getInstance().isInstallApp(deviceId, appPackageName);
        LogUtil.i(PhoneManagerUtil.TAG, "设备" + deviceId + "是否安装" + appPackageName + "  " + installApp);
        return installApp;
    }

    @Override
    public void installApp(String apkPath, @org.jetbrains.annotations.NotNull String appPackageName) {
        LogUtil.i(PhoneManagerUtil.TAG, "设备" + deviceId +
                "安装" + appPackageName + "  " +
                ADBManager.getInstance().installApp(deviceId, apkPath, appPackageName));
    }

    @Override
    public void uninstallApp(@org.jetbrains.annotations.NotNull String appPackageName) {
        ADBManager.getInstance().uninstallApp(deviceId, appPackageName);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public Point getDxSize() {
        return dxSize;
    }

    @Override
    public ITimeOutExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public void disconnect() {
        try {
            if (!executorService.isShutdown()) {
                executorService.shutdownNow().clear();
            }
            LogUtil.i(PhoneManagerUtil.TAG, "设备" + deviceId + "断开，释放线程池成功");
        } catch (Exception e) {
            LogUtil.i(PhoneManagerUtil.TAG, "设备" + deviceId + "断开，释放线程池失败：" + e.getMessage());
            try {
                executorService.shutdownNow();
            } catch (Exception ignored) {}
        }
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public String toString() {
        return "BaseAndroidDevice{" +
                "deviceId='" + deviceId + '\'' +
                ", dxSize=" + getDxSize() +
                '}';
    }
}
