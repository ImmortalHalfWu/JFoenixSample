package wu.controllers;

import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ListViewItemController implements Initializable {

    @FXML
    public Label desLabel;
    @FXML
    public Label refreshLabel;
    @FXML
    public Label editLabel;
    @FXML
    public Label deleteLabel;
    @FXML
    HBox root;
    @FXML
    ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setPreserveRatio(true);
        root.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteLabel.setVisible(true);
                refreshLabel.setVisible(true);
                editLabel.setVisible(true);
            }
        });
        root.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteLabel.setVisible(false);
                refreshLabel.setVisible(false);
                editLabel.setVisible(false);
            }
        });
        new Thread(() -> {
            try {

                SVGGlyph refreshIco;
                refreshIco = SVGGlyphLoader.getIcoMoonGlyph("icomoon.svg.rotate-left, undo");
                refreshIco.setFill(Color.valueOf("#A9A9A9"));
                refreshIco.setSize(15, 15);

                SVGGlyph editIco;
                editIco = SVGGlyphLoader.getIcoMoonGlyph("icomoon.svg.edit, pencil-square-o");
                editIco.setFill(Color.valueOf("#A9A9A9"));
                editIco.setSize(15, 15);

                SVGGlyph deleteIco;
                deleteIco = SVGGlyphLoader.getIcoMoonGlyph("icomoon.svg.trash-o");
                deleteIco.setFill(Color.valueOf("#A9A9A9"));
                deleteIco.setSize(15, 15);

                Image imageUrl = new Image(
                    "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg"        );
                Platform.runLater(() -> {
                    deleteLabel.setGraphic(deleteIco);
                    editLabel.setGraphic(editIco);
                    refreshLabel.setGraphic(refreshIco);
                    imageView.setImage(imageUrl);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
