package immortal.half.wu.executor.interfaces;

import com.sun.istack.internal.Nullable;

public interface IJobListener<resultType> {

    void onError(Exception e);

    void onComplete(@Nullable resultType result);

}