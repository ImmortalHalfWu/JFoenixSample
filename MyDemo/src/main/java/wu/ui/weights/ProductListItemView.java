package wu.ui.weights;

import wu.ui.weights.beans.ProductItemViewBean;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class ProductListItemView extends HBox {

    private static final int MAX_CHILD_COUNT = 4;
    private List<ProductItemViewBean> productItemChildViewBeans;

    public ProductListItemView(List<ProductItemViewBean> dataBeans) {
        super();

        if (dataBeans != null && dataBeans.size() > 0 && dataBeans.size() <= MAX_CHILD_COUNT) {
            productItemChildViewBeans = new ArrayList<>(dataBeans);
        } else {
            throw new IllegalStateException("ProductListItemView 收到的商品数量异常！" +
                (dataBeans == null ? "数据为null" : dataBeans));
        }

        initLayout();

    }

    private void initLayout() {
        setStyle("-fx-min-width: 828; -fx-max-width: 828;" +
            "-fx-background-color: #eeeeee; -fx-min-height: 253; -fx-max-height: 253");

        ObservableList<Node> children = getChildren();

        for (ProductItemViewBean bean : productItemChildViewBeans) {
            children.add(new ProductItemChildView(bean));
        }
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ProductItemViewBean productItemChildViewBean : productItemChildViewBeans) {
            s.append(productItemChildViewBean.getProductName()).append("__");
        }
        return s.toString();
    }
}
