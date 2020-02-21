package immortal.half.wu.idlefish.pagers;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;

import java.awt.*;
import java.util.Map;

public class ProductInfoPagerProcess {

    private final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_OK = "发布";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO = "商品信息啥的";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY = "一口价¥";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER = "选项";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_INFO_OK)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_OK)
                    .addClass("android.widget.EditText")
                    .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER)
                    .create();


    public static BasePageProcess<Boolean> createInputInfo(String info) {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @org.jetbrains.annotations.NotNull @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @org.jetbrains.annotations.NotNull @NotNull DeviceInfoBean deviceInfo, @org.jetbrains.annotations.NotNull @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO);
                if (point != null) {
                    adb.createBuild().addClick(point).addText(info).send(deviceInfo.getDeviceId());
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

    public static BasePageProcess<Boolean> createGoProductMoney() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @org.jetbrains.annotations.NotNull @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @org.jetbrains.annotations.NotNull @NotNull DeviceInfoBean deviceInfo, @org.jetbrains.annotations.NotNull @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY);
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

    public static BasePageProcess<Boolean> createGoProductOther() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @org.jetbrains.annotations.NotNull @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @org.jetbrains.annotations.NotNull @NotNull DeviceInfoBean deviceInfo, @org.jetbrains.annotations.NotNull @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER);
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


    public static BasePageProcess<Boolean> createSendProduct() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @org.jetbrains.annotations.NotNull @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_INFO_OK);
                if (point != null) {
                    LogUtil.i(AppManagerUtil.TAG, "点击发布闲置商品成功");
//                    adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
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
