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

public class PostProductNumKeyProcess extends BasePageProcess<Boolean> {

    private final static String PAGE_POINT_KEY_BOARD_OK = "确认";
    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addText("0").addIndex("10").next("0")
                    .addText("1").next("1")
                    .addText("2").next("2")
                    .addText("3").next("3")
                    .addText("4").next("4")
                    .addText("5").next("5")
                    .addText("6").next("6")
                    .addText("7").next("7")
                    .addText("8").next("8")
                    .addText("9").next("9")
                    .addText(".").next(".")
                    .addText(PAGE_POINT_KEY_BOARD_OK)
                    .next(PAGE_POINT_KEY_BOARD_OK)
                    .create();

    public static BasePageProcess<Boolean> create(String num) {
        return new PostProductNumKeyProcess(num);
    }

    private final String num;
    private PostProductNumKeyProcess(String num) {
        this.num = num;
    }

    @NotNull
    @Override
    public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {

        IADBBuilder adbBuild = adb.createBuild();
        char[] chars = num.toCharArray();
        for (char cNum : chars) {
            Point point = pointMap.get(String.valueOf(cNum));
            if (point != null) {
                adbBuild.addClick(point);
            } else {
                return false;
            }
        }
        adbBuild.send(deviceInfo.getDeviceId());
        return true;
    }

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return filter;
    }
}
