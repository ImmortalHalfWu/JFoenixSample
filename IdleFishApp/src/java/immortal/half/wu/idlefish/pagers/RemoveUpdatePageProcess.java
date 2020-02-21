package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class RemoveUpdatePageProcess extends BasePageProcess<Boolean> {

    private final static String PAGE_POINT_KEY_HOME_UPDATE = "暂不升级";

    private final static Map<String, Map<String, String>> filter = new PointFilterBuilder()
            .addText(PAGE_POINT_KEY_HOME_UPDATE)
            .addResourceId("com.taobao.idlefish:id/left")
            .next(PAGE_POINT_KEY_HOME_UPDATE)
            .create();

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return filter;
    }

    @Override
    public boolean checkPager(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return true;
    }

    @NotNull
    @Override
    public Boolean doPageProcess(@NotNull String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
        if (xml.contains(PAGE_POINT_KEY_HOME_UPDATE) && xml.contains("com.taobao.idlefish:id/left")) {
            Point point = pointMap.get(PAGE_POINT_KEY_HOME_UPDATE);
            if (point != null) {
                adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
            }
        }
        return true;
    }
}
