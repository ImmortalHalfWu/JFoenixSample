package wu.weights.beans;

import java.util.List;

public class ProductSettingViewBean {


    public static ProductSettingViewBean create(
        String url, String name, String oldMoney, String sellMoney, String ems, List<String> tags, List<String> imgUrls
    ) {
        return new ProductSettingViewBean(
            url, name, oldMoney, sellMoney, ems, tags, imgUrls
        );
    }


    private String url;
    private String name;
    private String oldMoney;
    private String sellMoney;
    private String emsMoney;
    private List<String> tags;
    private List<String> imgUrls;

    private ProductSettingViewBean(String url, String name, String oldMoney, String sellMoney, String ems, List<String> tags, List<String> imgUrls) {
        this.url = url;
        this.name = name;
        this.oldMoney = oldMoney;
        this.sellMoney = sellMoney;
        this.emsMoney = ems;
        this.tags = tags;
        this.imgUrls = imgUrls;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getOldMoney() {
        return oldMoney;
    }

    public String getSellMoney() {
        return sellMoney;
    }

    public String getEmsMoney() {
        return emsMoney;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOldMoney(String oldMoney) {
        this.oldMoney = oldMoney;
    }

    public void setSellMoney(String sellMoney) {
        this.sellMoney = sellMoney;
    }

    public void setEmsMoney(String emsMoney) {
        this.emsMoney = emsMoney;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Override
    public String toString() {
        return "ProductSettingViewBean{" +
            "url='" + url + '\'' +
            ", name='" + name + '\'' +
            ", oldMoney='" + oldMoney + '\'' +
            ", sellMoney='" + sellMoney + '\'' +
            ", emsMoney='" + emsMoney + '\'' +
            ", tags=" + tags +
            ", imgUrls=" + imgUrls +
            '}';
    }
}
