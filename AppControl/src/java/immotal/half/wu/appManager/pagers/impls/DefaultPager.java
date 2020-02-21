package immotal.half.wu.appManager.pagers.impls;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.intefaces.IPageProcess;

import java.util.Map;
import java.util.Set;

public class DefaultPager<DoResultType> extends BasePager<DoResultType> {

    private final @NotNull PagerInfoBean pagerInfoBean;
    private final @NotNull IPageProcess<DoResultType> pageProcess;

    public DefaultPager(@NotNull PagerInfoBean pagerInfoBean, @NotNull IPageProcess<DoResultType> pageProcess) {
        this.pagerInfoBean = pagerInfoBean;
        this.pageProcess = pageProcess;
    }

    public DefaultPager(
            @NotNull String activityName,
            @NotNull String activityDoc,
            @NotNull IPageProcess<DoResultType> pageProcess) {
        this.pagerInfoBean = new PagerInfoBean(activityName, activityDoc);
        this.pageProcess = pageProcess;
    }

    @Override
    public boolean check(
            @NotNull String xml,
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb) {

        if (pageProcess.checkPager(xml, pagerInfoBean, deviceInfo, adb)) {
            return true;
        }

        Map<String, Map<String, String>> uiFilter = pageProcess.getUiFilter(xml, pagerInfoBean, deviceInfo, adb);
        Set<String> pointKeys = uiFilter.keySet();

        for (String pointKey : pointKeys) {

            Map<String, String> pointFilter = uiFilter.get(pointKey);
            Set<String> keys = pointFilter.keySet();

            for (String key : keys) {
                if (!xml.contains(key + "=\"" + pointFilter.get(key))) {
                    return false;
                }
            }

        }

        return adb.findTopActivityPath(deviceInfo.getDeviceId()).equals(pagerInfoBean.getActivityNamePath());
    }

    @Nullable
    @Override
    public DoResultType doPageProcess(String xml, DeviceInfoBean deviceInfo, ADBManager adb) throws InterruptedException {
        if (pageProcess != null) {
            return pageProcess.doPageProcess(xml, pagerInfoBean, deviceInfo, adb);
        }
        return null;
    }

    @Override
    public boolean isComplete(String xml, DeviceInfoBean deviceInfo, ADBManager adb) {
        if (pageProcess != null) {
            return pageProcess.isComplete(xml, pagerInfoBean, deviceInfo, adb);
        }
        return true;
    }

    @Nullable
    @Override
    public Class<DoResultType> getResultType() {
        if (pageProcess != null) {
            return pageProcess.getResultType();
        }
        return null;
    }

    @Override
    public String toString() {
        return pagerInfoBean.getActivityDoc();
    }
}
