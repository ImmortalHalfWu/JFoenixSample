package immortal.half.wu.idlefish.pagers;

import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collections;
import java.util.Map;

public class StartMainPageProcess extends BasePageProcess<Boolean> {

    private final String appPackage;
    private final String mainActivityNamePath;

    public StartMainPageProcess(String appPackage, String mainActivityName) {
        this.mainActivityNamePath = mainActivityName;
        this.appPackage = appPackage;
    }

    @NotNull
    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return Collections.emptyMap();
    }

    @Override
    public boolean checkPager(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return true;
    }

    @NotNull
    @Override
    public Boolean doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) throws InterruptedException {

        String deviceId = deviceInfo.getDeviceId();

        int delayTime;

        if (adb.findTopActivityPath(deviceId).contains(mainActivityNamePath)) {
            LogUtil.i(AppManagerUtil.TAG, "回到" + appPackage + "首页" + mainActivityNamePath);
            delayTime = 0;
        } else {
            LogUtil.i(AppManagerUtil.TAG, "启动" + appPackage + "首页" + mainActivityNamePath);
            delayTime =  1500;
        }

        do {

            adb.startActivity(deviceInfo.getDeviceId(), appPackage, mainActivityNamePath);
            Thread.sleep(delayTime);

        } while (!checkTopActivity(deviceId, adb));

        return true;
    }

    private boolean checkTopActivity(String deviceId, ADBManager adb) {
        String topActivity = adb.findTopActivityPath(deviceId);
        return topActivity == null || topActivity.length() == 0 || topActivity.equals(mainActivityNamePath);
    }

}
