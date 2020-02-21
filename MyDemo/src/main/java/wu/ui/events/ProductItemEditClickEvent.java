package wu.ui.events;

import wu.ui.weights.beans.ProductItemViewBean;

public class ProductItemEditClickEvent {

    private final ProductItemViewBean productItemViewBean;

    public ProductItemEditClickEvent(ProductItemViewBean productItemViewBean) {
        this.productItemViewBean = productItemViewBean;
    }

    public ProductItemViewBean getProductItemViewBean() {
        return productItemViewBean;
    }
}
