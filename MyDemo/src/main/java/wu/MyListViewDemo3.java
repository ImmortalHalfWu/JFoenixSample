package wu;

import com.jfoenix.assets.JFoenixResources;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import wu.weights.ProductListItemView;
import wu.weights.beans.ProductItemChildViewBean;

import java.util.ArrayList;
import java.util.List;


public class MyListViewDemo3 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

//        List<ProductItemChildViewBean> productItemChildViewBeans = new ArrayList<>();
//        for (int i = 0; i < 13; i++) {
//            productItemChildViewBeans.add(
//                ProductItemChildViewBean.create(
//                    "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg",
//                    "商品：" + i,
//                    "2020/02/13",
//                    "发布成功",
//                    "#26A426",
//                    "" + i,
//                    "" + i * 2,
//                    "" + i
//                )
//            );
//        }
//
//        int size = productItemChildViewBeans.size();
//        ObservableList<ProductListItemView> itemViews = FXCollections.observableArrayList();
//        ProductItemChildViewBean[] beansTemp = new ProductItemChildViewBean[4];

//        if (size <= 0) {
//
//        } else if (size <= 4) {
////            System.out.println("size <= 4 " + size);
//            itemViews.add(new ProductListItemView(productItemChildViewBeans.subList(0, size).toArray(new ProductItemChildViewBean[size])));
//        } else {
//            for (int i = 0, max = size - (size % 4); i < max; i += 4) {
////                System.out.println("i += 4___ " + i + "_" +  (i + 4));
//                itemViews.add(new ProductListItemView(productItemChildViewBeans.subList(i, i + 4).toArray(new ProductItemChildViewBean[4])));
//            }
//
//            if (size % 4 != 0) {
////                System.out.println("size % 4 != 0__" + (size - size % 4) + "_" + size);
//
//                itemViews.add(
//                    new ProductListItemView(
//                        productItemChildViewBeans.subList(size - size % 4, size).toArray(new ProductItemChildViewBean[size % 4])));
//            }
//        }


        ObservableList<ProductListItemView> itemViews = FXCollections.observableArrayList();
        List<ProductItemChildViewBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (datas.size() == 4 || (i == 14  && datas.size() > 0)) {
                itemViews.add(new ProductListItemView(datas));
                datas.clear();
            }
            datas.add(ProductItemChildViewBean.create(
                "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg",
                "商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：" + i,
                "2020/02/13",
                "发布成功",
                "#26A426",
                "" + i + 1000,
                "" + i * 2  + 1000,
                "" + i  + 1000
            ));
        }

        ListView<ProductListItemView> objectListView = new ListView<>();
//        objectListView.setStyle("-fx-background-color: #123123");
        objectListView.setCellFactory(new Callback<ListView<ProductListItemView>, ListCell<ProductListItemView>>() {
            @Override
            public ListCell<ProductListItemView> call(ListView<ProductListItemView> param) {
                return new ListCell<ProductListItemView>() {
                    @Override
                    protected void updateItem(ProductListItemView item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            this.setGraphic(item);
                        }
                    }
                };
            }
        });
        objectListView.setItems(itemViews);

//        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/ListViewDemo3.fxml")), 828, 659);
        Scene scene = new Scene(objectListView, 828, 659);


        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
            JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
            MyListViewDemo3.class.getResource("/css/jfoenix-main-demo.css").toExternalForm(),
            MyListViewDemo3.class.getResource("/css/jfoenix-components.css").toExternalForm()
        );
        primaryStage.setTitle("StackPane布局示例(xntutor.com)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
