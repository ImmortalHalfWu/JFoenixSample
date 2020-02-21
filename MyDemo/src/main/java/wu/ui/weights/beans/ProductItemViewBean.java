package wu.ui.weights.beans;

import wu.ui.models.beans.CacheIdleFishUserConfigBean;

import java.util.List;

public class ProductItemViewBean extends BaseViewBean {

    public static ProductItemViewBean create(
            String productUrl, String imageUrl, String productName, String time,
            String stateString, String stateColor,
            String oldPrice, String nowPrice, String profitPrice, List<String> tags
    ) {
        return new ProductItemViewBean(
            "", productUrl, imageUrl, productName, time, stateString, stateColor,
                oldPrice, nowPrice, profitPrice, tags
        );
    }


    public static ProductItemViewBean create(
            Object tag, CacheIdleFishUserConfigBean.CacheIdleFishProductBean bean
    ) {
        return new ProductItemViewBean(
                tag, bean
        );
    }

    public static ProductItemViewBean createSendSuc(
            Object tag, CacheIdleFishUserConfigBean.CacheIdleFishProductBean bean
    ) {
        return new ProductItemViewBean(
                tag, STATE_COLOR_SUC, bean
        );
    }

    public static ProductItemViewBean createSendFail(
            Object tag, CacheIdleFishUserConfigBean.CacheIdleFishProductBean bean
    ) {
        return new ProductItemViewBean(
                tag, STATE_COLOR_FAIL, bean
        );
    }

    private static final String STATE_COLOR_SUC = "#26A426";
    private static final String STATE_COLOR_FAIL = "FC4426";

    private final static String STATE_SUC = "发布成功";
    private final static String STATE_FAIL = "发布失败";

    private final CacheIdleFishUserConfigBean.CacheIdleFishProductBean idleFishModelBean;
    private final String stateColor;


    private ProductItemViewBean(
            Object tag,
            String productUrl,
            String imageUrl,
            String productName,
            String time, String stateString,
            String stateColor, String oldPrice, String nowPrice, String profitPrice, List<String> tags) {
        this(tag, stateColor, new CacheIdleFishUserConfigBean.CacheIdleFishProductBean(
                productUrl, imageUrl, productName,
                time, stateString,
                oldPrice, nowPrice, profitPrice,
                tags));
    }

    private ProductItemViewBean(
            Object tag,
            String stateColor,
            CacheIdleFishUserConfigBean.CacheIdleFishProductBean bean) {
        super(tag);
        idleFishModelBean = bean;
        this.stateColor = stateColor;
    }

    private ProductItemViewBean(
            Object tag,
            CacheIdleFishUserConfigBean.CacheIdleFishProductBean bean) {
        super(tag);
        idleFishModelBean = bean;
        this.stateColor = bean.getStateString().equals(STATE_SUC) ? STATE_COLOR_SUC : STATE_COLOR_FAIL;
    }

    public String getImageUrl() {
        return idleFishModelBean.getImageUrl();
    }

    public String getStateString() {
        return idleFishModelBean.getStateString();
    }

    public String getStateColor() {
        return stateColor;
    }

    public String getOldPrice() {
        return idleFishModelBean.getOldPrice();
    }

    public String getNowPrice() {
        return idleFishModelBean.getNowPrice();
    }

    public String getProfitPrice() {
        return idleFishModelBean.getProfitPrice();
    }

    public String getProductName() {
        return idleFishModelBean.getProductName();
    }

    public String getTime() {
        return idleFishModelBean.getTime();
    }

    public String getProductUrl() {
        return idleFishModelBean.getProductUrl();
    }

    @Override
    public String toString() {
        return "ProductItemViewBean{" +
                "idleFishModelBean=" + idleFishModelBean +
                ", stateColor='" + stateColor + '\'' +
                '}';
    }
}
