package wu.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListViewDemoController implements Initializable {

    @FXML
    public ListView<HBox> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        listView.getStyleClass().add("mylistview");
        ObservableList<HBox> objects = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            try {
                objects.add(FXMLLoader.load(getClass().getResource("/fxml/ListViewItem2.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    }

}
