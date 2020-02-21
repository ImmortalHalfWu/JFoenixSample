package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class ImageControlPageProcess {

    private final static String PAGE_POINT_KEY_IMG_PROCESS_TAG = "添加说明标签可被更多人看见";
    private final static String PAGE_POINT_KEY_IMG_PROCESS_OK = "图像及价签处理完成";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addResourceId("com.taobao.idlefish:id/ftv_lable")
                    .next(PAGE_POINT_KEY_IMG_PROCESS_TAG)
                    .addResourceId("com.taobao.idlefish:id/effect_next")
                    .next(PAGE_POINT_KEY_IMG_PROCESS_OK)
                    .create();

    public static BasePageProcess<Boolean> createToImageTag() {
        return create(PAGE_POINT_KEY_IMG_PROCESS_TAG);

    }

    public static BasePageProcess<Boolean> createToProductInfo() {
        return create(PAGE_POINT_KEY_IMG_PROCESS_OK);
    }

    private static BasePageProcess<Boolean> create(String key) {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point point = pointMap.get(key);
                if (point != null) {
                    adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

}
