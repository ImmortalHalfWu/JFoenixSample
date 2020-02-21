package immortal.half.wu.beans.params;

public class ParamsProductInfoBean extends ParamsBaseBean {

    private String id;      // 商品id	是	Number	大淘客商品id，请求时id或goodsId必填其中一个，若均填写，将优先查找当前单品id
    private String goodsId; // 淘宝商品id	否	String	id或goodsId必填其中一个，若均填写，将优先查找当前单品id

    public ParamsProductInfoBean(String id, String goodsId) {
        super("1.1.0");
        this.id = id;
        this.goodsId = goodsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

}
