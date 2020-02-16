package wu.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import wu.adapters.BaseListViewAdapter;
import wu.weights.DeviceListItemView;
import wu.weights.ProductListItemView;
import wu.weights.beans.DeviceItemViewBean;
import wu.weights.beans.ProductItemChildViewBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@ViewController(value = "/fxml/MainFxml.fxml", title = "我的demo")
public final class MainController {

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private StackPane optionsBurger;
    @FXML
    private ListView<Node> listView;
    @FXML
    private JFXButton sendProductBt;
    @FXML
    private JFXButton searchDeviceBt;
    @FXML
    private ListView<Node> productListView;
    @FXML
    private BorderPane borderPane;


    @PostConstruct
    public void init() throws Exception {

        setBtIco(sendProductBt, "icomoon.svg.paper-plane-o, send-o");
        setBtIco(searchDeviceBt, "icomoon.svg.search2");

        listView.getStyleClass().add("device-list");
        listView.setPrefWidth(185);
        listView.setCellFactory(new BaseListViewAdapter<>());
        ObservableList<Node> deviceDatas = FXCollections.observableArrayList();
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createConnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createConnectUnLoginBean("")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        deviceDatas.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
        listView.setItems(deviceDatas);

        long s = System.currentTimeMillis();
        ObservableList<Node> itemViews = FXCollections.observableArrayList();
        List<ProductItemChildViewBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (datas.size() == 4 || (i == 14  && datas.size() > 0)) {
                itemViews.add(new ProductListItemView(datas));
                datas.clear();
            }
            datas.add(ProductItemChildViewBean.create(
                "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg",
                "商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：" + i,
                "2020/02/13",
                "发布成功",
                "#26A426",
                "" + i + 1000,
                "" + i * 2  + 1000,
                "" + i  + 1000
            ));
        }

        long e = System.currentTimeMillis();
        System.out.println((e - s));

        productListView.setStyle("-fx-background-color: #eeeeee");
        productListView.setCellFactory(new BaseListViewAdapter<>());
        productListView.setItems(itemViews);

    }

    private void setBtIco(JFXButton bt, String icoName) throws Exception {
        SVGGlyph icoMoonGlyph = SVGGlyphLoader.getIcoMoonGlyph(icoName);
        icoMoonGlyph.setFill(Color.WHITE);
        icoMoonGlyph.setSize(20, 20);
        bt.setGraphic(icoMoonGlyph);
    }

}
