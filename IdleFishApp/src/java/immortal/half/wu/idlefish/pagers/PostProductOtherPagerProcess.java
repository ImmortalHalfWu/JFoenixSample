package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.adbs.IADBBuilder;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class PostProductOtherPagerProcess extends BasePageProcess<Boolean> {

    private final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW = "全新";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY = "不讲价";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK = "确认";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK)
                    .create();

    @NotNull
    @Override
    public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
        IADBBuilder adbBuild = adb.createBuild();
        if (pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW) == null ||
                pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY) == null ||
                pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK) == null) {
            return false;
        }
        adbBuild.addClick(pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW))
                .addClick(pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY))
                .addClick(pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK))
                .send(deviceInfo.getDeviceId());
        return true;
    }

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return filter;
    }
}
