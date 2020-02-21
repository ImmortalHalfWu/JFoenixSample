package wu.ui.models.interfaces;

import wu.ui.models.beans.UIDevAppProductBindBean;

import java.util.List;

public interface MainModelListener {

    /**
     初始化设备列表， 全部未登录
     */
    void loadUserConfigOver(List<UIDevAppProductBindBean> devAppProductBindBeans);

    /**
     *         有设备连接， 未登录闲鱼用户
     */
    void deviceConnectLogout(
        List<UIDevAppProductBindBean> devAppProductBindBeans,
        UIDevAppProductBindBean bean
    );

    /**
     *         有设备连接， 并登录了闲鱼用户
     */
    void deviceConnectLogin(
        List<UIDevAppProductBindBean> devAppProductBindBeans,
        UIDevAppProductBindBean bean
    );

    /**
     *         从连接登录状态  转为  未连接状态
     */
    void deviceDisconnectLogin(
        List<UIDevAppProductBindBean> devAppProductBindBeans,
        UIDevAppProductBindBean bean
    );

    /**
     *         设备未登录状态下断开连接， 移除设备UI即可
     */
    void deviceDisconnectLogout(
        List<UIDevAppProductBindBean> devAppProductBindBeans,
        UIDevAppProductBindBean bean
    );

}
