package wu.weights.beans;

public class ProductItemChildViewBean extends BaseViewBean {

    private final String imageUrl;
    private final String productName;
    private final String time;
    private final String stateString;
    private final String stateColor;
    private final String oldPrice;
    private final String nowPrice;
    private final String profitPrice;

    public static ProductItemChildViewBean create(
        String imageUrl, String productName, String time, String stateString, String stateColor, String oldPrice, String nowPrice, String profitPrice
    ) {
        return new ProductItemChildViewBean(
            imageUrl, productName, time, stateString, stateColor, oldPrice, nowPrice, profitPrice
        );
    }

    private ProductItemChildViewBean(String imageUrl, String productName, String time, String stateString, String stateColor, String oldPrice, String nowPrice, String profitPrice) {
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.time = time;
        this.stateString = stateString;
        this.stateColor = stateColor;
        this.oldPrice = oldPrice;
        this.nowPrice = nowPrice;
        this.profitPrice = profitPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStateString() {
        return stateString;
    }

    public String getStateColor() {
        return stateColor;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public String getProfitPrice() {
        return profitPrice;
    }

    @Override
    public String toString() {
        return "ProductItemChildViewBean{" +
            "imageUrl='" + imageUrl + '\'' +
            ", tag='" + getTag() + '\'' +
            ", stateString='" + stateString + '\'' +
            ", stateColor='" + stateColor + '\'' +
            ", oldPrice='" + oldPrice + '\'' +
            ", nowPrice='" + nowPrice + '\'' +
            ", profitPrice='" + profitPrice + '\'' +
            '}';
    }

    public String getProductName() {
        return productName;
    }

    public String getTime() {
        return time;
    }
}
