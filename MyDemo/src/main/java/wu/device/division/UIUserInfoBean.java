package wu.device.division;

import immotal.half.wu.appManager.pagers.beans.UserInfoBean;

public class UIUserInfoBean {

    private final UserInfoBean userInfoBean;
    private final boolean isLogin;

    private UIUserInfoBean(UserInfoBean userInfoBean, boolean isLogin) {
        this.userInfoBean = userInfoBean;
        this.isLogin = isLogin;
    }

    public static UIUserInfoBean createLogout() {
        return new UIUserInfoBean(new UserInfoBean("未登录", "0"), false);
    }

    public static UIUserInfoBean create(String userName, boolean isLogin) {
        return new UIUserInfoBean(new UserInfoBean(userName, "0"), isLogin);
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public String getName() {
        return userInfoBean.getName();
    }

    public boolean isLogout() {
        return !isLogin;
    }

}
