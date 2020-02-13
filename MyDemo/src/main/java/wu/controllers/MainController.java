package wu.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import javax.annotation.PostConstruct;

@ViewController(value = "/fxml/MainFxml.fxml", title = "我的demo")
public final class MainController {

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private StackPane optionsBurger;
    @FXML
    private JFXListView<javafx.scene.control.Label> listView;
    @FXML
    private JFXButton sendProductBt;
    @FXML
    private JFXButton searchDeviceBt;
    @FXML
    private ListView<HBox> productListView;
    @FXML
    private BorderPane borderPane;


    @PostConstruct
    public void init() throws Exception {

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        listView.depthProperty().set(1);
                        listView.setExpanded(true);
                    }
                });

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

                        Bounds boundsInParent = borderPane.getCenter().getBoundsInParent();
                        Bounds boundsInLocal = borderPane.getCenter().getBoundsInLocal();
                        System.out.println(boundsInLocal.getHeight() + "_" + boundsInLocal.getWidth() + "\n" +
                            boundsInParent.getHeight() + "_" + boundsInParent.getWidth());
                    }
                });
            }
        }.start();
        setBtIco(sendProductBt, "icomoon.svg.paper-plane-o, send-o");
        setBtIco(searchDeviceBt, "icomoon.svg.search2");


        ObservableList<HBox> objects = FXCollections.observableArrayList();
        long s = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            objects.add(FXMLLoader.load(getClass().getResource("/fxml/ListViewItem.fxml")));
        }
        long e = System.currentTimeMillis();
        System.out.println((e - s));
        productListView.setStyle("-fx-background-color: #eeeeee");
        productListView.setCellFactory(new Callback<javafx.scene.control.ListView<HBox>, ListCell<HBox>>() {
            @Override
            public ListCell<HBox> call(javafx.scene.control.ListView<HBox> param) {
                return new ListCell<HBox>() {
                    @Override
                    protected void updateItem(HBox item, boolean empty) {
                        this.setGraphic(item);
                    }
                };
            }
        });
        productListView.setItems(objects);

    }

    private void setBtIco(JFXButton bt, String icoName) throws Exception {
        SVGGlyph icoMoonGlyph = SVGGlyphLoader.getIcoMoonGlyph(icoName);
        icoMoonGlyph.setFill(Color.WHITE);
        icoMoonGlyph.setSize(20, 20);
        bt.setGraphic(icoMoonGlyph);
    }


}
