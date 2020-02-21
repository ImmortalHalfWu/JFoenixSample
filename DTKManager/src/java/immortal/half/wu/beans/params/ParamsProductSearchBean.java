package immortal.half.wu.beans.params;

public class ParamsProductSearchBean extends ParamsBaseBean {

    private String type; //  		搜索类型	是	Number	0-综合结果，1-大淘客商品，2-联盟商品
    private String pageId; //  		页码	是	Number	请求的页码，默认参数1
    private String pageSize; //  		每页条数	是	Number	每页显示的条数，默认20
    private String keyWords; //  		关键词搜索	是	String
    private String tmall; //  		是否天猫商品	否	Number	1-天猫商品，0-所有商品，不填默认为0
    private String haitao; //  		是否海淘商品	否	Number	1-海淘商品，0-所有商品，不填默认为0
    private String sort; //  		排序字段	否	String	排序字段信息 销量（total_sales） 价格（price），排序_des（降序），排序_asc（升序）


    public ParamsProductSearchBean(String type, String pageId, String pageSize, String keyWords, String tmall, String haitao, String sort) {
        super("1.2.0");
        this.type = type;
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.keyWords = keyWords;
        this.tmall = tmall;
        this.haitao = haitao;
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getTmall() {
        return tmall;
    }

    public void setTmall(String tmall) {
        this.tmall = tmall;
    }

    public String getHaitao() {
        return haitao;
    }

    public void setHaitao(String haitao) {
        this.haitao = haitao;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
