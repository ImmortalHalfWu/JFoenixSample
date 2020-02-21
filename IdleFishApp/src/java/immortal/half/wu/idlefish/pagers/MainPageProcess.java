package immortal.half.wu.idlefish.pagers;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;

import java.awt.*;
import java.util.Map;

public class MainPageProcess extends BasePageProcess<Boolean> {


    private final static String PAGE_POINT_KEY_HOME_POST = "发布";
    private final static String PAGE_POINT_KEY_HOME_MY = "我的";
    private final static Map<String, Map<String, String>> filter = new PointFilterBuilder()
                        .addResourceId("com.taobao.idlefish:id/post_click")
                        .next(PAGE_POINT_KEY_HOME_POST)
                        .addContentDesc("我的，")
                        .next(PAGE_POINT_KEY_HOME_MY)
                        .create();


    private final String processType;
    private MainPageProcess(@NotNull String processType) {
        this.processType = processType;
    }

    public static BasePageProcess<Boolean> createToPostPageProcess() {
        return new MainPageProcess(PAGE_POINT_KEY_HOME_POST);
    }

    public static BasePageProcess<Boolean> createToMyPageProcess() {
        return new MainPageProcess(PAGE_POINT_KEY_HOME_MY);
    }

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return filter;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public Boolean doPageProcess(String xml, @org.jetbrains.annotations.NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @org.jetbrains.annotations.NotNull DeviceInfoBean deviceInfo, @org.jetbrains.annotations.NotNull ADBManager adb) {
        Point point = pointMap.get(processType);
        if (point != null) {
            adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
            return true;
        }
        return false;
    }

}
