package immotal.half.wu.appManager.pagers.cache;

import com.sun.istack.internal.Nullable;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PagerPointCache {

    private static PagerPointCache instance;

    @NotNull
    private final Map<String, Map<String, Point>> pointMap;

    private PagerPointCache() {
        pointMap = new HashMap<>();
    }

    public static PagerPointCache instance() {
        if (instance == null) {
            synchronized (PagerPointCache.class) {
                if (instance == null) {
                    instance = new PagerPointCache();
                }
            }
        }
        return instance;
    }

    @NotNull
    public PagerPointCache putPoint(@Nullable PagerInfoBean pagerInfoBean, @Nullable DeviceInfoBean deviceInfoBean, @Nullable String key, @Nullable Map<String, Point> point) {
        if (pagerInfoBean != null && deviceInfoBean != null && key != null && point != null) {
            pointMap.put(
                    key + deviceInfoBean.getDeviceId() + deviceInfoBean.getDeviceDx() + pagerInfoBean.getActivityNamePath(),
                    point
            );
        }
        return this;
    }

    @NotNull
    public @Nullable Map<String, Point> getPoint(@Nullable PagerInfoBean pagerInfoBean, @Nullable DeviceInfoBean deviceInfoBean, @Nullable String key) {
        Map<String, Point> result = null;
        if (pagerInfoBean != null && deviceInfoBean != null && key != null) {
            result = pointMap.get(key + deviceInfoBean.getDeviceId() + deviceInfoBean.getDeviceDx() + pagerInfoBean.getActivityNamePath());
        }
        return result == null ? Collections.emptyMap() : result;
    }

}
