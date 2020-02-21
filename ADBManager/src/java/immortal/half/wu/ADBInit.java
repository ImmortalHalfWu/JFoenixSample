package immortal.half.wu;

import java.io.File;

public class ADBInit {

    private static ADBInit instance;

    private final String PATH_ADB_ZIP_MAC;
    private final String PATH_ADB_ZIP_WIN;

    private String PATH_ADB = "";

    private ADBInit() {
        PATH_ADB_ZIP_MAC = ADBInit.class.getResource("res/ADB_MAC.zip").getPath();
        PATH_ADB_ZIP_WIN = ADBInit.class.getResource("res/ADB_WIN.zip").getPath();
        upZipADB();
    }

    private void upZipADB() {
        try {
            String adbZipPath = "";
            if (OSInfo.isWindows()) {
                adbZipPath = PATH_ADB_ZIP_WIN;
            }

            if (OSInfo.isMacOS() || OSInfo.isMacOSX()) {
                adbZipPath = PATH_ADB_ZIP_MAC;
            }

            FileUtils.copy(new File(adbZipPath), new File(FileUtils.DIR_PATH_OTHER + "ADB.zip"));
            ZIPUtil.unZip(new File(FileUtils.DIR_PATH_OTHER + "ADB.zip"), FileUtils.DIR_PATH_ADB);

            if (OSInfo.isWindows()) {
                PATH_ADB = FileUtils.DIR_PATH_ADB;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static ADBInit init() {
        if (instance == null) {
            synchronized (ADBInit.class) {
                if (instance == null) {
                    instance = new ADBInit();
                }
            }
        }
        return instance;
    }

    public String getPATH_ADB() {
        return PATH_ADB;
    }
}
