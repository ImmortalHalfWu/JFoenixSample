package wu.ui.models.beans;

import java.util.List;
import java.util.Objects;

public class CacheIdleFishUserConfigBean {

    private final List<CacheIdleFishUserInfoBean> idleFishUserInfoBeans;

    public CacheIdleFishUserConfigBean(List<CacheIdleFishUserInfoBean> idleFishUserInfoBeans) {
        this.idleFishUserInfoBeans = idleFishUserInfoBeans;
    }

    public List<CacheIdleFishUserInfoBean> getIdleFishUserInfoBeans() {
        return idleFishUserInfoBeans;
    }




    public static class CacheIdleFishUserInfoBean {

        private final String userName;
        private final List<CacheIdleFishProductBean> idleFishProductModelBeans;

        public CacheIdleFishUserInfoBean(String userName, List<CacheIdleFishProductBean> idleFishProductModelBeans) {
            this.userName = userName;
            this.idleFishProductModelBeans = idleFishProductModelBeans;
        }

        public String getUserName() {
            return userName;
        }

        public List<CacheIdleFishProductBean> getIdleFishProductModelBeans() {
            return idleFishProductModelBeans;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CacheIdleFishUserInfoBean)) return false;
            CacheIdleFishUserInfoBean that = (CacheIdleFishUserInfoBean) o;
            return userName.equals(that.userName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userName);
        }
    }






    public static class CacheIdleFishProductBean {

        private final String productUrl;
        private final String imageUrl;
        private final String productName;
        private final String time;
        private final String stateString;
        private final String oldPrice;
        private final String nowPrice;
        private final String profitPrice;
        private final List<String> tags;

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

        public String getImageUrl() {
            return imageUrl;
        }

        public String getProductName() {
            return productName;
        }

        public String getTime() {
            return time;
        }

        public String getStateString() {
            return stateString;
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
