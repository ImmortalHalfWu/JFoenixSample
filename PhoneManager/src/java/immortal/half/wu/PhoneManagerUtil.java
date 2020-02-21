package immortal.half.wu;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;

public class PhoneManagerUtil {

    public static final String TAG = "PhoneManagerUtil";

    private static final String ADB_KEY_BOARD_PACKAGE_CHOICE = "com.android.adbkeyboard/.AdbIME";
    private static final String ADB_KEY_BOARD_PACKAGE = "com.android.adbkeyboard";

    static void initPhoneDevice(IAndroidDevice device) {

        String deviceId = device.getDeviceId();

        if (!device.isInstallApp(ADB_KEY_BOARD_PACKAGE)) {
            LogUtil.i(TAG, "为" + deviceId + "安装ADB键盘，并切换");

            device.installApp(PhoneManagerUtil.class.getResource("res/ADBKeyboard.apk").getPath(),
                    ADB_KEY_BOARD_PACKAGE);

            if (!device.isInstallApp(ADB_KEY_BOARD_PACKAGE)) {
                LogUtil.i(TAG, "为" + deviceId + "安装ADB键盘失败！");
                return;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LogUtil.i(TAG, "为" + deviceId + "切换为ADB键盘");
        ADBManager.getInstance().choiceTextInputKeyBoard(deviceId, ADB_KEY_BOARD_PACKAGE_CHOICE);

    }

}
