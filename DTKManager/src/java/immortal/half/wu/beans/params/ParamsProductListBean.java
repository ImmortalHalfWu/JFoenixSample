package immortal.half.wu.beans.params;

public class ParamsProductListBean extends ParamsBaseBean {

    private final String pageSize;   //	每页条数	否	Number	默认为100，可选范围：10,50,100,200，如果小于10按10处理，大于200按照200处理，其它非范围内数字按100处理
    private final String pageId;   //		分页id	是	final String	默认为1，支持传统的页码分页方式和scroll_id分页方式，根据用户自身需求传入值。示例1：商品入库，则首次传入1，后续传入接口第一次返回的pageid，接口将持续返回符合条件的完整商品列表，该方式可以避免入库商品重复；示例2：根据pagesize和totalNum计算出总页数，按照需求返回指定页的商品（该方式可能在临近页取到重复商品）
    private final String sort;   //		排序方式	否	final String	默认为0，0-综合排序，1-商品上架时间从高到低，2-销量从高到低，3-领券量从高到低，4-佣金比例从高到低，5-价格（券后价）从高到低，6-价格（券后价）从低到高
    private final String cids;   //		一级类目id	否	final String	大淘客的一级分类id，如果需要传多个，以英文逗号相隔，如：”1,2,3”。当一级类目id和二级类目id同时传入时，会自动忽略二级类目id
    private final String subcid;   //		二级类目id	否	Number	大淘客的二级类目id，通过超级分类API获取。仅允许传一个二级id，当一级类目id和二级类目id同时传入时，会自动忽略二级类目id
    private final String juHuaSuan;   //		是否聚划算	否	Number	1-聚划算商品，0-所有商品，不填默认为0
    private final String taoQiangGou;   //		是否淘抢购	否	Number	1-淘抢购商品，0-所有商品，不填默认为0
    private final String tmall;   //		是否天猫商品	否	Number	1-天猫商品，0-所有商品，不填默认为0
    private final String tchaoshi;   //		是否天猫超市商品	否	Number	1-天猫超市商品，0-所有商品，不填默认为0
    private final String goldSeller;   //		是否金牌卖家	否	Number	1-金牌卖家，0-所有商品，不填默认为0
    private final String haitao;   //		是否海淘商品	否	Number	1-海淘商品，0-所有商品，不填默认为0
    private final String pre;   //		是否预告商品	否	Number	1-预告商品，0-非预告商品
    private final String brand;   //		是否品牌商品	否	Number	1-品牌商品，0-所有商品，不填默认为0
    private final String brandIds;   //		品牌id	否	final String	当brand传入0时，再传入brandIds将获取不到结果。品牌id可以传多个，以英文逗号隔开，如：”345,321,323”
    private final String priceLowerLimit;   //		价格（券后价）下限	否	Number
    private final String priceUpperLimit;   //		价格（券后价）上限	否	Number
    private final String couponPriceLowerLimit;   //		最低优惠券面额	否	Number
    private final String commissionRateLowerLimit;   //		最低佣金比率	否	Number
    private final String monthSalesLowerLimit;   //		最低月销量	否	Number

    public ParamsProductListBean(String pageSize, String pageId, String sort, String cids, String subcid, String juHuaSuan, String taoQiangGou, String tmall, String tchaoshi, String goldSeller, String haitao, String pre, String brand, String brandIds, String priceLowerLimit, String priceUpperLimit, String couponPriceLowerLimit, String commissionRateLowerLimit, String monthSalesLowerLimit) {
        super("1.1.0");
        this.pageSize = pageSize;
        this.pageId = pageId;
        this.sort = sort;
        this.cids = cids;
        this.subcid = subcid;
        this.juHuaSuan = juHuaSuan;
        this.taoQiangGou = taoQiangGou;
        this.tmall = tmall;
        this.tchaoshi = tchaoshi;
        this.goldSeller = goldSeller;
        this.haitao = haitao;
        this.pre = pre;
        this.brand = brand;
        this.brandIds = brandIds;
        this.priceLowerLimit = priceLowerLimit;
        this.priceUpperLimit = priceUpperLimit;
        this.couponPriceLowerLimit = couponPriceLowerLimit;
        this.commissionRateLowerLimit = commissionRateLowerLimit;
        this.monthSalesLowerLimit = monthSalesLowerLimit;
    }

    public String getPageSize() {
        return pageSize;
    }


    public String getPageId() {
        return pageId;
    }


    public String getSort() {
        return sort;
    }


    public String getCids() {
        return cids;
    }


    public String getSubcid() {
        return subcid;
    }


    public String getJuHuaSuan() {
        return juHuaSuan;
    }


    public String getTaoQiangGou() {
        return taoQiangGou;
    }


    public String getTmall() {
        return tmall;
    }


    public String getTchaoshi() {
        return tchaoshi;
    }


    public String getGoldSeller() {
        return goldSeller;
    }


    public String getHaitao() {
        return haitao;
    }


    public String getPre() {
        return pre;
    }


    public String getBrand() {
        return brand;
    }


    public String getBrandIds() {
        return brandIds;
    }


    public String getPriceLowerLimit() {
        return priceLowerLimit;
    }


    public String getPriceUpperLimit() {
        return priceUpperLimit;
    }


    public String getCouponPriceLowerLimit() {
        return couponPriceLowerLimit;
    }


    public String getCommissionRateLowerLimit() {
        return commissionRateLowerLimit;
    }


    public String getMonthSalesLowerLimit() {
        return monthSalesLowerLimit;
    }

}
