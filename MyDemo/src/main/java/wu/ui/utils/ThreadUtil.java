package wu.ui.utils;

import javafx.application.Platform;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {

//    private static ThreadUtil instance;
//
//    private ThreadUtil() {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//    }

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public static void runInWork(Runnable runnable) {
        runInWork(runnable, 0);
    }

    public static void runInWork(Runnable runnable, long delayTime) {
        if (!executorService.isShutdown() && runnable != null) {
            ScheduledFuture<?> schedule = executorService.schedule(runnable, delayTime, TimeUnit.MILLISECONDS);
        }
    }

    public static void runInMain(Runnable runnable) {
        runInMain(runnable, 0);
    }

    public static void runInMain(Runnable runnable, long delayTime) {
        runInWork(() -> Platform.runLater(runnable), delayTime);
    }

}
