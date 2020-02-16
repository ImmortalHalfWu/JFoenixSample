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
import wu.weights.MProductSettingView;
import wu.weights.MyInputUrlDialog;
import wu.weights.MyProductForUrlDialog;
import wu.weights.beans.ProductSettingViewBean;

import java.net.URL;
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

                        MyProductForUrlDialog.showDialogTest(root, new MProductSettingView.ProductSettingViewListener() {
                            @Override
                            public void sendClick(ProductSettingViewBean dataBean) {

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
