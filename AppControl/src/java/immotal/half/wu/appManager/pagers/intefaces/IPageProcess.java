package immotal.half.wu.appManager.pagers.intefaces;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;

import java.util.Map;

public interface IPageProcess<DoResultType> {

    @NotNull
    Map<String, Map<String, String>> getUiFilter(
            @NotNull String xml,
            @NotNull PagerInfoBean pagerInfo,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb);

    @Nullable boolean checkPager(
            @NotNull String xml,
            @NotNull PagerInfoBean pagerInfo,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb);

    @Nullable DoResultType doPageProcess(
            @NotNull String xml,
            @NotNull PagerInfoBean pagerInfo,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb) throws InterruptedException;

    boolean isComplete(
            @NotNull String xml,
            @NotNull PagerInfoBean pagerInfo,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb
    );

    @NotNull
    @Nullable Class<DoResultType> getResultType();

}
