package wu;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.svg.SVGGlyphLoader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
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

        ObservableList<HBox> objects = FXCollections.observableArrayList();
        long s = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            objects.add(FXMLLoader.load(getClass().getResource("/fxml/ListViewItem.fxml")));
        }
        long e = System.currentTimeMillis();
        System.out.println((e - s));
        ListView<HBox> listView = new ListView<>();
        listView.setStyle("-fx-background-color: #eeeeee");
        listView.setCellFactory(new Callback<ListView<HBox>, ListCell<HBox>>() {
            @Override
            public ListCell<HBox> call(ListView<HBox> param) {
                return new ListCell<HBox>() {
                    @Override
                    protected void updateItem(HBox item, boolean empty) {
                        this.setGraphic(item);
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
