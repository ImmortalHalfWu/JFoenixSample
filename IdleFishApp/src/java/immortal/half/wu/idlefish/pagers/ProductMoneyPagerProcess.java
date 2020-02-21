package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class ProductMoneyPagerProcess {

    private final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY = "入手价¥";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL = "一口价¥";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL = "包邮";
    private final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK = "确认";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL)
                    .create();

    private final static Map<String, Map<String, String>> clickSuccessFilter =
            new PointFilterBuilder()
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                    .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL)
                    .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL)
                    .create();

    public static BasePageProcess<Boolean> createClickBuyMoney() {
        return create(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY, filter);
    }

    public static BasePageProcess<Boolean> createClickSellMoney() {
        return create(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL, filter);
    }

    public static BasePageProcess<Boolean> createClickSuccess() {
        return create(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK, clickSuccessFilter);
    }

    public static BasePageProcess<Boolean> createClickMill() {
        return create(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL, clickSuccessFilter);
    }


    private static BasePageProcess<Boolean> create(String key, @NotNull Map<String, Map<String, String>> mFilter) {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point point = pointMap.get(key);
                if (point != null) {
                    adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
                    return true;
                }
                return false;
            }

            @NotNull
            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return mFilter;
            }
        };
    }

}
