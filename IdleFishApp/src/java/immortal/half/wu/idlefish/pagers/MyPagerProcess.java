package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.XMLUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class MyPagerProcess {

    private final static String PAGE_POINT_KEY_HOME_MY_POSTED = "我发布的";
    private final static String PAGE_POINT_KEY_HOME_MY_POSTED_NUM = "我发布的商品数量";
    private final static String PAGE_POINT_KEY_HOME_MY_USER_NAME = "用户名称";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addText(PAGE_POINT_KEY_HOME_MY_POSTED)
                    .next(PAGE_POINT_KEY_HOME_MY_POSTED)
                    .addResourceId("com.taobao.idlefish:id/entry_title")
                    .addIndex("0")
                    .next(PAGE_POINT_KEY_HOME_MY_USER_NAME)
                    .addIndex("2")
                    .addResourceId("com.taobao.idlefish:id/entry_sub_title")
                    .next(PAGE_POINT_KEY_HOME_MY_POSTED_NUM)
                    .create();


    public static BasePageProcess<Boolean> createToPostedPageProcess() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_HOME_MY_POSTED);
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

    public static BasePageProcess<UserInfoBean> createGetUserInfoPageProcess() {

        return new BasePageProcess<UserInfoBean>() {
            @NotNull
            @Override
            public UserInfoBean doPageProcess(@NotNull String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {

                Map<String, String> textByUIXML = XMLUtil.getTextByUIXML(xml, filter);

                return new UserInfoBean(
                        textByUIXML.get(PAGE_POINT_KEY_HOME_MY_USER_NAME),
                        textByUIXML.get(PAGE_POINT_KEY_HOME_MY_POSTED_NUM)
                );
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };

    }

    public static BasePageProcess<Boolean> createGetIsLoginPageProcess() {
        return new BasePageProcess<Boolean>() {

            @Override
            public boolean checkPager(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return true;
            }

            @Override
            public Boolean doPageProcess(@NotNull String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return xml.contains("text=\"去炫耀\"");
            }

            @Override
            public Boolean doPageProcess(@NotNull String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return xml.contains("text=\"去炫耀\"");
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };

    }


}
