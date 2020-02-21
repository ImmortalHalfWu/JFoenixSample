package immortal.half.wu.beans.params;

public class ParamsProductRefreshBean extends ParamsBaseBean {


    private String pageSize;     //		每页条数	是	Number	默认100，可选范围：10,50,100,200，如果小于10按10处理，大于200按照200处理，其它非范围内数字按100处理
    private String pageId;     //		分页id	是	String	默认为1，支持传统的页码分页方式和scroll_id分页方式，根据用户自身需求传入值。示例1：商品入库，则首次传入1，后续传入接口返回的pageid，接口将持续返回符合条件的完整商品列表，该方式可以避免入口商品重复；示例2：根据pagesize和totalNum计算出总页数，按照需求返回指定页的商品（该方式可能在临近页取到重复商品）
    private String startTime;     //		商品上架开始时间	否	Date	请求格式：yyyy-MM-dd HH:mm:ss
    private String endTime;     //		商品上架结束时间	否	Date	请求格式：yyyy-MM-dd HH:mm:ss
    private String cids;     //		一级类目Id	否	String	大淘客的一级分类id，如果需要传多个，以英文逗号相隔，如：”1,2,3”。当一级类目id和二级类目id同时传入时，会自动忽略二级类目id，1 -女装，2 -母婴，3 -美妆，4 -居家日用，5 -鞋品，6 -美食，7 -文娱车品，8 -数码家电，9 -男装，10 -内衣，11 -箱包，12 -配饰，13 -户外运动，14 -家装家纺
    private String subcid;     //		二级类目Id	否	Number	大淘客的二级类目id，通过超级分类API获取。仅允许传一个二级id，当一级类目id和二级类目id同时传入时，会自动忽略二级类目id
    private String juHuaSuan;     //		是否聚划算	否	Number	1-聚划算商品，0-所有商品，不填默认为0
    private String taoQiangGou;     //		是否淘抢购	否	Number	1-淘抢购商品，0-所有商品，不填默认为0
    private String tmall;     //		是否天猫商品	否	Number	1-天猫商品，0-所有商品，不填默认为0
    private String tchaoshi;     //		是否天猫超市商品	否	Number	1-天猫超市商品，0-所有商品，不填默认为0
    private String goldSeller;     //		是否金牌卖家	否	Number	1-金牌卖家，0-所有商品，不填默认为0
    private String haitao;     //		是否海淘商品	否	Number	1-海淘商品，0-所有商品，不填默认为0
    private String brand;     //		是否品牌商品	否	Number	1-品牌商品，0-所有商品，不填默认为0
    private String brandIds;     //		品牌id	否	String	当brand传入0时，再传入brandIds将获取不到结果。品牌id可以传多个，以英文逗号隔开，如：”345,321,323”
    private String priceLowerLimit;     //		价格（券后价）下限	否	Number
    private String priceUpperLimit;     //		价格（券后价）上限	否	Number
    private String couponPriceLowerLimit;     //		最低优惠券面额	否	Number
    private String commissionRateLowerLimit;     //		最低佣金比率	否	Number
    private String monthSalesLowerLimit;     //		最低月销量	否	Number
    private String sort;     //		排序字段	否	String	默认为0，0-综合排序，1-商品上架时间从新到旧，2-销量从高到低，3-领券量从高到低，4-佣金比例从高到低，5-价格（券后价）从高到低，6-价格（券后价）从低到高

    public ParamsProductRefreshBean(String pageSize, String pageId, String startTime, String endTime, String cids, String subcid, String juHuaSuan, String taoQiangGou, String tmall, String tchaoshi, String goldSeller, String haitao, String brand, String brandIds, String priceLowerLimit, String priceUpperLimit, String couponPriceLowerLimit, String commissionRateLowerLimit, String monthSalesLowerLimit, String sort) {
        super("1.1.0");
        this.pageSize = pageSize;
        this.pageId = pageId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cids = cids;
        this.subcid = subcid;
        this.juHuaSuan = juHuaSuan;
        this.taoQiangGou = taoQiangGou;
        this.tmall = tmall;
        this.tchaoshi = tchaoshi;
        this.goldSeller = goldSeller;
        this.haitao = haitao;
        this.brand = brand;
        this.brandIds = brandIds;
        this.priceLowerLimit = priceLowerLimit;
        this.priceUpperLimit = priceUpperLimit;
        this.couponPriceLowerLimit = couponPriceLowerLimit;
        this.commissionRateLowerLimit = commissionRateLowerLimit;
        this.monthSalesLowerLimit = monthSalesLowerLimit;
        this.sort = sort;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public String getSubcid() {
        return subcid;
    }

    public void setSubcid(String subcid) {
        this.subcid = subcid;
    }

    public String getJuHuaSuan() {
        return juHuaSuan;
    }

    public void setJuHuaSuan(String juHuaSuan) {
        this.juHuaSuan = juHuaSuan;
    }

    public String getTaoQiangGou() {
        return taoQiangGou;
    }

    public void setTaoQiangGou(String taoQiangGou) {
        this.taoQiangGou = taoQiangGou;
    }

    public String getTmall() {
        return tmall;
    }

    public void setTmall(String tmall) {
        this.tmall = tmall;
    }

    public String getTchaoshi() {
        return tchaoshi;
    }

    public void setTchaoshi(String tchaoshi) {
        this.tchaoshi = tchaoshi;
    }

    public String getGoldSeller() {
        return goldSeller;
    }

    public void setGoldSeller(String goldSeller) {
        this.goldSeller = goldSeller;
    }

    public String getHaitao() {
        return haitao;
    }

    public void setHaitao(String haitao) {
        this.haitao = haitao;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds;
    }

    public String getPriceLowerLimit() {
        return priceLowerLimit;
    }

    public void setPriceLowerLimit(String priceLowerLimit) {
        this.priceLowerLimit = priceLowerLimit;
    }

    public String getPriceUpperLimit() {
        return priceUpperLimit;
    }

    public void setPriceUpperLimit(String priceUpperLimit) {
        this.priceUpperLimit = priceUpperLimit;
    }

    public String getCouponPriceLowerLimit() {
        return couponPriceLowerLimit;
    }

    public void setCouponPriceLowerLimit(String couponPriceLowerLimit) {
        this.couponPriceLowerLimit = couponPriceLowerLimit;
    }

    public String getCommissionRateLowerLimit() {
        return commissionRateLowerLimit;
    }

    public void setCommissionRateLowerLimit(String commissionRateLowerLimit) {
        this.commissionRateLowerLimit = commissionRateLowerLimit;
    }

    public String getMonthSalesLowerLimit() {
        return monthSalesLowerLimit;
    }

    public void setMonthSalesLowerLimit(String monthSalesLowerLimit) {
        this.monthSalesLowerLimit = monthSalesLowerLimit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
