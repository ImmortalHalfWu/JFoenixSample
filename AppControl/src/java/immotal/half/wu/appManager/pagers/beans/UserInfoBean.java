package immotal.half.wu.appManager.pagers.beans;

import com.sun.istack.internal.NotNull;

public class UserInfoBean {

    private final String name;
    private final String postedNum;

    public UserInfoBean(String name, String postedNum) {
        this.name = name;
        this.postedNum = postedNum;
    }

    public String getName() {
        return name;
    }

    public String getPostedNum() {
        return postedNum;
    }

    @NotNull
    @Override
    public String toString() {
        return "UserInfoBean{" +
                "name='" + name + '\'' +
                ", postedNum='" + postedNum + '\'' +
                '}';
    }
}
