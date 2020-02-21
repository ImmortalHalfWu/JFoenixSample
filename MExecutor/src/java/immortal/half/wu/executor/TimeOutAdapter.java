package immortal.half.wu.executor;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.IJobWithTimeOut;

import java.util.concurrent.*;

class TimeOutAdapter<T> implements Runnable {

    private @NotNull ExecutorService runExecutor;// 定义一个线程池
    private @NotNull
    IJobWithTimeOut<T> job;
    private @Nullable
    IJobListener<T> runResultListener;
    private @Nullable T jobResult;
    private final long outTime;

    TimeOutAdapter(
            @NotNull ExecutorService pullExecutor,
            @NotNull IJobWithTimeOut<T> job,
            @Nullable IJobListener<T> runResultListener,
            long outTime) {
        this.runExecutor = pullExecutor;
        this.job = job;
        this.runResultListener = runResultListener;
        this.outTime = outTime;
    }

    private final Callable<Exception> callable = new Callable<Exception>() {
        @Override
        public Exception call() {
            try {
                if (!Thread.interrupted()) {
                    jobResult = job.run();
                }
            } catch (Exception e) {
                return e;
            }
            return null;
        }
    };

    @Override
    public void run() {

        @Nullable Exception resultException;
        Future<Exception> future = null;

        try {
            future = runExecutor.submit(callable);// 将任务提交到线程池中
            resultException = future.get(outTime, TimeUnit.MILLISECONDS);// 设定在X秒的时间内完成
        } catch (TimeoutException e) {// 超时异常
            System.out.println(job.toString() + "运行超时，退出运行");
            future.cancel(true);// 中断执行此任务的线程
            resultException = e;
        } catch (Exception e) {
            System.out.println("TimeOutAdapter运行异常：" + e.getMessage());
            resultException = e;
        }

        if (runResultListener != null) {
            try {
                if (resultException == null) {
                    runResultListener.onComplete(jobResult);
                } else {
                    runResultListener.onError(resultException);
                }
            } catch (Exception e) {
                System.out.println("TimeOutAdapter回调异常：" + e.getMessage());
            }
        }

        runExecutor = null;
        job = null;

    }

}
