package wu.device.division;

import immotal.half.wu.appManager.beans.IdleFishProductBean;

import java.io.File;
import java.util.List;

public class UIIdleFishProductBean {

    private IdleFishProductBean productBean;

    public UIIdleFishProductBean(IdleFishProductBean productBean) {
        this.productBean = productBean;
    }

    public IdleFishProductBean getProductBean() {
        return productBean;
    }

    public String getTitle() {
        return productBean.getTitle();
    }

    public String getInfo() {
        return productBean.getTitle();
    }

    public List<File> getImageList() {
        return productBean.getImageList();
    }

    public List<String> getImgTag() {
        return productBean.getImgTag();
    }

    public String getPrice() {
        return productBean.getPrice();
    }

    public String getOldPrice() {
        return productBean.getOldPrice();
    }

}
