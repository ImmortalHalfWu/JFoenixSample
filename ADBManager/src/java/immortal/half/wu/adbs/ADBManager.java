package immortal.half.wu.adbs;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.ADBInit;
import immortal.half.wu.FileUtils;

import java.awt.*;
import java.util.ArrayList;

public class ADBManager {

    private static ADBManager instance;

    public static ADBManager getInstance() {
        if (instance == null) {
            synchronized (ADBManager.class) {
                if (instance == null) {
                    instance = new ADBManager();
                }
            }
        }
        return instance;
    }

    private ADBManager() {
        init();
    }

    private void init() {

        FileUtils.init();
        String path_adb = ADBInit.init().getPATH_ADB();
        ADBUtils.setADBPath(path_adb);

    }


    @NotNull
    public IADBBuilder createBuild() {
        return new ADBBuilder();
    }

    public String findTopActivityName(String deviceAddr) {
        try {
            String[] split = findTopActivityPath(deviceAddr).split("\\.");
            return split[split.length - 1];
        } catch (Exception ignored) {
        }

        return "";
    }

    public String findTopActivityPath(String deviceAddr) {

        try {
            return ADBUtils.findTopActivity(deviceAddr).split("/")[1].split(" ")[0];
        } catch (Exception ignored) {
        }

        return "";
    }

    @NotNull
    public String[] adbFindAllDevice() {

        String s = ADBProcess.getInstance().adbFindAllDevice();
        s = s.replace("List of devices attached\n", "").replace("\n\n", "\n");
        String[] split = s.split("\n");

        ArrayList<String> returnStrings = new ArrayList<>(split.length);

        for (String nn :
                split) {
            if (nn.contains("offline")) {
                continue;
            }
            String address = nn.split("\t")[0];
            returnStrings.add(address);
        }

        return returnStrings.toArray(new String[0]);
    }

    @NotNull
    public String[] adbAllAppPackage(String deviceAddr) {
        return ADBUtils.adbAllAppPackage(deviceAddr).split("\r\n");
    }

    public boolean isInstallApp(String deviceAddr, @NotNull String appPackageName) {
        return ADBUtils.adbAllAppPackage(deviceAddr).contains(appPackageName);
    }

    public boolean installApp(String deviceId, String apkPath, @NotNull String packageName) {
        return ADBUtils.adbInstallApk(deviceId, apkPath, packageName);
    }

    public void uninstallApp(String deviceId, @NotNull String appPackageName) {
        ADBUtils.adbUNInstallApk(deviceId, appPackageName);
    }

    public void choiceTextInputKeyBoard(String deviceId, String keyBoardName) {
        ADBUtils.adbChangeKeyBoard(deviceId, keyBoardName);
    }

    @NotNull
    public Point getDxSize(String deviceId) {
        String size = ADBUtils.adbWmSize(deviceId)
                .replace("Physical size: ", "")
                .replace("\n", "")
                .replace("\r", "")
                .replace("\t", "");
        String[] xes = size.split("x");
        return new Point(Integer.parseInt(xes[0]), Integer.parseInt(xes[1]));
    }

    public boolean androidUIXML(String deviceAddr, @NotNull String phoneFileName, String saveFileName) {
        return ADBUtils.adbGetAndroidUIXML(deviceAddr, phoneFileName, saveFileName);
    }

    public boolean startActivity(String deviceAddr, String packageName, String mainActivityPath) {
        return ADBUtils.adbStartActivity(deviceAddr, packageName, mainActivityPath);
    }

    public boolean closeApp(String deviceAddr, String packageName) {
        return ADBUtils.closeApp(deviceAddr, packageName);
    }

    public boolean adbPushFile(String deviceAddr, @NotNull String fromPath, String toFileName) {
        return ADBUtils.adbPushFile(deviceAddr, fromPath, toFileName);
    }

    public boolean adbScanFile(String deviceAddr, String fileName) {
        return ADBUtils.adbScanFile(deviceAddr, fileName);
    }

}
