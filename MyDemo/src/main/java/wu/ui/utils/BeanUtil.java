package wu.ui.utils;

import com.sun.istack.internal.Nullable;
import immortal.half.wu.FileUtils;
import wu.ui.weights.DeviceListItemView;
import wu.ui.weights.beans.DeviceItemViewBean;

public class BeanUtil {

    /**
     * 设备是否处于 连接并登录
     */
    public static boolean deviceItemIsConnectLogin(DeviceListItemView bean) {
        if (bean == null || bean.getDataBean() == null) {
            return false;
        }
        return bean.getDataBean().getState() == DeviceItemViewBean.STATE_CONNECT_LOGIN;
    }


    /**
     * 设备是否处于 连接但未登录
     */
    public static boolean deviceItemIsConnectLogout(DeviceListItemView bean) {
        if (bean == null || bean.getDataBean() == null) {
            return false;
        }
        return bean.getDataBean().getState() == DeviceItemViewBean.STATE_CONNECT_LOGOUT;
    }


    /**
     * 设备是否处于 未连接但有用户缓存
     */
    public static boolean deviceItemIsDisconnectLogin(DeviceListItemView bean) {
        if (bean == null || bean.getDataBean() == null) {
            return false;
        }
        return bean.getDataBean().getState() == DeviceItemViewBean.STATE_DISCONNECT_LOGIN;
    }

    /**
     * 转换deviceItem状态 连接已登录
     */
    public static DeviceItemViewBean deviceItemViewBeanConvertToConnectLogin(DeviceListItemView itemView, @Nullable String userName) {
        if (itemView == null || itemView.getDataBean() == null) {
            return DeviceItemViewBean.createConnectLoginBean("", "null", "");
        }
        return DeviceItemViewBean.createConnectLoginBean(
                itemView.getDataBean().getTag(),
                FileUtils.isEmpty(userName) ? itemView.getDataBean().getUserNameText() : userName,
                itemView.getDataBean().getDeviceId());
    }

    /**
     * 转换deviceItem状态 连接未登录
     */
    public static DeviceItemViewBean deviceItemViewBeanConvertToConnectLogout(DeviceListItemView itemView) {
        if (itemView == null || itemView.getDataBean() == null) {
            return DeviceItemViewBean.createConnectUnLogoutBean("", "");
        }
        return DeviceItemViewBean.createConnectUnLogoutBean(
                itemView.getDataBean().getTag(),
                itemView.getDataBean().getDeviceId());
    }

    /**
     * 转换deviceItem状态 未连接已登录
     */
    public static DeviceItemViewBean deviceItemViewBeanConvertToDisconnectLogin(DeviceListItemView itemView, @Nullable String userName) {
        if (itemView == null || itemView.getDataBean() == null) {
            return DeviceItemViewBean.createDisconnectLoginBean("", "null");
        }
        return DeviceItemViewBean.createDisconnectLoginBean(
                itemView.getDataBean().getTag(),
                FileUtils.isEmpty(userName) ? itemView.getDataBean().getUserNameText() : userName
        );
    }


}
