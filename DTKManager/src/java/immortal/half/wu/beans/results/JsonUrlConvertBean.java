package immortal.half.wu.beans.results;

public class JsonUrlConvertBean {

    /**
     * time : 1562825335025
     * code : 0
     * msg : 成功
     * data : {"couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=Ei%2FSRd6Rw3sGQASttHIRqTdaDsHXMTtpeMG8Ji1zoO5jTfHRi%2FIrN%2BlmuMDII2iZ%2FASFi097%2BCOSU6IuEVIxtk4aJXRcltkaWv9OAj9evKDahba4h8MrZ%2Bdth9k8bqqSHKTgBzHkoM7XTQC0vfau6E%2F9Zk7cDx8UPY2GSU4OeGdhKq32VpkwglrmuEwmYOZU&traceId=0b01ce9815628253346013983e&union_lens=lensId:0b0b6466_0bd9_16bdfa61f60_31e5&xId=tY1oHef5wDRDL5jFXbLZwj7uqTnrSVS9r7bv6LSbshPx9SidsmOeRBMH2KT0jktPQ37qUHbdRjOWr4cZdofTS4","couponEndTime":"2019-07-14","couponInfo":"满65元减20元","couponStartTime":"2019-07-11","itemId":"557600945136","couponTotalCount":"10000","couponRemainCount":"10000","itemUrl":"https://s.click.taobao.com/t?e=m%3D2%26s%3DCRLlBslseCNw4vFB6t2Z2ueEDrYVVa64K7Vc7tFgwiHLWlSKdGSYDqMboaSDWhVnxq3IhSJN6GRqfiF9WU5AouE%2FUJG1YTQOOGKJzdHhnZyvsjd%2BxQft6h8X7G7Q37BarTLiz9%2FRq%2BNM4rJFfofDKowe6%2FtGg2%2FRSyiL934V8t5NFJMOTFqC%2BlPFeq0Ze80KfWsUjZoD5RBLpVTy%2BXVZdXxE89EX4PxacSpj5qSCmbA%3D&union_lens=lensId:0b0b6466_0bd9_16bdfa61f60_31e5&xId=tY1oHef5wDRDL5jFXbLZwj7uqTnrSVS9r7bv6LSbshPx9SidsmOeRBMH2KT0jktPQ37qUHbdRjOWr4cZdofTS4","tpwd":"￥sjhh23j￥","maxCommissionRate":"20.00","shortUrl":"https://s.click.taobao.com/xaulr5w"}
     */

    private long time;
    private int code;
    private String msg;
    private DataBean data;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * couponClickUrl : https://uland.taobao.com/coupon/edetail?e=Ei%2FSRd6Rw3sGQASttHIRqTdaDsHXMTtpeMG8Ji1zoO5jTfHRi%2FIrN%2BlmuMDII2iZ%2FASFi097%2BCOSU6IuEVIxtk4aJXRcltkaWv9OAj9evKDahba4h8MrZ%2Bdth9k8bqqSHKTgBzHkoM7XTQC0vfau6E%2F9Zk7cDx8UPY2GSU4OeGdhKq32VpkwglrmuEwmYOZU&traceId=0b01ce9815628253346013983e&union_lens=lensId:0b0b6466_0bd9_16bdfa61f60_31e5&xId=tY1oHef5wDRDL5jFXbLZwj7uqTnrSVS9r7bv6LSbshPx9SidsmOeRBMH2KT0jktPQ37qUHbdRjOWr4cZdofTS4
         * couponEndTime : 2019-07-14
         * couponInfo : 满65元减20元
         * couponStartTime : 2019-07-11
         * itemId : 557600945136
         * couponTotalCount : 10000
         * couponRemainCount : 10000
         * itemUrl : https://s.click.taobao.com/t?e=m%3D2%26s%3DCRLlBslseCNw4vFB6t2Z2ueEDrYVVa64K7Vc7tFgwiHLWlSKdGSYDqMboaSDWhVnxq3IhSJN6GRqfiF9WU5AouE%2FUJG1YTQOOGKJzdHhnZyvsjd%2BxQft6h8X7G7Q37BarTLiz9%2FRq%2BNM4rJFfofDKowe6%2FtGg2%2FRSyiL934V8t5NFJMOTFqC%2BlPFeq0Ze80KfWsUjZoD5RBLpVTy%2BXVZdXxE89EX4PxacSpj5qSCmbA%3D&union_lens=lensId:0b0b6466_0bd9_16bdfa61f60_31e5&xId=tY1oHef5wDRDL5jFXbLZwj7uqTnrSVS9r7bv6LSbshPx9SidsmOeRBMH2KT0jktPQ37qUHbdRjOWr4cZdofTS4
         * tpwd : ￥sjhh23j￥
         * maxCommissionRate : 20.00
         * shortUrl : https://s.click.taobao.com/xaulr5w
         */

        private String couponClickUrl;
        private String couponEndTime;
        private String couponInfo;
        private String couponStartTime;
        private String itemId;
        private String couponTotalCount;
        private String couponRemainCount;
        private String itemUrl;
        private String tpwd;
        private String maxCommissionRate;
        private String shortUrl;

        public String getCouponClickUrl() {
            return couponClickUrl;
        }

        public void setCouponClickUrl(String couponClickUrl) {
            this.couponClickUrl = couponClickUrl;
        }

        public String getCouponEndTime() {
            return couponEndTime;
        }

        public void setCouponEndTime(String couponEndTime) {
            this.couponEndTime = couponEndTime;
        }

        public String getCouponInfo() {
            return couponInfo;
        }

        public void setCouponInfo(String couponInfo) {
            this.couponInfo = couponInfo;
        }

        public String getCouponStartTime() {
            return couponStartTime;
        }

        public void setCouponStartTime(String couponStartTime) {
            this.couponStartTime = couponStartTime;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getCouponTotalCount() {
            return couponTotalCount;
        }

        public void setCouponTotalCount(String couponTotalCount) {
            this.couponTotalCount = couponTotalCount;
        }

        public String getCouponRemainCount() {
            return couponRemainCount;
        }

        public void setCouponRemainCount(String couponRemainCount) {
            this.couponRemainCount = couponRemainCount;
        }

        public String getItemUrl() {
            return itemUrl;
        }

        public void setItemUrl(String itemUrl) {
            this.itemUrl = itemUrl;
        }

        public String getTpwd() {
            return tpwd;
        }

        public void setTpwd(String tpwd) {
            this.tpwd = tpwd;
        }

        public String getMaxCommissionRate() {
            return maxCommissionRate;
        }

        public void setMaxCommissionRate(String maxCommissionRate) {
            this.maxCommissionRate = maxCommissionRate;
        }

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }
    }
}
