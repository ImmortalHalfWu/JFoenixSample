package immortal.half.wu.adbs;


import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ADBBuilder implements IADBBuilder {


    @NotNull
    private final List<ADBMessageBean> adbMessageBeans;

    public ADBBuilder() {
        adbMessageBeans = new LinkedList<>();
    }

    @NotNull
    @Override
    public IADBBuilder addClick(@Nullable Point point) {
        if (point != null) {
            adbMessageBeans.add(ADBMessageBean.instanceClick(point));
//            delayTime(200);
        }
        return this;
    }

    @NotNull
    @Override
    public IADBBuilder addClick(@Nullable Point point, int offsetX, int offsetY) {
        if (point != null) {
            point.x += offsetX;
            point.y += offsetY;
        }
        return addClick(point);
    }

    @NotNull
    @Override
    public IADBBuilder addClick(@NotNull Point[] points) {
        for (Point point :
                points) {
            adbMessageBeans.add(ADBMessageBean.instanceClick(point));

        }
        return this;
    }

    @NotNull
    @Override
    public IADBBuilder addSwipe(@Nullable Point start, @Nullable Point end, int time) {
        if (start != null && end != null) {
            adbMessageBeans.add(ADBMessageBean.instanceSwipe(start, end, time));
        }
        return this;
    }

    @NotNull
    @Override
    public IADBBuilder addText(@Nullable String text) {
        if (text != null) {
            adbMessageBeans.add(ADBMessageBean.instanceText(text));
        }
        return this;
    }

    @NotNull
    @Override
    public IADBBuilder addBackClick() {
        adbMessageBeans.add(ADBMessageBean.instanceBackClick());
        return this;
    }

    @NotNull
    @Override
    public IADBBuilder addCallBack(@Nullable ADBRunnable callBack) {
        if (callBack != null) {
            adbMessageBeans.add(ADBMessageBean.instanceCallBack(callBack));
        }
        return this;
    }

    @NotNull
    @Override
    public IADBBuilder delayTime(long ms) {
        addCallBack(adbProcess -> {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return this;
    }


    @Override
    public void send(String deviceId) {
        IADBProcess iadbProcess = ADBProcess.getInstance();
        for (ADBMessageBean adbMessage :
                adbMessageBeans) {
            switch (adbMessage.mType) {
                case ADBMessageBean.TYPE_BACK_CLICK:
                    iadbProcess.adbSendBackKeyEvent(deviceId);
                    break;
                case ADBMessageBean.TYPE_CLICK:
                    iadbProcess.adbInputTap(deviceId, adbMessage.pointStart.x, adbMessage.pointStart.y);
                    break;
                case ADBMessageBean.TYPE_SWIPE:
                    iadbProcess.adbSwipe(
                            deviceId,
                            adbMessage.pointStart.x, adbMessage.pointStart.y,
                            adbMessage.pointEnd.x, adbMessage.pointEnd.y,
                            adbMessage.swipeTime
                    );
                    break;
                case ADBMessageBean.TYPE_TEXT:
                    iadbProcess.adbInputText(deviceId, adbMessage.text);
                    break;
                case ADBMessageBean.TYPE_CALL_BACK:
                    try {
                        adbMessage.callback.run(ADBManager.getInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }


    private static final class ADBMessageBean {

        final static int TYPE_CLICK = 1;
        final static int TYPE_SWIPE = 2;
        final static int TYPE_TEXT = 3;
        final static int TYPE_BACK_CLICK = 4;
        final static int TYPE_CALL_BACK = 5;

        private int mType;

        private Point pointStart;
        private Point pointEnd;
        private int swipeTime;

        private String text;

        private ADBRunnable callback;


        private ADBMessageBean(ADBRunnable run) {
            this(TYPE_CALL_BACK, null, null, 0);
            this.callback = run;
        }

        private ADBMessageBean(String text) {
            this(TYPE_TEXT, null, null, 0);
            this.text = text;
        }

        private ADBMessageBean(Point point) {
            this(TYPE_CLICK, point, null, 0);
        }

        private ADBMessageBean(int type, Point pointStart, Point pointEnd, int swipeTime) {
            mType = type;
            this.pointStart = pointStart;
            this.pointEnd = pointEnd;
            this.swipeTime = swipeTime;
        }

        static ADBMessageBean instanceClick(Point point) {
            return new ADBMessageBean(point);
        }

        static ADBMessageBean instanceSwipe(Point pointStart, Point pointEnd, int swipeTime) {
            return new ADBMessageBean(TYPE_SWIPE, pointStart, pointEnd, swipeTime);
        }

        static ADBMessageBean instanceText(String text) {
            return new ADBMessageBean(text);
        }

        static ADBMessageBean instanceBackClick() {
            return new ADBMessageBean(TYPE_BACK_CLICK, null, null, 0);
        }

        static ADBMessageBean instanceCallBack(ADBRunnable runnable) {
            return new ADBMessageBean(runnable);
        }

    }

}
