package immotal.half.wu.appManager.pagers.processs;

import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.intefaces.IPageProcess;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class BasePageProcess<DoResultType> implements IPageProcess<DoResultType> {

    @Override
    public boolean checkPager(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return false;
    }

    @Override
    public boolean isComplete(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return true;
    }

    @Nullable
    @Override
    public DoResultType doPageProcess(@NotNull String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) throws InterruptedException {

        Map<String, Map<String, String>> uiFilter = getUiFilter(xml, pagerInfo, deviceInfo, adb);
        Map<String, Point> uiPointWithSaveCache = AppManagerUtil.getUiPointWithSaveCache(
                xml, uiFilter, pagerInfo, deviceInfo, uiFilter.toString()
        );

        return doPageProcess(
                xml,
                uiPointWithSaveCache,
                pagerInfo,
                deviceInfo,
                adb
        );
    }

    @NotNull
    @Override
    public Class<DoResultType> getResultType() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];

        try {
            return (Class<DoResultType>) ((ParameterizedType) trueType).getRawType();
        } catch (Exception e) {
            return (Class<DoResultType>) trueType;
        }
    }

    @Nullable
    public abstract DoResultType doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) throws InterruptedException;

}
