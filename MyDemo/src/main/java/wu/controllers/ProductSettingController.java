package wu.controllers;

import com.jfoenix.controls.JFXToggleNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import wu.adapters.BaseListViewAdapter;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductSettingController implements Initializable {
    public ListView<Node> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Node> objects = FXCollections.observableArrayList();

        for (int i = 0; i < 10; i++) {


            ImageView productImage = new ImageView();
            productImage.setFitHeight(127);
            productImage.setFitWidth(127);
            Rectangle clip = new Rectangle(127, 127);
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            productImage.setClip(clip);
            Image imageUrl = new Image("http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg", true);
            productImage.setImage(imageUrl);


            JFXToggleNode jfxToggleNode = new JFXToggleNode();
            jfxToggleNode.getStyleClass().add("toggle-icon1");
            jfxToggleNode.setStyle("-fx-pref-width: -Infinity; -fx-pref-height: -Infinity;" +
                "-fx-background-radius: 10; -jfx-toggle-color: #15a7f4; ");
            jfxToggleNode.setPadding(new Insets(3));
            jfxToggleNode.setGraphic(productImage);

            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPadding(new Insets(3));
            stackPane.getChildren().add(jfxToggleNode);

            objects.add(stackPane);
        }

        listView.getStyleClass().add("product-setting-list");
        listView.setCellFactory(new BaseListViewAdapter<>());
        listView.setItems(objects);
    }
}
