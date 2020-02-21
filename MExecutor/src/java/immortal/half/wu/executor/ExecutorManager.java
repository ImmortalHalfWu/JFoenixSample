package immortal.half.wu.executor;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.IJobWithTimeOut;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorManager {

    public static ITimeOutExecutorService createTimeOutExecutorService(Object tag) {
        return new TimeOutThreadPool(tag);
    }

    public static ExecutorService createFixExecutorService(int fixThread, Object tag) {
        return Executors.newFixedThreadPool(fixThread, new CustomThreadFactory("FixThread:" + tag + ":"));
    }

    public static void test() {
        TimeOutThreadPool timeOutThreadPool = new TimeOutThreadPool("TEST");

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {

                for (int i1 = 0; i1 < 100; i1++) {

                    timeOutThreadPool.executeTimeOut(
                            new IJobWithTimeOut<Boolean>() {
                                int i = 0;

                                @NotNull
                                @Override
                                public Boolean run() {
                                    for (int j = 0; i < 1; i++) {
                                        try {
                                            Thread.sleep(149);
                                        } catch (Exception e) {
                                            return false;
                                        }
                                        System.out.println("job 运行完");
                                    }
                                    return true;
                                }
                            },
                            new IJobListener<Boolean>() {
                                @Override
                                public void onError(@NotNull Exception e) {
                                    System.out.println("job 运行异常" + e.getClass().getSimpleName());
                                }

                                @Override
                                public void onComplete(Boolean result) {
                                    System.out.println("job 运行结束" + result);
                                }
                            },
                            150
                    );

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

}
