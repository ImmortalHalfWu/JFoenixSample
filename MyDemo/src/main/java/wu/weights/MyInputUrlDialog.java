package wu.weights;

import com.jfoenix.controls.*;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MyInputUrlDialog {

    public static void showDialog(StackPane root, InputUrlDialogCallBack callBack) {

        if (root == null) {
            return;
        }

        JFXDialog dialog = new JFXDialog();

        Label titleLabel = new Label("请输入商品地址");
        titleLabel.setStyle("-fx-text-fill: #333333");

        JFXTextField textField = new JFXTextField();
        textField.setStyle("-fx-text-fill: #4d4d4d;");
        textField.setPromptText("目前支持天猫、京东及拼多多等平台");
        textField.setValidators(new ValidatorBase("目前支持天猫、京东及拼多多等平台") {
            @Override
            protected void eval() {
                hasErrors.set(true);
            }
        });


        JFXButton button = new JFXButton("  解析  ");
        button.setStyle("-fx-text-fill: #5264ae");
        button.setOnAction(event -> {
            try {
                if (callBack == null || callBack.filter(textField.getText())) {
                    textField.validate();
                } else {
                    callBack.sucClick(textField.getText());
                    dialog.close();
                }
            } catch (Exception ignored) { }
        });


        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setHeading(titleLabel);
        jfxDialogLayout.setBody(textField);
        jfxDialogLayout.setActions(button);


        dialog.setContent(jfxDialogLayout);
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.show(root);

    }


    public interface InputUrlDialogCallBack {
        boolean filter(String text);
        void sucClick(String text);
    }

}
