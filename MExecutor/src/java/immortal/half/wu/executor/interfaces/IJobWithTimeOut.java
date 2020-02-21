package immortal.half.wu.executor.interfaces;


import com.sun.istack.internal.Nullable;

public interface IJobWithTimeOut<T> {
    @Nullable
    T run() throws Exception;
}
