package wu.ui;

import com.jfoenix.assets.JFoenixResources;
import wu.ui.weights.ProductListItemView;
import wu.ui.weights.beans.ProductItemViewBean;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;


public class MyListViewDemo3 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        ObservableList<ProductListItemView> itemViews = FXCollections.observableArrayList();
        List<ProductItemViewBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (datas.size() == 4 || (i == 14  && datas.size() > 0)) {
                itemViews.add(new ProductListItemView(datas));
                datas.clear();
            }
            datas.add(ProductItemViewBean.create(
                    "TestUrl",
                "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg",
                "商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：" + i,
                "2020/02/13",
                "发布成功",
                "#26A426",
                "" + i + 1000,
                "" + i * 2  + 1000,
                "" + i  + 1000, new ArrayList<>()
            ));
        }

        ListView<ProductListItemView> objectListView = new ListView<>();
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
