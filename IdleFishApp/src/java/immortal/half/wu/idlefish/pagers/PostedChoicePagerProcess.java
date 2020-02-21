package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class PostedChoicePagerProcess {

    private final static String PAGE_POINT_KEY_POST_CHOICE_POST = "发布闲置";
    private final static String PAGE_POINT_KEY_POST_CHOICE_FREE = "免费送";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addResourceId("com.taobao.idlefish:id/top_entry_second")
                    .next(PAGE_POINT_KEY_POST_CHOICE_FREE)
                    .addText(PAGE_POINT_KEY_POST_CHOICE_POST)
                    .next(PAGE_POINT_KEY_POST_CHOICE_POST)
                    .create();


    public static BasePageProcess<Boolean> createToPost() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_CHOICE_POST);
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
        };
    }

    public static BasePageProcess<Boolean> createToFree() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_CHOICE_FREE);
                if (point != null) {
                    adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
                    return true;
                }
                return false;            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };
    }

}
