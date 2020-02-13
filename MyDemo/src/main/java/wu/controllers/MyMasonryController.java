package wu.controllers;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.effects.JFXDepthManager;
import io.datafx.controller.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@ViewController(value = "/fxml/MyMasonry.fxml", title = "我的demo")
public class MyMasonryController implements Initializable {

//    @FXML
//    JFXListView<StackPane> listView;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXMasonryPane masonryPane;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        scrollPane.setCache(true);
        new Thread() {

            @Override
            public void run() {

                ArrayList<Node> children = new ArrayList<>();
                for (int i = 0; i < 40; i++) {
                    StackPane child = new StackPane();
                    child.setAlignment(Pos.TOP_CENTER);
                    double width = 100;
                    child.setPrefWidth(width);
                    double height = 100;
                    child.setPrefHeight(height);
                    JFXDepthManager.setDepth(child, 1);
                    children.add(child);

                    // create content
                    ImageView imageView = new ImageView();
                    imageView.setFitWidth(150);
                    imageView.setFitHeight(100);



                    StackPane body = new StackPane();
                    StackPane.setMargin(body, new Insets(90, 0, 0, 0));
                    body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(123,123,123);");

                    child.getChildren().add(imageView);
                    child.getChildren().add(body);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImage(new Image("https://dss0.baidu.com/73F1bjeh1BF3odCf/it/u=3451562070,2097201428&fm=85&s=00384D3278B04284E70971CD0300A022"));
// set a clip to apply rounded border to the original image.
                            Rectangle clip = new Rectangle(
                                imageView.getFitWidth(), imageView.getFitHeight()
                            );
                            clip.setArcWidth(15);
                            clip.setArcHeight(15);
                            imageView.setClip(clip);

                            // snapshot the rounded image.
                            SnapshotParameters parameters = new SnapshotParameters();
                            parameters.setFill(Color.TRANSPARENT);
                            WritableImage image = imageView.snapshot(parameters, null);

                            // remove the rounding clip so that our effect can show through.
                            imageView.setClip(null);

                            // apply a shadow effect.
//                            imageView.setEffect(new DropShadow(20, Color.BLACK));

                            // store the rounded image in the imageView.
                            imageView.setImage(image);
                        }
                    });
                }

                masonryPane.getChildren().addAll(children);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scrollPane.requestLayout();
                    }
                });
                JFXScrollPane.smoothScrolling(scrollPane);


            }
        }.start();

    }

    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0:
                color = "#8F3F7E";
                break;
            case 1:
                color = "#B5305F";
                break;
            case 2:
                color = "#CE584A";
                break;
            case 3:
                color = "#DB8D5C";
                break;
            case 4:
                color = "#DA854E";
                break;
            case 5:
                color = "#E9AB44";
                break;
            case 6:
                color = "#FEE435";
                break;
            case 7:
                color = "#99C286";
                break;
            case 8:
                color = "#01A05E";
                break;
            case 9:
                color = "#4A8895";
                break;
            case 10:
                color = "#16669B";
                break;
            case 11:
                color = "#2F65A5";
                break;
            case 12:
                color = "#4E6A9C";
                break;
            default:
                break;
        }
        return color;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
    }
}
