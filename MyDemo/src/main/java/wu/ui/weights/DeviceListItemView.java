package wu.ui.weights;

import wu.ui.events.DeviceListItemClickEvent;
import wu.ui.utils.MEventBus;
import wu.ui.weights.beans.DeviceItemViewBean;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class DeviceListItemView extends StackPane {

    private static DeviceItemViewBean choiceDataBean;

    private final DeviceItemViewBean dataBean;

    public DeviceListItemView(DeviceItemViewBean dataBean) {
        super();
        this.dataBean = dataBean;
        init();
    }

    private void init() {

        /*

           alignment="TOP_LEFT"
           prefHeight="55"
           minHeight="55"
           maxHeight="55"
           prefWidth="185"
           minWidth="185"
           maxWidth="185">

    <ImageView fitWidth="15" fitHeight="15" style="-fx-background-color: #123123">
        <StackPane.margin>
            <Insets left="8" top="8"/>
        </StackPane.margin>
    </ImageView>

    <Label style="-fx-font-size: 12;  -fx-text-fill: #888888">
        未连接
        <StackPane.margin>
            <Insets left="21" top="8"/>
        </StackPane.margin>
    </Label>

    <Label style="-fx-font-size: 16; -fx-text-fill: #888888">
        未登录

        <StackPane.margin>
            <Insets left="8" bottom="8"/>
        </StackPane.margin>
        <StackPane.alignment>BOTTOM_LEFT</StackPane.alignment>
    </Label>

         */

//        setStyle("-fx-min-width: 180; -fx-max-width: 180; " +
//            "-fx-min-height: 55; -fx-max-height: 5;");

        setAlignment(Pos.TOP_LEFT);
        setPrefHeight(55);
        setPrefWidth(182);


        ImageView iconImage = new ImageView();
        iconImage.setFitWidth(15);
        iconImage.setFitHeight(15);
        StackPane.setMargin(iconImage, new Insets(8, 0, 0, 8));
        iconImage.setImage(new Image(dataBean.getImageUrl(), true));

        Label deviceStateLabel = new Label(dataBean.getDeviceStateText());
        deviceStateLabel.setStyle("-fx-font-size: 12; -fx-text-fill: " + dataBean.getDeviceStateColor());
        StackPane.setMargin(deviceStateLabel, new Insets(8, 0, 0, 31));

        Label loginStateLabel = new Label(dataBean.getUserNameText());
        loginStateLabel.setStyle("-fx-font-size: 16; -fx-text-fill: " + dataBean.getUserNameColor());
        StackPane.setMargin(loginStateLabel, new Insets(8, 0, 8, 8));
        StackPane.setAlignment(loginStateLabel, Pos.BOTTOM_LEFT);


        getChildren().add(iconImage);
        getChildren().add(deviceStateLabel);
        getChildren().add(loginStateLabel);

        setOnMouseClicked(event -> {
            if (choiceDataBean != null && choiceDataBean.equals(dataBean)) {
                return;
            }
            choiceDataBean = dataBean;
            MEventBus.post(new DeviceListItemClickEvent(DeviceListItemView.this, dataBean));
        });
    }

    public DeviceItemViewBean getDataBean() {
        return dataBean;
    }

}
