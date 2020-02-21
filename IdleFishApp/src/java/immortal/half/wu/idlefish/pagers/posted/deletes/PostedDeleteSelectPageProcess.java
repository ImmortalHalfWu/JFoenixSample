package immortal.half.wu.idlefish.pagers.posted.deletes;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class PostedDeleteSelectPageProcess extends BasePageProcess<Boolean> {

    private final static String PAGE_POINT_KEY_POSTED_DELETE = "删除";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addContentDesc(PAGE_POINT_KEY_POSTED_DELETE)
                    .next(PAGE_POINT_KEY_POSTED_DELETE)
                    .create();

    @NotNull
    @Override
    public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
        Point point = pointMap.get(PAGE_POINT_KEY_POSTED_DELETE);
        if (point != null) {
            adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return filter;
    }

}
