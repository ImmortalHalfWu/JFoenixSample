package wu.ui.weights;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXToggleNode;
import wu.ui.adapters.BaseListViewAdapter;
import wu.ui.weights.beans.DeviceItemViewBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class MyDeviceListDialog {

    public static void showDeviceListDialog(
        StackPane root,
        List<DeviceItemViewBean> loginDeviceItemBean,
        MyDeviceListDialogListener listDialogListener) {

        try {
            if (root == null || listDialogListener == null) {
                return;
            }

            List<DeviceItemViewBean> choiceDeviceItemBean = new ArrayList<>();

            JFXDialog dialog = new JFXDialog();
            VBox vBox = new VBox();

            Label title = new Label("选择发布的账号");
            title.getStyleClass().add("title-label");
            title.setStyle(" -fx-font-size: 18; -fx-text-fill: #99999; -fx-pref-width: 245");
            title.setPadding(new Insets(0, 0, 0, 0));
            title.setAlignment(Pos.CENTER);
            vBox.getChildren().add(title);

            ListView<Node> listView = new ListView<>();
            ObservableList<Node> itemViews = FXCollections.observableArrayList();

            for (DeviceItemViewBean bean :
                loginDeviceItemBean) {
                itemViews.add(createItemView(bean, choiceDeviceItemBean));
            }

            listView.getStyleClass().add("dialog-device-list");
            listView.setCellFactory(new BaseListViewAdapter<>());
            listView.setItems(itemViews);
            vBox.getChildren().add(listView);

            Label errLabel = new Label("未选择发布的账号");
            errLabel.setStyle("-fx-text-fill: #fc4426; -fx-font-size: 13; visibility: false;" +
                "-fx-pref-width: 245; -fx-alignment: CENTER; -fx-padding: 5");
            vBox.getChildren().add(errLabel);


            JFXButton sendBt = new JFXButton("发布");
            sendBt.setStyle(" -fx-font-size: 18; -fx-text-fill: WHITE; " +
                "-fx-background-color: #15a7f4; -fx-pref-width: 125");
            sendBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (choiceDeviceItemBean.size() == 0) {
                        errLabel.setVisible(true);
                    } else {
                        listDialogListener.clickSend(choiceDeviceItemBean);
                        dialog.close();
                    }
                }
            });
            StackPane.setAlignment(sendBt, Pos.CENTER_RIGHT);


            JFXButton cancelBt = new JFXButton("取消");
            cancelBt.setStyle(" -fx-font-size: 18; -fx-text-fill: WHITE; " +
                "-fx-background-color: #dcdcdc; -fx-pref-width: 125");
            cancelBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });
            StackPane.setAlignment(cancelBt, Pos.CENTER_LEFT);


            StackPane stackPane = new StackPane();
            stackPane.setStyle(" -fx-pref-width: 250");
            stackPane.getChildren().addAll(sendBt, cancelBt);

            vBox.getChildren().add(stackPane);

            dialog.setContent(vBox);
            dialog.setTransitionType(JFXDialog.DialogTransition.NONE);
            dialog.setOverlayClose(false);
            dialog.show(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Node createItemView(DeviceItemViewBean bean, List<DeviceItemViewBean> choiceDeviceItemBeans) {

        DeviceListItemView deviceListItemView = new DeviceListItemView(bean);
        deviceListItemView.setStyle("-fx-background-color:WHITE; -fx-background-radius: 10; -fx-min-width: 210; -fx-max-width: 210;  ");

        JFXToggleNode jfxToggleNode = new JFXToggleNode();

        jfxToggleNode.setOnMousePressed(event -> {
            if (!jfxToggleNode.isSelected()) {
                choiceDeviceItemBeans.add(bean);
//                dataBean.getImgUrls().add(imageUrl);
            } else {
                choiceDeviceItemBeans.remove(bean);
//                dataBean.getImgUrls().remove(imageUrl);
            }
        });

        jfxToggleNode.getStyleClass().add("toggle-icon1");
        jfxToggleNode.setStyle("-fx-pref-width: -Infinity; -fx-pref-height: -Infinity;" +
            "-fx-background-radius: 10; -jfx-toggle-color: rgba(21, 167, 244, 0.7) ");
        jfxToggleNode.setPadding(new Insets(3));
        jfxToggleNode.setGraphic(deviceListItemView);
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(3));
        stackPane.getChildren().add(jfxToggleNode);

        return stackPane;
    }


    public interface MyDeviceListDialogListener {
        void clickSend(List<DeviceItemViewBean> choiceDeviceItemBean);
    }

}
