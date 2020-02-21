package immortal.half.wu.adbs;


public class ADBProcess implements IADBProcess {

    private static ADBProcess instance;

    static ADBProcess getInstance() {
        if (instance == null) {
            synchronized (ADBProcess.class) {
                if (instance == null) {
                    instance = new ADBProcess();
                }
            }
        }
        return instance;
    }

    private ADBProcess() {
    }

    @Override
    public boolean adbInputTap(String deviceAddr, int x, int y) {
        return ADBUtils.adbInputTap(deviceAddr, x, y);
    }

    @Override
    public String adbFindAllDevice() {
        return ADBUtils.adbFindAllDevice();
    }

    @Override
    public boolean adbInputText(String deviceAddr, String text) {
        return ADBUtils.adbInputText(deviceAddr, text);
    }

    @Override
    public boolean adbSwipe(String deviceAddr, int startX, int startY, int endX, int endY, int time) {
        return ADBUtils.adbSwipe(deviceAddr, startX, startY, endX, endY, time);
    }

    @Override
    public boolean adbSendBackKeyEvent(String deviceAddr) {
        return ADBUtils.adbSendBackKeyEvent(deviceAddr);
    }

}
