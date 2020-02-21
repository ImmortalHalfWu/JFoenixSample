package immortal.half.wu.idlefish.pagers.posted.deletes;

import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class PostedDeleteOkPageProcess extends BasePageProcess<Boolean> {

    private final static String PAGE_POINT_KEY_POSTED_DELETE_OK = "确认";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addContentDesc(PAGE_POINT_KEY_POSTED_DELETE_OK)
                    .next(PAGE_POINT_KEY_POSTED_DELETE_OK)
                    .create();


    @NotNull
    @Override
    public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        Point point = pointMap.get(PAGE_POINT_KEY_POSTED_DELETE_OK);
        if (point != null) {
            LogUtil.i(AppManagerUtil.TAG, "确定删除商品：" + point);
//            adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return filter;
    }
}
