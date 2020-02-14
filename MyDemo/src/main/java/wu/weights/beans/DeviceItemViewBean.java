package wu.weights.beans;

public class DeviceItemViewBean extends BaseViewBean {


    public static DeviceItemViewBean createConnectLoginBean(
        Object tag, String userNameText
    ) {
        return new DeviceItemViewBean(
            tag, DEVICE_CONNECT_IMG, "已连接",
            userNameText, DEVICE_CONNECT_COLOR, USER_NAME_LOGIN_COLOR
        );
    }

    public static DeviceItemViewBean createDisconnectLoginBean(
        Object tag, String userNameText
    ) {
        return new DeviceItemViewBean(
            tag, DEVICE_DISCONNECT_IMG, "未连接",
            userNameText, DEVICE_DISCONNECT_COLOR, USER_NAME_UNLOGIN_COLOR
        );
    }

    public static DeviceItemViewBean createConnectUnLoginBean(
        Object tag
    ) {
        return new DeviceItemViewBean(
            tag, DEVICE_CONNECT_IMG, "已连接",
            "未登录", DEVICE_CONNECT_COLOR, USER_NAME_UNLOGIN_COLOR
        );
    }


    private final static String DEVICE_CONNECT_IMG = DeviceItemViewBean.class.getResource("/imgs/fish_connect.png").toString();
    private final static String DEVICE_DISCONNECT_IMG = DeviceItemViewBean.class.getResource("/imgs/fish_disconnect.png").toString();

    private final static String DEVICE_CONNECT_COLOR = "#26A426";
    private final static String DEVICE_DISCONNECT_COLOR = "#888888";
    private final static String USER_NAME_LOGIN_COLOR = "#000000";
    private final static String USER_NAME_UNLOGIN_COLOR = "#888888";

    private final String imageUrl;
    private final String deviceStateText;
    private final String userNameText;
    private final String deviceStateColor;
    private final String userNameColor;

    private DeviceItemViewBean(Object tag, String imageUrl, String deviceStateText, String userNameText, String deviceStateColor, String userNameColor) {
        super(tag);
        this.imageUrl = imageUrl;
        this.deviceStateText = deviceStateText;
        this.userNameText = userNameText;
        this.deviceStateColor = deviceStateColor;
        this.userNameColor = userNameColor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDeviceStateText() {
        return deviceStateText;
    }

    public String getUserNameText() {
        return userNameText;
    }

    public String getDeviceStateColor() {
        return deviceStateColor;
    }

    public String getUserNameColor() {
        return userNameColor;
    }

    @Override
    public String toString() {
        return "DeviceItemViewBean{" +
            "imageUrl='" + imageUrl + '\'' +
            ", deviceStateText='" + deviceStateText + '\'' +
            ", userNameText='" + userNameText + '\'' +
            ", deviceStateColor='" + deviceStateColor + '\'' +
            ", userNameColor='" + userNameColor + '\'' +
            '}';
    }
}
