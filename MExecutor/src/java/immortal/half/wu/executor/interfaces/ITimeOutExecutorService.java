package immortal.half.wu.executor.interfaces;


import java.util.concurrent.ExecutorService;

public interface ITimeOutExecutorService extends ExecutorService {

    <T> void executeTimeOut60s(IJobWithTimeOut<T> run, IJobListener<T> runResultListener);

    <T> void executeTimeOut120s(IJobWithTimeOut<T> run, IJobListener<T> runResultListener);

    <T> void executeTimeOut(IJobWithTimeOut<T> run, IJobListener<T> runResultListener);

    <T> void executeTimeOut(IJobWithTimeOut<T> run, IJobListener<T> runResultListener, long outTimeMs);

}
