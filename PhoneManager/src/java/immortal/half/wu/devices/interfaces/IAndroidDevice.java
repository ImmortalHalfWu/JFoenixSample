package immortal.half.wu.devices.interfaces;

import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface IAndroidDevice {

    @NotNull String getDeviceId();

//    String[] getAllAppsPackage(@NotNull IAndroidAppFactory androidAppFactory);

    @NotNull String[] getAllAppsPackage();

    boolean isInstallApp(String appPackageName);

    void installApp(String apkPath, String appPackageName);

    void uninstallApp(String appPackageName);

    @NotNull Point getDxSize();

    ITimeOutExecutorService getExecutorService();

    void disconnect();

}
