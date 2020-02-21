package wu.device.division.interfaces;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;

import java.awt.*;

/**
 * see {@link IAndroidDevice}
 */
public interface IUiAndroidDevice {

    @NotNull
    String getDeviceId();

    @NotNull String[] getAllAppsPackage();

    boolean isInstallApp(String appPackageName);

    void installApp(String apkPath, String appPackageName);

    void uninstallApp(String appPackageName);

    @NotNull
    Point getDxSize();

    ITimeOutExecutorService getExecutorService();

    void disconnect();
}
