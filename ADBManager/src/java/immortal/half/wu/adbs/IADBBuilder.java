package immortal.half.wu.adbs;

import com.sun.istack.internal.NotNull;

import java.awt.*;

public interface IADBBuilder {

    @NotNull
    IADBBuilder addClick(Point point);

    @NotNull IADBBuilder addClick(Point point, int offsetX, int offsetY);

    @NotNull IADBBuilder addClick(Point[] point);

    @NotNull IADBBuilder addSwipe(Point start, Point end, int time);

    @NotNull IADBBuilder addText(String text);

    @NotNull IADBBuilder addBackClick();

    @NotNull IADBBuilder addCallBack(ADBRunnable runnable);

    @NotNull IADBBuilder delayTime(long ms);

    void send(String deviceId);

}
