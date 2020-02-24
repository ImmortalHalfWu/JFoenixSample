package wu.ui;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.svg.SVGGlyphLoader;
import wu.ui.weights.DeviceListItemView;
import wu.ui.weights.beans.DeviceItemViewBean;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class MyListViewDemo2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread thread = new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(MyListViewDemo2.class.getResourceAsStream("/fonts/icomoon.svg"),
                    "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        });
        thread.start();
        thread.join();

        ObservableList<Node> objects = FXCollections.observableArrayList();
        long s = System.currentTimeMillis();
        objects.add(new DeviceListItemView(DeviceItemViewBean.createConnectLoginBean("", "朴素不朴素", "")));
        objects.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        objects.add(new DeviceListItemView(DeviceItemViewBean.createConnectUnLogoutBean("", "")));

        for (int i = 0; i < 30; i++) {
//            objects.add(FXMLLoader.load(getClass().getResource("/fxml/DeviceListItem.fxml")));
            objects.add(new DeviceListItemView(DeviceItemViewBean.createConnectLoginBean("", "朴素不朴素", "")));
        }

        long e = System.currentTimeMillis();
        System.out.println((e - s));
        ListView<Node> listView = new ListView<>();
        listView.getStyleClass().add("device-list");
        listView.setCellFactory(new Callback<ListView<Node>, ListCell<Node>>() {
            @Override
            public ListCell<Node> call(ListView<Node> param) {
                return new ListCell<Node>() {
                    @Override
                    protected void updateItem(Node item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            this.setGraphic(item);
                        }
                    }
                };
            }
        });
        listView.setItems(objects);

        Scene scene = new Scene(listView, 1024, 760);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
            JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
            MyListViewDemo2.class.getResource("/css/jfoenix-main-demo.css").toExternalForm(),
            MyListViewDemo2.class.getResource("/css/jfoenix-components.css").toExternalForm()
        );
        primaryStage.setTitle("StackPane布局示例(xntutor.com)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
