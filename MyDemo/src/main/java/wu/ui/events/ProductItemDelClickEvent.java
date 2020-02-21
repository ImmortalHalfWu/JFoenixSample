package wu.ui.events;

import wu.ui.weights.beans.ProductItemViewBean;

public class ProductItemDelClickEvent {

    private final ProductItemViewBean productItemViewBean;

    public ProductItemDelClickEvent(ProductItemViewBean productItemViewBean) {
        this.productItemViewBean = productItemViewBean;
    }

    public ProductItemViewBean getProductItemViewBean() {
        return productItemViewBean;
    }
}
