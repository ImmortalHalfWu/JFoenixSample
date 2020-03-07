package wu.ui.models.beans;

import java.util.List;
import java.util.Objects;

public class CacheIdleFishUserInfoBean {

    private String userName;
    private List<CacheIdleFishProductBean> idleFishProductModelBeans;

    public CacheIdleFishUserInfoBean(String userName, List<CacheIdleFishProductBean> idleFishProductModelBeans) {
        this.userName = userName;
        this.idleFishProductModelBeans = idleFishProductModelBeans;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CacheIdleFishProductBean> getIdleFishProductModelBeans() {
        return idleFishProductModelBeans;
    }

    public void setIdleFishProductModelBeans(List<CacheIdleFishProductBean> idleFishProductModelBeans) {
        this.idleFishProductModelBeans = idleFishProductModelBeans;
    }


    public static class CacheIdleFishProductBean {

        private String productUrl;
        private String imageUrl;
        private String productName;
        private String time;
        private String stateString;
        private String oldPrice;
        private String nowPrice;
        private String profitPrice;
        private List<String> tags;

        public CacheIdleFishProductBean(String productUrl, String imageUrl, String productName, String time, String stateString, String oldPrice, String nowPrice, String profitPrice, List<String> tags) {
            this.productUrl = productUrl;
            this.imageUrl = imageUrl;
            this.productName = productName;
            this.time = time;
            this.stateString = stateString;
            this.oldPrice = oldPrice;
            this.nowPrice = nowPrice;
            this.profitPrice = profitPrice;
            this.tags = tags;
        }

        public String getProductUrl() {
            return productUrl;
        }

        public void setProductUrl(String productUrl) {
            this.productUrl = productUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStateString() {
            return stateString;
        }

        public void setStateString(String stateString) {
            this.stateString = stateString;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getNowPrice() {
            return nowPrice;
        }

        public void setNowPrice(String nowPrice) {
            this.nowPrice = nowPrice;
        }

        public String getProfitPrice() {
            return profitPrice;
        }

        public void setProfitPrice(String profitPrice) {
            this.profitPrice = profitPrice;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
        @Override
        public String toString() {
            return "CacheIdleFishProductBean{" +
                    "productUrl='" + productUrl + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", productName='" + productName + '\'' +
                    ", time='" + time + '\'' +
                    ", stateString='" + stateString + '\'' +
                    ", oldPrice='" + oldPrice + '\'' +
                    ", nowPrice='" + nowPrice + '\'' +
                    ", profitPrice='" + profitPrice + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CacheIdleFishProductBean)) return false;
            CacheIdleFishProductBean that = (CacheIdleFishProductBean) o;
            return productUrl.equals(that.productUrl) &&
                    oldPrice.equals(that.oldPrice) &&
                    nowPrice.equals(that.nowPrice) &&
                    profitPrice.equals(that.profitPrice);
        }

        @Override
        public int hashCode() {
            return Objects.hash(productUrl, oldPrice, nowPrice, profitPrice);
        }

        public List<String> getTags() {
            return tags;
        }
    }

}
