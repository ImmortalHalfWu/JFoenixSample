package wu.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import wu.weights.ProductListItemView;
import wu.weights.beans.ProductItemChildViewBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@ViewController(value = "/fxml/MainFxml.fxml", title = "我的demo")
public final class MainController {

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private StackPane optionsBurger;
    @FXML
    private ListView<javafx.scene.control.Label> listView;
    @FXML
    private JFXButton sendProductBt;
    @FXML
    private JFXButton searchDeviceBt;
    @FXML
    private ListView<ProductListItemView> productListView;
    @FXML
    private BorderPane borderPane;


    @PostConstruct
    public void init() throws Exception {

        setBtIco(sendProductBt, "icomoon.svg.paper-plane-o, send-o");
        setBtIco(searchDeviceBt, "icomoon.svg.search2");

        listView.getStyleClass().add("device-list");
        listView.setPrefWidth(185);

        new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < 30; i++) {
                    listView.getItems().add(new javafx.scene.control.Label("JFXListView" + i));
                }
            }
        }
        .start()
        ;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        Bounds boundsInParent = borderPane.getLeft().getBoundsInParent();
                        Bounds boundsInLocal = borderPane.getLeft().getBoundsInLocal();
                        System.out.println(boundsInLocal.getHeight() + "_" + boundsInLocal.getWidth() + "\n" +
                            boundsInParent.getHeight() + "_" + boundsInParent.getWidth());
                    }
                });
            }
        }.start();


        long s = System.currentTimeMillis();
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

        long e = System.currentTimeMillis();
        System.out.println((e - s));

        productListView.setStyle("-fx-background-color: #eeeeee");
        productListView.setCellFactory(new Callback<javafx.scene.control.ListView<ProductListItemView>, ListCell<ProductListItemView>>() {
            @Override
            public ListCell<ProductListItemView> call(javafx.scene.control.ListView<ProductListItemView> param) {
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
        productListView.setItems(itemViews);

    }

    private void setBtIco(JFXButton bt, String icoName) throws Exception {
        SVGGlyph icoMoonGlyph = SVGGlyphLoader.getIcoMoonGlyph(icoName);
        icoMoonGlyph.setFill(Color.WHITE);
        icoMoonGlyph.setSize(20, 20);
        bt.setGraphic(icoMoonGlyph);
    }

}
