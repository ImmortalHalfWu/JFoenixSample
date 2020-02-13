package wu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MyListViewDemo extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i < 40; i++) {
            data.add(i + "");
        }
        ListView<String> javaListView = new ListView<>();
        javaListView.setItems(data);
//        javaListView.setFixedCellSize(10);

        javaListView.getStyleClass().add("mylistview");
        javaListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>(){

                    @Override
                    //只定义编辑单元格一定要重写的方法
                    protected void updateItem(String item, boolean empty) {
                        // TODO Auto-generated method stub
                        super.updateItem(item, empty);

                        if(!empty){

                            StackPane child = new StackPane();
                            child.setStyle("-fx-background-radius: 25; -fx-border-radius: 25");
                            child.setAlignment(Pos.TOP_CENTER);
                            child.setStyle("-fx-background-color: #123123");
                            double width = 100;
                            child.setPrefWidth(width);
                            double height = 300;
                            child.setPrefHeight(height);
                            JFXDepthManager.setDepth(child, 10);

                            ImageView imageView = new ImageView();
                            imageView.setFitWidth(150);
                            imageView.setFitHeight(100);

                            StackPane body = new StackPane();
                            StackPane.setMargin(body, new Insets(90, 0, 0, 0));
                            body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(123,123,123);");

//                            child.getChildren().add(imageView);
//                            child.getChildren().add(body);

                            this.setGraphic(child);

                        }
                    }

                };
            }
        });

        ObservableList<StackPane> objects = FXCollections.observableArrayList();

        JFXListView<StackPane> jfxList = new JFXListView<>();
        jfxList.setCellFactory(new Callback<ListView<StackPane>, ListCell<StackPane>>() {
            @Override
            public ListCell<StackPane> call(ListView<StackPane> param) {

                return new ListCell<StackPane>() {
                    @Override
                    protected void updateItem(StackPane item, boolean empty) {
                        super.updateItem(item, empty);
                        this.setGraphic(item);
                    }
                };
            }
        });


        for (int i = 0; i < 40; i++) {

            StackPane child = new StackPane();
            child.setAlignment(Pos.TOP_CENTER);
            child.setStyle("-fx-background-color: #123123");
            double width = 100;
            child.setPrefWidth(width);
            double height = 300;
            child.setPrefHeight(height);
            JFXDepthManager.setDepth(child, 1);

            ImageView imageView = new ImageView();
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);

            StackPane body = new StackPane();
            StackPane.setMargin(body, new Insets(90, 0, 0, 0));
            body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(123,123,123);");

            child.getChildren().add(imageView);
            child.getChildren().add(body);
            objects.add(child);
        }
        jfxList.getItems().addAll(objects);
        jfxList.getStyleClass().add("mylistview");


        FlowPane pane = new FlowPane();
        pane.setStyle("-fx-background-color:WHITE");


        JFXButton buttonExpand = new JFXButton("EXPAND");
        buttonExpand.setOnMouseClicked(e -> {
            jfxList.depthProperty().set(1);
            jfxList.setExpanded(true);
        });

        pane.getChildren().add(buttonExpand);

        AnchorPane listsPane = new AnchorPane();
        listsPane.getChildren().add(jfxList);
        AnchorPane.setLeftAnchor(jfxList, 20.0);

        VBox box = new VBox();
        box.getChildren().add(pane);
        box.getChildren().add(listsPane);
        box.setSpacing(40);


        StackPane main = new StackPane();
        main.getChildren().add(jfxList);
        main.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        StackPane.setMargin(jfxList, new Insets(20, 0, 0, 20));

        final Scene scene = new Scene(main, 1024, 600, Color.WHITE);
        stage.setTitle("JFX ListView Demo ");
        scene.getStylesheets().add(MyListViewDemo.class.getResource("/css/jfoenix-components.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    private static class MListCell extends ListCell<Node> {
        public MListCell() {
            this.getStyleClass().add("jfx-list-cell");
            this.setPadding(new Insets(8, 12, 8, 12));
        }

        @Override
        protected void updateItem(Node item, boolean empty) {
            super.updateItem(item, empty);
            this.setGraphic(item);
        }
    }
}
