package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.XMLUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.XMLUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class ImagePagerProcess {

    private final static Map<String, Map<String, String>> choiceImgFilter =
            new PointFilterBuilder().addContentDesc("图片未选中").next("aa").create();

    public static BasePageProcess<Boolean> createChoiceImgPageProcess(int choiceCount) {
        return new BasePageProcess<Boolean>() {

            @NotNull
            @Override
            public Boolean doPageProcess(@NotNull String xml, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {

                List<Point> allImgPoint = XMLUtil.findAllPointByAttrKeyValue(
                        xml,
                        "content-desc",
                        "图片未选中");

                if (allImgPoint.size() == 0) {
                    return false;
                }

                IADBBuilder adbBuild = adb.createBuild();
                for (int i = allImgPoint.size() - 1, count = choiceCount; i >= 0 && count > 0; i--, count--) {
                    adbBuild.addClick(allImgPoint.get(i));
                }
                adbBuild.send(deviceInfo.getDeviceId());

                return true;
            }

            @Nullable
            @Override
            public Boolean doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return null;
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return choiceImgFilter;
            }
        };
        
    }


    private final static String PAGE_POINT_KEY_IMG_CHOICE_OK = "完成(";
    private final static Map<String, Map<String, String>> toImagePageFilter =
            new PointFilterBuilder()
                    .addText(PAGE_POINT_KEY_IMG_CHOICE_OK)
                    .next(PAGE_POINT_KEY_IMG_CHOICE_OK)
                    .create();

    public static BasePageProcess<Boolean> createToImgPageProcess() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_IMG_CHOICE_OK);
                if (point != null) {
                    adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
                    return true;
                }
                return false;
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return toImagePageFilter;
            }
        };
    }



}
