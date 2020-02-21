package wu.ui.events;

import wu.ui.weights.DeviceListItemView;
import wu.ui.weights.beans.DeviceItemViewBean;

/**
 * 设备列表触发Item点击事件
 */
public class DeviceListItemClickEvent {

    private final DeviceListItemView view;
    private final DeviceItemViewBean dataBean;

    public DeviceListItemClickEvent(DeviceListItemView view, DeviceItemViewBean dataBean) {
        this.view = view;
        this.dataBean = dataBean;
    }

    public DeviceListItemView getView() {
        return view;
    }

    public DeviceItemViewBean getDataBean() {
        return dataBean;
    }
}
