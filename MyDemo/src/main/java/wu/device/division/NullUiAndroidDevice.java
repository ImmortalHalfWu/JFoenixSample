package wu.device.division;

import immortal.half.wu.LogUtil;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import wu.device.division.interfaces.IUiAndroidDevice;

import java.awt.*;

public class NullUiAndroidDevice implements IUiAndroidDevice {

    public static NullUiAndroidDevice getInstance() {
        return new NullUiAndroidDevice();
    }

    private NullUiAndroidDevice() {
        deviceId = System.currentTimeMillis() + "";
    }

    private static final String TAG = "NullUiAndroidDevice";
    private final String deviceId;

    @Override
    public String getDeviceId() {
        LogUtil.e(TAG, "NullUiAndroidDevice getDeviceId");
        return deviceId;
    }

    @Override
    public String[] getAllAppsPackage() {
        LogUtil.e(TAG, "NullUiAndroidDevice getAllAppsPackage");

        return new String[0];
    }

    @Override
    public boolean isInstallApp(String appPackageName) {
        LogUtil.e(TAG, "NullUiAndroidDevice isInstallApp");
        return false;
    }

    @Override
    public void installApp(String apkPath, String appPackageName) {
        LogUtil.e(TAG, "NullUiAndroidDevice installApp");

    }

    @Override
    public void uninstallApp(String appPackageName) {
        LogUtil.e(TAG, "NullUiAndroidDevice uninstallApp");

    }

    @Override
    public Point getDxSize() {
        LogUtil.e(TAG, "NullUiAndroidDevice getDxSize");
        return new Point(0, 0);
    }

    @Override
    public ITimeOutExecutorService getExecutorService() {
        LogUtil.e(TAG, "NullUiAndroidDevice getExecutorService");
        return null;
    }

    @Override
    public void disconnect() {
        LogUtil.e(TAG, "NullUiAndroidDevice disconnect");
    }
}
