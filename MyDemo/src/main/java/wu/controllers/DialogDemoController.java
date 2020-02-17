package wu.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import wu.weights.MyDeviceListDialog;
import wu.weights.MyInputUrlDialog;
import wu.weights.MyProductSettingUrlDialog;
import wu.weights.MyProductSettingView;
import wu.weights.beans.DeviceItemViewBean;
import wu.weights.beans.ProductSettingViewBean;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ViewController(value = "/fxml/DialogDemo.fxml", title = "我的DialogDemo")
public class DialogDemoController implements Initializable {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    public StackPane root;

    @FXML
    public JFXButton showDialogBt;

    private JFXDialog dialog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDialogBt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                MyInputUrlDialog.showDialog(root, new MyInputUrlDialog.InputUrlDialogCallBack() {
                    @Override
                    public boolean filter(String text) {
                        return text == null || !text.startsWith("a");
                    }

                    @Override
                    public void sucClick(String text) {

                        MyProductSettingUrlDialog.showDialogTest(root, new MyProductSettingView.ProductSettingViewListener() {
                            @Override
                            public void sendClick(ProductSettingViewBean dataBean) {

                                List<DeviceItemViewBean> data = new ArrayList<>();
                                for (int i = 0; i < 10; i++) {
                                    data.add(
                                        DeviceItemViewBean.createConnectLoginBean("", i + "奥术大师多")
                                    );
                                }

                                MyDeviceListDialog.showDeviceListDialog(root, data, new MyDeviceListDialog.MyDeviceListDialogListener() {
                                    @Override
                                    public void clickSend(List<DeviceItemViewBean> choiceDeviceItemBean) {
                                        System.out.println(choiceDeviceItemBean);
                                    }
                                });
                            }

                            @Override
                            public void cancelClick(ProductSettingViewBean dataBean) {

                            }

                            @Override
                            public void errClick(ProductSettingViewBean dataBean) {

                            }
                        });
                    }
                });

            }
        });
    }
}
