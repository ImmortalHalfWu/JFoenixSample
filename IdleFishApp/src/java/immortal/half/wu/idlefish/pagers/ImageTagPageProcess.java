package immortal.half.wu.idlefish.pagers;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.XMLUtil;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;

import java.awt.*;
import java.util.Map;

public class ImageTagPageProcess {

    private final static String PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT = "输入框";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addClass("android.widget.EditText")
                    .next(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT)
                    .create();

    public static BasePageProcess<Boolean> createInputTag(String tagName) {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @org.jetbrains.annotations.NotNull @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @org.jetbrains.annotations.NotNull @NotNull DeviceInfoBean deviceInfo, @org.jetbrains.annotations.NotNull @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT);
                if (point != null) {
                    adb.createBuild().addClick(new Point(point.x, point.y + 20)).addText(tagName).send(deviceInfo.getDeviceId());
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


    public static BasePageProcess<Boolean> createChoiceTag(@org.jetbrains.annotations.NotNull String tagName) {
        return new BasePageProcess<Boolean>() {

            private PointFilterBuilder pointFilterBuilder = new PointFilterBuilder()
                    .addText(tagName + "自定义标签")
                    .next(tagName + tagName.hashCode());

            @Nullable
            @Override
            public Boolean doPageProcess(@org.jetbrains.annotations.NotNull @NotNull String xml, PagerInfoBean pagerInfo, @org.jetbrains.annotations.NotNull DeviceInfoBean deviceInfo, @org.jetbrains.annotations.NotNull ADBManager adb) throws InterruptedException {
                Point firstLinePoint = XMLUtil.findPointByAttrKeyValueEndWith(
                        xml,
                        "text",
                        "自定义标签");

                if (firstLinePoint != null) {
                    adb.createBuild().addClick(firstLinePoint).send(deviceInfo.getDeviceId());
                } else {
                    adb.createBuild().addBackClick().send(deviceInfo.getDeviceId());
                }
                return true;
            }

            @NotNull
            @Override
            public Boolean doPageProcess(@NotNull String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                return true;
            }

//            @Override
//            public boolean checkPager(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
//                return true;
//            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return pointFilterBuilder.create();
            }
        };
    }



}
