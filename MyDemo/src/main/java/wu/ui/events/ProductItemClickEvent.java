package wu.ui.events;

import wu.ui.weights.beans.ProductItemViewBean;

public class ProductItemClickEvent {

    private final ProductItemViewBean productItemViewBean;

    public ProductItemClickEvent(ProductItemViewBean productItemViewBean) {
        this.productItemViewBean = productItemViewBean;
    }

    public ProductItemViewBean getProductItemViewBean() {
        return productItemViewBean;
    }
}
