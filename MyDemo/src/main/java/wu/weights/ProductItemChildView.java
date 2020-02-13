package wu.weights;

import com.jfoenix.controls.JFXRippler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import wu.utils.ThreadUtil;
import wu.weights.beans.ProductItemChildViewBean;

import java.util.ArrayList;
import java.util.List;


public class ProductItemChildView extends JFXRippler {

    /*

    <StackPane alignment="BOTTOM_RIGHT"
               maxHeight="248" maxWidth="204"
               minHeight="248" minWidth="204">

               <StackPane
                alignment="TOP_LEFT" maxHeight="238" maxWidth="194" minHeight="238" minWidth="194"
                style="-fx-background-color: WHITE; -fx-background-radius: 10,10,5,5;">

     */

    private ProductItemChildViewBean dataBean;

    private StackPane rootStackPane;
    private ImageView productImage;
    private Button delBt, editBt;
    private Label productNameLabel, stateLabel, syncLabel, oldPriceLabel, nowPriceLabel, profitPriceLabel;

    private List<Node> nodesTemps;

    ProductItemChildView(ProductItemChildViewBean dataBean) {
        this.dataBean = dataBean;

        init();
    }

    private void init() {
        ThreadUtil.runInWork(initLayoutRunnable);
    }

    private Runnable initLayoutRunnable = new Runnable() {
        @Override
        public void run()  {
            nodesTemps = new ArrayList<>();

            setAlignment(Pos.BOTTOM_RIGHT);
            setStyle("-jfx-rippler-fill:  #808080; -fx-min-width: 204; -fx-max-width: 204; " +
                "-fx-min-height: 248; -fx-max-height: 248;");

        /*
               <StackPane
                alignment="TOP_LEFT" maxHeight="238" maxWidth="194" minHeight="238" minWidth="194"
                style="-fx-background-color: WHITE; -fx-background-radius: 10,10,5,5;">


                <JFXRippler maskType="RECT"
                style="-jfx-rippler-fill:  #808080;">
         */
            rootStackPane = new StackPane();
            rootStackPane.setAlignment(Pos.TOP_LEFT);
            rootStackPane.setStyle("-fx-min-width: 194; -fx-max-width: 194;" +
                "-fx-min-height: 238; -fx-max-height: 238;" +
                "-fx-background-color: WHITE; -fx-background-radius: 10,10,5,5;");


        /*
            <ImageView
                fx:id="imageView"
                fitHeight="135" fitWidth="194.0" pickOnBounds="true">
                <image>
                    <Image url="@../../../../../../Downloads/123123.jpg" />
                </image>
            </ImageView>
         */
            productImage = new ImageView();
            productImage.setFitHeight(135);
            productImage.setFitWidth(194);
            Rectangle clip = new Rectangle(194, 135);
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            productImage.setClip(clip);
            Image imageUrl = new Image(dataBean.getImageUrl());
            productImage.setImage(imageUrl);
            nodesTemps.add(productImage);


        /*

            <Button
                fx:id="deleteBt"
                visible="false"
                prefWidth="97" prefHeight="35" text="删除商品"
                style="
                       -fx-background-radius: 2;
                       -fx-font-size:14px;-fx-background-color:#FB522A;-fx-text-fill: WHITE">
                <StackPane.margin>
                    <Insets top="95" />
                </StackPane.margin>
            </Button>
         */
            delBt = createButton("删除商品", "#FB522A");
            StackPane.setMargin(delBt, new Insets(95, 0, 0 ,0));
            nodesTemps.add(delBt);


        /*

            <Button
                fx:id="editBt"
                visible="false"
                prefWidth="97" prefHeight="35" text="编辑商品"
                style="
                       -fx-background-radius: 2;
                       -fx-font-size:14px;-fx-background-color:#FF8130;-fx-text-fill: WHITE">
                <StackPane.margin>
                    <Insets top="95" left="97"/>
                </StackPane.margin>
            </Button>

         */
            editBt = createButton("编辑商品", "#FF8130");
            StackPane.setMargin(editBt, new Insets(95, 0, 0, 97));
            nodesTemps.add(editBt);


        /*

            <Label minHeight="10" minWidth="194"
                   style="-fx-background-color: WHITE;">
                <StackPane.margin>
                    <Insets top="130" />
                </StackPane.margin>
            </Label>
         */
            Label tempLabel = new Label();
            tempLabel.setMinSize(194, 10);
            tempLabel.setStyle("-fx-background-color: WHITE;");
            StackPane.setMargin(tempLabel, new Insets(130, 0, 0, 0));
            nodesTemps.add(tempLabel);


        /*
        <Label alignment="CENTER" style="-fx-text-fill: #666666; -fx-font-size: 13" text="人教版新编同步AB卷全...">
                <StackPane.margin>
                    <Insets top="140" />
                </StackPane.margin>
                <StackPane.alignment>TOP_CENTER</StackPane.alignment>
            </Label>
         */

            productNameLabel = createLabel(dataBean.getProductName(), "#666666", "13", 140, 0);
            StackPane.setAlignment(productNameLabel, Pos.TOP_CENTER);
            nodesTemps.add(productNameLabel);


        /*

            <Label alignment="CENTER" style="-fx-text-fill: #26A426; -fx-font-size: 12" text="发布成功">
                <StackPane.margin>
                    <Insets left="24" top="161" />
                </StackPane.margin>
            </Label>
         */
            stateLabel = createLabel(dataBean.getStateString(), dataBean.getStateColor(), "12", 161, 24);
            nodesTemps.add(stateLabel);

        /*
            <Label alignment="CENTER" style="-fx-text-fill: #3395DD; -fx-font-size: 12" text="同步成功">
                <StackPane.margin>
                    <Insets left="122" top="161" />
                </StackPane.margin>
            </Label>
         */
            syncLabel =createLabel(dataBean.getTime(), "#999999", "12", 161, 122);
            nodesTemps.add(syncLabel);


        /*
            <Label alignment="CENTER" style="-fx-text-fill: #101010; -fx-font-size: 16" text="29.9">
                <StackPane.margin>
                    <Insets left="17" top="185" />
                </StackPane.margin>
            </Label>
            <Label alignment="CENTER" style="-fx-text-fill: #999999; -fx-font-size: 13" text="原价">
                <StackPane.margin>
                    <Insets left="21" top="211" />
                </StackPane.margin>
            </Label>
         */
            oldPriceLabel = createPriceLabel(dataBean.getOldPrice(), "#101010", "16", 185, 9);
            Label oldPriceTempLabel = createLabel("原价", "#999999", "13", 211, 21);
            nodesTemps.add(oldPriceLabel);
            nodesTemps.add(oldPriceTempLabel);



        /*

            <Label alignment="CENTER" style="-fx-text-fill: #101010; -fx-font-size: 16" text="29.9">
                <StackPane.margin>
                    <Insets left="81" top="185" />
                </StackPane.margin>
            </Label>
            <Label alignment="CENTER" style="-fx-text-fill: #999999; -fx-font-size: 13" text="售价">
                <StackPane.margin>
                    <Insets left="84" top="211" />
                </StackPane.margin>
            </Label>
         */
            nowPriceLabel = createPriceLabel(dataBean.getNowPrice(), "#101010", "16", 185, 72);
            oldPriceTempLabel = createLabel("售价", "#999999", "13", 211, 84);
            nodesTemps.add(nowPriceLabel);
            nodesTemps.add(oldPriceTempLabel);



        /*
            <Label alignment="CENTER" style="-fx-text-fill: #101010; -fx-font-size: 16" text="29.9">
                <StackPane.margin>
                    <Insets left="144" top="185" />
                </StackPane.margin>
            </Label>
            <Label alignment="CENTER" style="-fx-text-fill: #999999; -fx-font-size: 13" text="售价">
                <StackPane.margin>
                    <Insets left="147" top="211" />
                </StackPane.margin>
            </Label>
         */
            profitPriceLabel = createPriceLabel(dataBean.getProfitPrice(), "#101010", "16", 185, 135);
            oldPriceTempLabel = createLabel("盈利", "#999999", "13", 211, 147);
            nodesTemps.add(profitPriceLabel);
            nodesTemps.add(oldPriceTempLabel);

            ThreadUtil.runInMain(addLayoutRunnable);
        }
    };

