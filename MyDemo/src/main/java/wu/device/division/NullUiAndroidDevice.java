package wu.device.division;

import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import wu.device.division.interfaces.IUiAndroidDevice;

import java.awt.*;

public class NullUiAndroidDevice implements IUiAndroidDevice {

    public static NullUiAndroidDevice getInstance() {
        return new NullUiAndroidDevice(System.currentTimeMillis() + "");
    }

    public static NullUiAndroidDevice getInstance(String deviceId) {
        return new NullUiAndroidDevice(deviceId);
    }

    private NullUiAndroidDevice(String deviceId) {
        this.deviceId = deviceId;
    }

    private static final String TAG = "NullUiAndroidDevice";
    private final String deviceId;

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String[] getAllAppsPackage() {
        return new String[0];
    }

    @Override
    public boolean isInstallApp(String appPackageName) {
        return false;
    }

    @Override
    public void installApp(String apkPath, String appPackageName) {

    }

    @Override
    public void uninstallApp(String appPackageName) {

    }

    @Override
    public Point getDxSize() {
        return new Point(0, 0);
    }

    @Override
    public ITimeOutExecutorService getExecutorService() {
        return null;
    }

    @Override
    public void disconnect() {
    }
}
