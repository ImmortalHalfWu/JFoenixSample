package immortal.half.wu.adbs;

import com.sun.istack.internal.Nullable;
import immortal.half.wu.FileUtils;
import immortal.half.wu.LogUtil;
import com.sun.istack.internal.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class ADBUtils {
    private static final String TAG = "ADB";
    private final static String ADB_PHONE_ROOT_DIR = "/sdcard/";
    private static String ADB = "adb -s ";
    private final static String ADB_UI_AUTO_MATOR = " shell uiautomator dump " + ADB_PHONE_ROOT_DIR;
    private final static String ADB_PULL_FILE = " pull " + ADB_PHONE_ROOT_DIR;
    private final static String ADB_PUSH_FILE = " push ";
    private final static String ADB_START_IDLE_FISH_MAIN_ACTIVITY = " shell am start -n com.taobao.idlefish/com.taobao.fleamarket.home.activity.InitActivity";
    private final static String ADB_IDLE_FISH_IS_RUNNING = " shell dumpsys activity activities | grep ResumedActivity";
    private final static String ADB_IDLE_FISH_IS_INSTANCES = " shell pm list packages | grep com.taobao.idlefish";
    private final static String ADB_ALL_APP_PACKAGE = " shell pm list packages";
    private final static String ADB_INSTANCES = " install -r ";
    private final static String ADB_IDLE_FISH_UNINSTANCES = " uninstall com.taobao.idlefish";
    private final static String ADB_UNINSTALL = " uninstall ";
    private final static String ADB_INPUT_TAP = " shell input tap ";
    private final static String ADB_CONNECT = " connect ";
    private final static String ADB_FIND_DEVICES = "adb devices ";
    private final static String ADB_INPUT_KEY = " shell input keyevent ";
    private final static String ADB_SWIPE = " shell input swipe ";
    private final static String ADB_DELETE_FILE = " shell rm ";
    private final static String ADB_TOP_ACTIVITY = " shell dumpsys activity activities | grep ResumedActivity ";
    private final static String ADB_SCAN_FILE = " shell am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file://";
    private final static String ADB_CHANGE_KEY_BOARD = " shell ime set ";
    private final static String ADB_WM_SIZE = " shell wm size";

    static void setADBPath(String path) {
        ADB = path + ADB;
    }

    synchronized static boolean adbStartIdleFishMainActivity(String deviceAddr) {
        return runInCmd(ADB + deviceAddr + ADB_START_IDLE_FISH_MAIN_ACTIVITY, "Starting:");
    }

    synchronized static boolean adbStartActivity(String deviceAddr, String packageName, String mainActivityPath) {
        return runInCmd(ADB +
                deviceAddr +
                " shell am start -n " + packageName + "/" + mainActivityPath, "Starting:");
    }

    synchronized static String adbAllAppPackage(String deviceAddr) {
        return runInCmd(ADB + deviceAddr + ADB_ALL_APP_PACKAGE);
    }

    synchronized static boolean adbIdleFishIsResume(String deviceAddr) {
        return runInCmd(ADB + deviceAddr + ADB_IDLE_FISH_IS_RUNNING, "com.taobao.idlefish/com.taobao.fleamarket.home.activity.MainActivity");
    }

    synchronized static boolean adbIdleFishIsInstance(String deviceAddr) {
        return runInCmd(ADB + deviceAddr + ADB_IDLE_FISH_IS_INSTANCES, "com.taobao.idlefish");
    }

    synchronized static boolean adbIdleFishUNInstance(String deviceAddr) {
        return runInCmd(ADB + deviceAddr + ADB_IDLE_FISH_UNINSTANCES, "com.taobao.idlefish");
    }

    synchronized static boolean adbIdleFishInstance(String deviceAddr, String apkPath) {
        return runInCmd(ADB + deviceAddr + ADB_INSTANCES + apkPath, "com.taobao.idlefish");
    }

    synchronized static boolean adbInstallApk(String deviceAddr, String apkPath, @NotNull String packageName) {
        return runInCmd(ADB + deviceAddr + ADB_INSTANCES + apkPath, "Install");
    }

    synchronized static boolean adbUNInstallApk(String deviceAddr, @NotNull String packageName) {
        return runInCmd(ADB + deviceAddr + ADB_UNINSTALL + packageName, packageName);
    }

    synchronized static boolean adbGetAndroidUIXML(String deviceAddr, @NotNull String phoneFileName, String saveFileName) {
        return runInCmd(ADB + deviceAddr + ADB_UI_AUTO_MATOR + phoneFileName, "UI hierchary dumped to: ") &&
                runInCmd(ADB + deviceAddr + ADB_PULL_FILE + phoneFileName + " " + saveFileName, phoneFileName);
    }

    synchronized static boolean adbPushFile(String deviceAddr, @NotNull String fromPath, String toFileName) {
        return runInCmd(ADB + deviceAddr + ADB_PUSH_FILE + fromPath + " " + ADB_PHONE_ROOT_DIR + toFileName, fromPath);
    }

    synchronized static boolean adbScanFile(String deviceAddr, String fileName) {
//        "adb shell am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///sdcard/1231.jpg";
        return runInCmd(ADB + deviceAddr + ADB_SCAN_FILE + "/sdcard/" + fileName, "result=0");
    }

    synchronized static boolean adbInputTap(String deviceAddr, int x, int y) {
        return runInCmd(ADB + deviceAddr + ADB_INPUT_TAP + x + " " + y, "");
    }

    synchronized static boolean adbConnectDevice(@NotNull String deviceAddr) {
        runInCmd(ADB + ADB_CONNECT + deviceAddr, "");
        return adbFindDevice(deviceAddr);
    }

    synchronized static boolean adbFindDevice(@NotNull String deviceAddr) {
        return runInCmd(ADB_FIND_DEVICES, deviceAddr);
    }

    synchronized static String adbFindAllDevice() {
        return runInCmd(ADB_FIND_DEVICES);
    }

    synchronized static boolean adbInputText(String deviceAddr, String text) {
        return runInCmd(ADB + deviceAddr + " shell am broadcast -a ADB_INPUT_B64 --es msg `echo '" + text + "' | base64`", "Broadcast completed");
    }

    synchronized static boolean adbSwipe(String deviceAddr, int startX, int startY, int endX, int endY, int time) {
        return runInCmd(ADB + deviceAddr + ADB_SWIPE + startX + " " + startY + " " + endX + " " + endY + " " + time, "");
    }

    synchronized static String findTopActivity(String deviceAddr) {
        return runInCmd(ADB + deviceAddr + ADB_TOP_ACTIVITY);
    }

    synchronized static boolean adbDeleteFile(String deviceAddr, String filePath) {
        return !runInCmd(ADB + deviceAddr + ADB_DELETE_FILE + ADB_PHONE_ROOT_DIR + filePath, "No Such");
    }

    synchronized static boolean adbSendBackKeyEvent(String deviceAddr) {
        return adbSendKeyEvent(deviceAddr, "4");
    }

    private synchronized static boolean adbSendKeyEvent(String deviceAddr, String key) {
        return runInCmd(ADB + deviceAddr + ADB_INPUT_KEY + key, "");
    }

    synchronized static boolean adbChangeKeyBoard(String deviceAddr, String keyBoardName) {
        return runInCmd(ADB + deviceAddr + ADB_CHANGE_KEY_BOARD + keyBoardName, "selected");
    }

    synchronized static String adbWmSize(String deviceId) {
        return runInCmd(ADB + deviceId + ADB_WM_SIZE);
    }

    synchronized static boolean closeApp(String deviceAddr, String packageName) {
        return runInCmd(ADB + deviceAddr + " shell am force-stop " + packageName, "");
    }

    synchronized static boolean runInCmd(String cmd, @NotNull String resultIsSuc) {
        String cmdResult = runInCmd(cmd);
        boolean contains = cmdResult.contains(resultIsSuc);
        if (!contains) {
            LogUtil.e(TAG, "CMD执行失败" + cmd + "，执行结果：" + cmdResult);
        }
        return contains;
    }

    synchronized static String runInCmd(String cmd) {

        try {
//            MLog.logi("执行ADB：" + cmd);
            Process process = Runtime.getRuntime().exec(cmd);
//            process.waitFor();

            String erroResult = readCMDResult(process.getErrorStream());

            if (!FileUtils.isEmpty(erroResult)) {
//                MLog.logi("执行结果：" + erroResult);
                return erroResult;
            }

            //            MLog.logi("执行结果：" + result);
            return readCMDResult(process.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
//            MLog.logi("执行结果：" + e.getMessage());
            return e.getMessage();
        }
    }

    private static String readCMDResult(@Nullable InputStream inputStream) throws IOException {

        if (inputStream == null) {
            return "inputStream == null";
        }

        byte[] readBytes = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();

        BufferedInputStream bis = new BufferedInputStream(inputStream);

        while (bis.read(readBytes) > -1) {
            String s = new String(readBytes, StandardCharsets.UTF_8);
            stringBuilder.append(s, 0, s.indexOf('\u0000') < 0 ? s.length() : s.indexOf('\u0000'));
        }

        bis.close();
        return stringBuilder.toString();

    }

}
