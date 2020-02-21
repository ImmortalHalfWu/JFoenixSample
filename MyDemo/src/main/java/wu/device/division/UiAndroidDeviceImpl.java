package wu.device.division;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.devices.interfaces.IAndroidDevice;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import wu.device.division.interfaces.IUiAndroidDevice;

import java.awt.*;
import java.util.Objects;

public class UiAndroidDeviceImpl implements IUiAndroidDevice {

    private final String deviceId;
    private final IAndroidDevice androidDevice;

    public UiAndroidDeviceImpl(@NotNull IAndroidDevice androidDevice) {
        this.androidDevice = androidDevice;
        deviceId = androidDevice.getDeviceId();
    }

    @Override
    public String getDeviceId() {
        return androidDevice.getDeviceId();
    }

    @Override
    public String[] getAllAppsPackage() {
        return androidDevice.getAllAppsPackage();
    }

    @Override
    public boolean isInstallApp(String appPackageName) {
        return androidDevice.isInstallApp(appPackageName);
    }

    @Override
    public void installApp(String apkPath, String appPackageName) {
        androidDevice.installApp(apkPath, appPackageName);
    }

    @Override
    public void uninstallApp(String appPackageName) {
        androidDevice.uninstallApp(appPackageName);
    }

    @Override
    public Point getDxSize() {
        return androidDevice.getDxSize();
    }

    @Override
    public ITimeOutExecutorService getExecutorService() {
        return androidDevice.getExecutorService();
    }

    @Override
    public void disconnect() {
        androidDevice.disconnect();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UiAndroidDeviceImpl)) return false;
        UiAndroidDeviceImpl that = (UiAndroidDeviceImpl) o;
        return Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId);
    }
}
