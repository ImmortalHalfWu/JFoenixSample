package immortal.half.wu.executor;

import com.sun.istack.internal.Nullable;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.IJobWithTimeOut;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import com.sun.istack.internal.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

class TimeOutThreadPool implements ITimeOutExecutorService {

    private final ExecutorService postExecutor;// 定义一个线程池
    private final ExecutorService pullExecutor;// 定义一个线程池

    TimeOutThreadPool(Object tag) {
        postExecutor = Executors.newSingleThreadExecutor(new CustomThreadFactory("post:" + tag + ":"));
        pullExecutor = Executors.newSingleThreadExecutor(new CustomThreadFactory("pull:" + tag + ":"));
    }

    @Override
    public void shutdown() {
        postExecutor.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return postExecutor.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return postExecutor.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return postExecutor.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, @NotNull TimeUnit unit) throws InterruptedException {
        return postExecutor.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return postExecutor.submit(task);
    }

    
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return postExecutor.submit(task, result);
    }

    
    @Override
    public Future<?> submit(Runnable task) {
        return postExecutor.submit(task);
    }

    
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return postExecutor.invokeAll(tasks);
    }

    
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return postExecutor.invokeAll(tasks, timeout, unit);
    }

    
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return postExecutor.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return postExecutor.invokeAny(tasks, timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        postExecutor.execute(command);
    }

    @Override
    public <T> void executeTimeOut60s(@NotNull IJobWithTimeOut<T> run, @Nullable IJobListener<T> runResultListener) {
        executeTimeOut(run, runResultListener, 11 * 1000);
    }

    @Override
    public <T> void executeTimeOut120s(@NotNull IJobWithTimeOut<T> run, @Nullable IJobListener<T> runResultListener) {
        executeTimeOut(run, runResultListener, 2 * 60 * 1000);
    }

    @Override
    public <T> void executeTimeOut(@NotNull IJobWithTimeOut<T> run, @Nullable IJobListener<T> runResultListener) {
        executeTimeOut(run, runResultListener, Integer.MAX_VALUE);
    }

    @Override
    public <T> void executeTimeOut(@NotNull IJobWithTimeOut<T> run, @Nullable IJobListener<T> runResultListener, long outTimeMs) {
        execute(new TimeOutAdapter<>(pullExecutor, run, runResultListener, outTimeMs));
    }

}
