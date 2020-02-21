package wu.ui.weights;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import wu.ui.adapters.BaseListViewAdapter;
import wu.ui.weights.beans.ProductSettingViewBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.HashSet;


public class MyProductSettingView extends StackPane {

    private final ProductSettingViewBean dataBean;
    private ProductSettingViewListener listener;

    private HashSet<JFXTextField> jfxTextFields;

    public MyProductSettingView(ProductSettingViewBean dataBean, ProductSettingViewListener listener) {
        super();

        this.dataBean = dataBean;
        jfxTextFields = new HashSet<>();
        this.listener = listener;

        init();
    }

    private void init() {
        /*
<StackPane alignment="TOP_CENTER" prefHeight="653.0" prefWidth="500.0"
           fx:controller="wu.controllers.ProductSettingController"
           xmlns="http://javafx.com/javafx/8.0.172-ea" xmlns:fx="http://javafx.com/fxml/1">

    <padding>
        <Insets top="26" right="36" bottom="26" left="36" />
    </padding>
         */

        setAlignment(Pos.TOP_CENTER);
        setPrefSize(500, 623);
        setHeight(653);
        setWidth(500);
        setPadding(new Insets(26, 36, 26, 36));


        /*

    <Label styleClass="title-label" style="-fx-padding: 0; -fx-font-size: 18">
        商品信息
        <StackPane.alignment>TOP_LEFT</StackPane.alignment>
    </Label>
         */

        getChildren().add(createTitleText("商品信息", 0));

//      <VBox >

        VBox vBox = new VBox();
        ObservableList<Node> vboxChildren = FXCollections.observableArrayList();

        /*

        <MJFXTextField id="jfxTextArea" labelFloat="true"
                       prefHeight="29"
                       prefWidth="Infinity" promptText="商品地址">
            <validators>
                <RequiredFieldValidator message="We would love to get your feedback.">
                    <FontIcon iconLiteral="fas-exclamation-triangle" />
                </RequiredFieldValidator>
            </validators>
            <font>
                <Font size="14.0" />
            </font>
            <VBox.margin>
                <Insets top="40"/>
            </VBox.margin>

        </MJFXTextField>
         */

        MJFXTextField priTextField = createTextField("商品地址", dataBean.getUrl(), 40);
        priTextField.setDisable(true);
        vboxChildren.add(priTextField);

        /*

        <MJFXTextField id="jfxTextArea" labelFloat="true"
                      prefHeight="29" prefWidth="Infinity" promptText="商品描述">
            <validators>
                <RequiredFieldValidator message="We would love to get your feedback.">
                    <FontIcon iconLiteral="fas-exclamation-triangle" />
                </RequiredFieldValidator>
            </validators>
            <font>
                <Font size="14.0" />
            </font>
            <VBox.margin>
                <Insets top="40"/>
            </VBox.margin>
        </MJFXTextField>

         */
        vboxChildren.add(createTextField("商品描述", dataBean.getName(), 20).setFocusedChangeListener(new MJFXTextField.FocusedChangeListener() {
            @Override
            public void changed(MJFXTextField textField, boolean validate, boolean newValue) {
                if (!newValue && validate) {
                    String text = textField.getText();
                    System.out.println("商品描述" + text);
                    dataBean.setName(text);
                }
            }
        }));

        /*

        <Label styleClass="title-label" style="-fx-padding: 0; -fx-font-size: 18">
            商品价格
            <StackPane.alignment>TOP_LEFT</StackPane.alignment>
            <VBox.margin>
                <Insets top="26"/>
            </VBox.margin>
        </Label>
         */


        vboxChildren.add(createTitleText("商品价格", 26));


        /*
        <HBox spacing="40">

            <VBox.margin>
                <Insets top="20"/>
            </VBox.margin>
         */
        HBox hBox = new HBox(40);
        VBox.setMargin(hBox, new Insets(20, 0, 0, 0));

        /*

            <MJFXTextField id="jfxTextArea" labelFloat="true"
                           prefHeight="29" prefWidth="80" promptText="原价">
                <validators>
                    <RequiredFieldValidator message="We would love to get your feedback.">
                        <FontIcon iconLiteral="fas-exclamation-triangle" />
                    </RequiredFieldValidator>
                </validators>
                <font>
                    <Font size="14.0" />
                </font>
            </MJFXTextField>
         */
        hBox.getChildren().add(createNumberTextField("原价", dataBean.getOldMoney()).setFocusedChangeListener(new MJFXTextField.FocusedChangeListener() {
            @Override
            public void changed(MJFXTextField textField, boolean validate, boolean newValue) {
                if (!newValue && validate) {
                    String text = textField.getText();
                    System.out.println("原价" + text);
                    dataBean.setOldMoney(text);
                }
            }
        }));

        /*
            <MJFXTextField id="jfxTextArea" labelFloat="true"
                           prefHeight="29" prefWidth="80" promptText="售价">
                <validators>
                    <RequiredFieldValidator message="We would love to get your feedback.">
                        <FontIcon iconLiteral="fas-exclamation-triangle" />
                    </RequiredFieldValidator>
                </validators>
                <font>
                    <Font size="14.0" />
                </font>
            </MJFXTextField>
         */
        hBox.getChildren().add(createNumberTextField("售价", dataBean.getSellMoney()).setFocusedChangeListener(new MJFXTextField.FocusedChangeListener() {
            @Override
            public void changed(MJFXTextField textField, boolean validate, boolean newValue) {
                if (!newValue && validate) {
                    String text = textField.getText();
                    System.out.println("售价" + text);
                    dataBean.setSellMoney(text);
                }
            }
        }));

        /*
            <MJFXTextField id="jfxTextArea" labelFloat="true"
                           text="0.0"
                           prefHeight="29" prefWidth="80" promptText="运费">
                <validators>
                    <RequiredFieldValidator message="We would love to get your feedback.">
                        <FontIcon iconLiteral="fas-exclamation-triangle" />
                    </RequiredFieldValidator>
                </validators>
                <font>
                    <Font size="14.0" />
                </font>
            </MJFXTextField>
         */
        hBox.getChildren().add(createNumberTextField("运费", dataBean.getEmsMoney()).setFocusedChangeListener(new MJFXTextField.FocusedChangeListener() {
            @Override
            public void changed(MJFXTextField textField, boolean validate, boolean newValue) {
                if (!newValue && validate) {
                    String text = textField.getText();
                    System.out.println("运费" + text);
                    dataBean.setEmsMoney(text);
                }
            }
        }));
        vboxChildren.add(hBox);

        /*

        <Label styleClass="title-label" style="-fx-padding: 0; -fx-font-size: 18">
            标签选择
            <StackPane.alignment>TOP_LEFT</StackPane.alignment>
            <VBox.margin>
                <Insets top="26"/>
            </VBox.margin>
        </Label>
         */
        vboxChildren.add(createTitleText("标签选择", 26));


        /*
        <FlowPane vgap="10" hgap="10">
            <VBox.margin>
                <Insets top="5"/>
            </VBox.margin>
         */
        FlowPane flowPane = new FlowPane(10, 10);
//        flowPane.setStyle("-fx-background-color: WHITE; -fx-min-width:100; -fx-min-height:10;" +
//            "-fx-pref-width: 100; -fx-pref-height: 10;");
        VBox.setMargin(flowPane, new Insets(5, 0, 0, 0));
        ObservableList<Node> flowPaneChildren = FXCollections.observableArrayList();


        /*
        <JFXToggleNode styleClass="toggle-tag"
                           prefWidth="-Infinity"
                           pickOnBounds="false">
                <padding>
                    <Insets topRightBottomLeft="0"/>
                </padding>
                <Label text="精精精"
                       alignment="CENTER"
                       style="
                       -fx-min-width: 62;
                       -fx-font-size: 12;">
                    <font>
                        <Font size="14.0" />
                    </font>
                </Label>
            </JFXToggleNode>
         */
        for (String tag :
            dataBean.getTags()) {
            flowPaneChildren.add(createToggleNodeTag(tag));
        }

        flowPane.getChildren().addAll(flowPaneChildren);


        vboxChildren.add(flowPane);




        /*

        <Label styleClass="title-label" style="-fx-padding: 0; -fx-font-size: 18">
            图片选择
            <StackPane.alignment>TOP_LEFT</StackPane.alignment>
            <VBox.margin>
                <Insets top="26"/>
            </VBox.margin>
        </Label>
         */
        vboxChildren.add(createTitleText("图片选择", 26));

        /*
        <ListView fx:id="listView"
                  orientation="HORIZONTAL"
                  style="-fx-padding: 0; -fx-pref-height: 143">
            <VBox.margin>
                <Insets top="5"/>
            </VBox.margin>
            <!--<StackPane prefWidth="132" prefHeight="22" style="-fx-background-color: #123123"></StackPane>-->
        </ListView>
         */
        ListView<Node> listView = new ListView<>();
        listView.getStyleClass().add("product-setting-list");
        listView.setCellFactory(new BaseListViewAdapter<>());
        listView.setOrientation(Orientation.HORIZONTAL);
        listView.setStyle("-fx-padding: 0; -fx-pref-height: 143");
        ObservableList<Node> itemViews = FXCollections.observableArrayList();

        for (String url :
            dataBean.getImgUrls()) {
            itemViews.add(createItemView(url));
        }
        listView.setItems(itemViews);
        vboxChildren.add(listView);


        /*

        <HBox nodeOrientation="RIGHT_TO_LEFT" alignment="CENTER_LEFT" spacing="26">
            <VBox.margin>
            <Insets top="20"/>
            </VBox.margin>

            <JFXButton prefWidth="100" prefHeight="40" text="发布"
                       alignment="CENTER"
                       style="-fx-text-fill:#15a7f4;
                        -fx-padding: 0;
                        -fx-pref-width: 40;
                        -fx-pref-height: 20;
                        -fx-font-size:20;">
            </JFXButton>

            <JFXButton text="信息错误?"
                       alignment="BOTTOM_CENTER"
                       style="-fx-text-fill:#FB522A;
                        -fx-padding: 0;
                          -fx-pref-width: 63; -fx-pref-height: 15">
            </JFXButton>
        </HBox>
         */
        StackPane btBox = new StackPane();
        VBox.setMargin(btBox, new Insets(20, 0, 0, 0));

        JFXButton sendBt = new JFXButton("完成");
        StackPane.setAlignment(sendBt, Pos.CENTER_RIGHT);
        sendBt.setAlignment(Pos.CENTER);
        sendBt.setStyle("-fx-text-fill:#15a7f4;" +
            "                        -fx-padding: 0;" +
            "                        -fx-pref-width: 40;" +
            "                        -fx-pref-height: 20;" +
            "                        -fx-font-size:20;");
        sendBt.setOnAction(event -> {
            for (JFXTextField textField : jfxTextFields) {
                if (!textField.validate()) {
                    return;
                }
            }
            if (listener != null) {
                try {
                    listener.sendClick(dataBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btBox.getChildren().add(sendBt);


        JFXButton cancelBt = new JFXButton("取消");
        StackPane.setAlignment(cancelBt, Pos.TOP_RIGHT);
        StackPane.setMargin(cancelBt, new Insets(0, 66, 0, 0));
        cancelBt.setAlignment(Pos.CENTER);
        cancelBt.setStyle("-fx-text-fill: #999999;" +
            "                        -fx-padding: 0;" +
            "                        -fx-pref-width: 40;" +
            "                        -fx-pref-height: 20;" +
            "                        -fx-font-size: 20;");

        cancelBt.setOnAction(event -> {
            if (listener != null) {
                try {
                    listener.cancelClick(dataBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btBox.getChildren().add(cancelBt);


        JFXButton errBt = new JFXButton("信息有误?");
        errBt.setAlignment(Pos.CENTER);
        StackPane.setAlignment(errBt, Pos.CENTER_LEFT);
        errBt.setStyle("-fx-text-fill:#999999;" +
            "                        -fx-padding: 0;" +
            "                          -fx-pref-width: 63; -fx-pref-height: 15");
        errBt.setOnAction(event -> {
            errBt.setText("感谢反馈");
            if (listener != null) {
                try {
                    listener.errClick(dataBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btBox.getChildren().add(errBt);

        vboxChildren.add(btBox);
        vBox.getChildren().addAll(vboxChildren);
        getChildren().add(vBox);

    }

    private Node createItemView(String imageUrl) {
        javafx.scene.image.ImageView productImage = new javafx.scene.image.ImageView();
        productImage.setFitHeight(127);
        productImage.setFitWidth(127);
        Rectangle clip = new Rectangle(127, 127);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        productImage.setClip(clip);
        productImage.setImage(new Image(imageUrl, true));


        JFXToggleNode jfxToggleNode = new JFXToggleNode();

        jfxToggleNode.setOnMousePressed(event -> {
            if (!jfxToggleNode.isSelected()) {
                System.out.println("add image");
                dataBean.getImgUrls().add(imageUrl);
            } else {
                System.out.println("remove image");
                dataBean.getImgUrls().remove(imageUrl);
            }
        });

        jfxToggleNode.getStyleClass().add("toggle-icon1");
        jfxToggleNode.setStyle("-fx-pref-width: -Infinity; -fx-pref-height: -Infinity;" +
            "-fx-background-radius: 10; -jfx-toggle-color: #15a7f4; ");
        jfxToggleNode.setPadding(new Insets(3));
        jfxToggleNode.setGraphic(productImage);
//        jfxToggleNode.setSelected(true);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(3));
        stackPane.getChildren().add(jfxToggleNode);

        return stackPane;
    }

    private JFXToggleNode createToggleNodeTag(String tagName) {

        Label label = new Label(tagName);
        label.setAlignment(Pos.CENTER);
        label.setStyle(" -fx-text-fill: #999999");

        JFXToggleNode jfxToggleNode = new JFXToggleNode();
        jfxToggleNode.getStyleClass().add("toggle-tag");
        jfxToggleNode.setStyle("-fx-pref-width: -Infinity; padding: 0;");

        jfxToggleNode.setOnMousePressed(event -> {
            if (!jfxToggleNode.isSelected()) {
                System.out.println("add tag");
                dataBean.getTags().add(tagName);
            } else {
                System.out.println("remove tag");
                dataBean.getTags().remove(tagName);
            }
            label.setStyle("-fx-text-fill: " + (jfxToggleNode.isSelected()? "#999999" : "#15a7f4"));
        });

        jfxToggleNode.setGraphic(label);

        return jfxToggleNode;
    }


    private MJFXTextField createTextField(String prmptText, String innerText, int marginTop) {

        MJFXTextField mjfxTextField = new MJFXTextField();
        mjfxTextField.setText(innerText);
        mjfxTextField.setLabelFloat(true);
        mjfxTextField.setStyle("-fx-pref-width: Infinity; -fx-pref-height: 29; -fx-font-size: 14");
        mjfxTextField.setPromptText(prmptText);
        ValidatorBase textNullValidator = new RequiredFieldValidator("不能为空");
        textNullValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        mjfxTextField.setValidators(textNullValidator);
        VBox.setMargin(mjfxTextField, new Insets(marginTop, 0, 0, 0));

        jfxTextFields.add(mjfxTextField);
        return mjfxTextField;

    }

    private MJFXTextField createNumberTextField(String prmptText, String innerText) {

        ValidatorBase numberNUllValidator = new DoubleValidator("请输入数字");
        numberNUllValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));

        MJFXTextField textField = createTextField(prmptText, innerText,40);
        textField.setStyle("-fx-pref-width: 140; -fx-pref-height: 29; -fx-font-size: 14");
        textField.setValidators(numberNUllValidator);
        VBox.setMargin(textField, new Insets(0));

        jfxTextFields.add(textField);
        return textField;

    }


    private Label createTitleText(String text, int marginTop) {
        Label label = new Label(text);
        label.getStyleClass().add("title-label");
        label.setStyle("-fx-padding: 0; -fx-font-size: 18");
        VBox.setMargin(label, new Insets(marginTop, 0, 0, 0));
        StackPane.setAlignment(label, Pos.TOP_LEFT);
        return label;
    }

    public interface ProductSettingViewListener {
        void sendClick(ProductSettingViewBean dataBean);
        void cancelClick(ProductSettingViewBean dataBean);
        void errClick(ProductSettingViewBean dataBean);
    }

}
