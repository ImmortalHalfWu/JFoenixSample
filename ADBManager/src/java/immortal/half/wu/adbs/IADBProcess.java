package immortal.half.wu.adbs;

public interface IADBProcess {

    boolean adbInputTap(String deviceAddr, int x, int y);

    String adbFindAllDevice();

    boolean adbInputText(String deviceAddr, String text);

    boolean adbSwipe(String deviceAddr, int startX, int startY, int endX, int endY, int time);

    boolean adbSendBackKeyEvent(String deviceAddr);

}