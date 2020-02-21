package immotal.half.wu.appManager.pagers.intefaces;


import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;

public interface IPage<DoResultType> {

    boolean check(
            @NotNull String xml,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb);

    @Nullable
    @NotNull
    DoResultType doPageProcess(
            @NotNull String xml,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb) throws InterruptedException;

    boolean isComplete(
            @NotNull String xml,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb
    );

    @Nullable
    Class<DoResultType> getResultType();

}
