package immortal.half.wu.beans.params;

public class ParamsUrlConveterBean extends ParamsBaseBean {

    private String goodsId; //	淘宝商品id	是	String
    private String couponId; //	优惠券ID	否	String	商品的优惠券ID，一个商品在联盟可能有多个优惠券，可通过填写该参数的方式选择使用的优惠券，请确认优惠券ID正确，否则无法正常跳转
    private String pid; //	推广位ID	否	string	用户可自由填写当前大淘客账号下已授权淘宝账号的任一pid，若未填写，则默认使用创建应用时绑定的pid
    private String channelId; //	渠道id	否	string	渠道id将会和传入的pid进行验证，验证通过将正常转链，请确认填入的渠道id是正确的

    public ParamsUrlConveterBean(String goodsId, String couponId, String pid, String channelId) {
        super("1.1.0");
        this.goodsId = goodsId;
        this.couponId = couponId;
        this.pid = pid;
        this.channelId = channelId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
