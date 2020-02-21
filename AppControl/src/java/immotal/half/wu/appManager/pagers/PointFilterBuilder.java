package immotal.half.wu.appManager.pagers;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class PointFilterBuilder {

    private Map<String, Map<String, String>> pointFilter;
    private Map<String, String> filterMap;

    public PointFilterBuilder() {
        pointFilter = new HashMap<>();
        filterMap = new HashMap<>();
    }

    @NotNull
    public PointFilterBuilder addContentDesc(String contentDesc) {
        filterMap.put("content-desc", contentDesc);
        return this;
    }

    @NotNull
    public PointFilterBuilder addText(String text) {
        filterMap.put("text", text);
        return this;
    }

    @NotNull
    public PointFilterBuilder addResourceId(String resourceId) {
        filterMap.put("resource-id", resourceId);
        return this;
    }

    @NotNull
    public PointFilterBuilder addClass(String className) {
        filterMap.put("class", className);
        return this;
    }

    @NotNull
    public PointFilterBuilder addIndex(String index) {
        filterMap.put("index", index);
        return this;
    }

    @NotNull
    public PointFilterBuilder next(String key) {
        if (filterMap.size() > 0 && !FileUtils.isEmpty(key)) {
            pointFilter.put(key, new HashMap<>(filterMap));
            filterMap.clear();
        }
        return this;
    }

    @NotNull
    public Map<String, Map<String, String>> create() {
        return pointFilter;
    }

}