    private Runnable addLayoutRunnable = new Runnable() {
        @Override
        public void run() {
            setOnMouseEntered(event -> setBtVisible(true));
            setOnMouseExited(event -> setBtVisible(false));

            rootStackPane.getChildren().addAll(nodesTemps);
            setControl(rootStackPane);

        }
    };

    private void setBtVisible(boolean visible) {
        delBt.setVisible(visible);
        editBt.setVisible(visible);
    }

    private Button createButton(String text, String bg) {
        Button bt = new Button(text);
        bt.setVisible(false);
        bt.setPrefSize(97, 35);
        bt.setStyle("-fx-background-radius: 2;\n" +
            "-fx-font-size:14px;-fx-background-color:"+ bg + ";-fx-text-fill: WHITE");
        return bt;
    }

    private Label createLabel(String text, String fillString, String fontSize, double insetTop, double insetLeft) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill: "+ fillString + "; -fx-font-size: " + fontSize);
        StackPane.setMargin(label, new Insets(insetTop, 0, 0, insetLeft));

        return label;
    }

    private Label createPriceLabel(
        String text, String fillString, String fontSize, double insetTop, double insetLeft) {
        Label label = createLabel(text, fillString, fontSize, insetTop, insetLeft);

        label.setMinWidth(51);
        label.setAlignment(Pos.CENTER);
        return label;
    }

}
