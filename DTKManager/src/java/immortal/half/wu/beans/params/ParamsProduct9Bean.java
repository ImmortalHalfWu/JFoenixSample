package immortal.half.wu.beans.params;

public class ParamsProduct9Bean extends ParamsBaseBean {

    private String pageSize; //  		每页条数	是	Number	默认100 ，可选范围：10,50,100，如果小于10按10处理，大于100按照100处理，其它非范围内数字按100处理
    private String pageId; //  		分页id	是	String	常规分页方式，请直接传入对应页码
    private String nineCid; //  		精选类目Id	是	String	9.9精选的类目id，分类id请求详情：-1-精选，1 -居家百货，2 -美食，3 -服饰，4 -配饰，5 -美妆，6 -内衣，7 -母婴，8 -箱包，9 -数码配件，10 -文娱车品

    public ParamsProduct9Bean(String pageSize, String pageId, String nineCid) {
        super("1.0.2");
        this.pageSize = pageSize;
        this.pageId = pageId;
        this.nineCid = nineCid;
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

    public String getNineCid() {
        return nineCid;
    }

    public void setNineCid(String nineCid) {
        this.nineCid = nineCid;
    }
}
