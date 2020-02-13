package wu.weights;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import wu.weights.beans.ProductItemChildViewBean;

import java.util.ArrayList;
import java.util.List;

public class ProductListItemView extends HBox {

    /*
    <HBox
    fx:id="root"
    -fx-background-color: #eeeeee; -fx-min-height: 253; -fx-max-height: 253"
    xmlns="http://javafx.com/javafx/8.0.172-ea" xmlns:fx="http://javafx.com/fxml/1"
    fx:controller="wu.controllers.ListViewItemController2">
     */
    private static final int MAX_CHILD_COUNT = 4;

    private List<ProductItemChildViewBean> productItemChildViewBeans;


    public ProductListItemView(List<ProductItemChildViewBean> dataBeans) {
        super();

        if (dataBeans != null && dataBeans.size() > 0 && dataBeans.size() <= MAX_CHILD_COUNT) {
            productItemChildViewBeans = new ArrayList<>(dataBeans);
        } else {
            throw new IllegalStateException("ProductListItemView 收到的商品数量异常！" +
                (dataBeans == null ? "数据为null" : dataBeans));
        }

        initLayout();

    }
//    public ProductListItemView(ProductItemChildViewBean... dataBeans) {
//
//        if (dataBeans != null && dataBeans.length > 0 && dataBeans.length <= MAX_CHILD_COUNT) {
//            productItemChildViewBeans = dataBeans;
//        } else {
//            throw new IllegalStateException("ProductListItemView 收到的商品数量异常！" +
//                (dataBeans == null ? "数据为null" : dataBeans));
//        }
//
//        initLayout();
//
//    }

    private void initLayout() {
        setStyle("-fx-min-width: 828; -fx-max-width: 828;" +
            "-fx-background-color: #eeeeee; -fx-min-height: 253; -fx-max-height: 253");

        ObservableList<Node> children = getChildren();

        for (ProductItemChildViewBean bean : productItemChildViewBeans) {
            children.add(new ProductItemChildView(bean));
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ProductItemChildViewBean productItemChildViewBean : productItemChildViewBeans) {
            s.append(productItemChildViewBean.getProductName()).append("__");
        }
        return s.toString();
    }
}
