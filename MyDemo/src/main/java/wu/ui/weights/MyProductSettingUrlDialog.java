package wu.ui.weights;

import com.jfoenix.controls.JFXDialog;
import wu.ui.weights.beans.ProductSettingViewBean;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class MyProductSettingUrlDialog {

    public static void showDialogTest(StackPane root, MyProductSettingView.ProductSettingViewListener listener) {
        if (root == null) {
            return;
        }

        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            tags.add("标签" + i);
            imgUrls.add("https://img.alicdn.com/imgextra/i3/3233918042/O1CN01UsJ6pB29HHkGA8k4B_!!3233918042.jpg");
        }

        ProductSettingViewBean productSettingViewBean = ProductSettingViewBean.create(
            "https://detail.tmall.com/item.htm?spm=a220o.1000855.w5001-17792484070.4.97245476jz5ZO2&id=575188036159&rn=eab8d63190b5cdd97862c52bb7063851&abbucket=14&scene=taobao_shop",
            "小熊电热饭盒可插电加热保温双层带饭神器菜蒸煮电饭锅煲小上班族",
            "149",
            "159",
            "0.0",
            tags, imgUrls
        );
        showDialog(root, productSettingViewBean, listener);
    }


    public static void showDialog(StackPane root, ProductSettingViewBean bean, MyProductSettingView.ProductSettingViewListener listener) {

        try {
            if (root == null) {
                return;
            }

            JFXDialog dialog = new JFXDialog();

            StackPane dialogRoot = new MyProductSettingView(bean, new MyProductSettingView.ProductSettingViewListener() {
                @Override
                public void sendClick(ProductSettingViewBean dataBean) {
                    listener.sendClick(dataBean);
                    dialog.close();
                }

                @Override
                public void cancelClick(ProductSettingViewBean dataBean) {
                    dialog.close();
                }

                @Override
                public void errClick(ProductSettingViewBean dataBean) {
                    listener.errClick(dataBean);
                }
            });

            dialog.setContent(dialogRoot);
            dialog.setTransitionType(JFXDialog.DialogTransition.NONE);
            dialog.setOverlayClose(false);
            dialog.show(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
