package immotal.half.wu.appManager;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.FileUtils;
import immortal.half.wu.XMLUtil;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.cache.PagerPointCache;
import org.dom4j.DocumentException;

import java.awt.*;
import java.util.Collections;
import java.util.Map;

public class AppManagerUtil {

    public static final String TAG = "AppManager";


    /**
     * @return 手机屏幕xml
     */
    public static String readUiInfo(
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb) {

        String fileName =
                deviceInfo.getDeviceId() +
                        deviceInfo.getDeviceDx();
        String filePath = FileUtils.DIR_PATH_XML + fileName + ".xml";

        String fileContent;
        return adb.androidUIXML(deviceInfo.getDeviceId(), fileName, filePath) ?
                (fileContent = FileUtils.readFile(filePath)) == null ? "" : fileContent.replaceAll("&#10;", "") : "";
    }


    /**
     * @return 获取xml中符合约束的point点，先取缓存
     */
    private static @NotNull Map<String, Point> getUiPoint(
            @NotNull String xmlString,
            @NotNull Map<String, Map<String, String>> filterMap,
            @NotNull PagerInfoBean pagerInfoBean,
            @NotNull DeviceInfoBean deviceInfoBean,
            @NotNull String key) {

        Map<String, Point> point = PagerPointCache.instance().getPoint(pagerInfoBean, deviceInfoBean, key);
        try {
            return point.isEmpty() ? XMLUtil.findAllPointByAttrKeyValue(xmlString, filterMap) : point;
        } catch (DocumentException e) {
            return Collections.emptyMap();
        }
    }

    public static @NotNull Map<String, Point> getUiPointWithSaveCache(
            @NotNull String xmlString,
            @NotNull Map<String, Map<String, String>> filterMap,
            @NotNull PagerInfoBean pagerInfoBean,
            @NotNull DeviceInfoBean deviceInfoBean,
            @NotNull String key) {

        Map<String, Point> uiPoint = getUiPoint(xmlString, filterMap, pagerInfoBean, deviceInfoBean, key);
        PagerPointCache.instance().putPoint(pagerInfoBean, deviceInfoBean, key, uiPoint);
        return uiPoint;
    }

}
