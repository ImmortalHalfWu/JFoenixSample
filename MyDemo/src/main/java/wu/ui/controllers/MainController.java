package wu.ui.controllers;

import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import immortal.half.wu.LogUtil;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import wu.ui.adapters.BaseListViewAdapter;
import wu.ui.events.DeviceListItemClickEvent;
import wu.ui.events.ProductItemClickEvent;
import wu.ui.events.ProductItemDelClickEvent;
import wu.ui.events.ProductItemEditClickEvent;
import wu.ui.models.MainModel;
import wu.ui.models.beans.CacheIdleFishUserConfigBean;
import wu.ui.models.beans.UIDevAppProductBindBean;
import wu.ui.models.interfaces.MainModelListener;
import wu.ui.utils.MEventBus;
import wu.ui.weights.DeviceListItemView;
import wu.ui.weights.ProductListItemView;
import wu.ui.weights.beans.DeviceItemViewBean;
import wu.ui.weights.beans.ProductItemViewBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@ViewController(value = "/fxml/MainFxml.fxml", title = "我的demo")
public final class MainController implements MainModelListener {

    private static final String TAG = "MainController";

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private StackPane optionsBurger;
    @FXML
    private ListView<DeviceListItemView> deviceListView;
    @FXML
    private JFXButton sendProductBt;
    @FXML
    private JFXButton searchDeviceBt;
    @FXML
    private ListView<ProductListItemView> productListView;
    @FXML
    private BorderPane borderPane;

    private LinkedHashMap<DeviceListItemView, ObservableList<ProductListItemView>> deviceProductMap = new LinkedHashMap<>();
    private DeviceListItemView choiceDeviceItemView;

    @PostConstruct
    public void init() throws Exception {

        MEventBus.register(this);
        new MainModel(this);

        setBtIco(sendProductBt, "icomoon.svg.paper-plane-o, send-o");
        setBtIco(searchDeviceBt, "icomoon.svg.search2");

        deviceListView.getStyleClass().add("device-list");
        deviceListView.setPrefWidth(185);
        deviceListView.setCellFactory(new BaseListViewAdapter<>());
//        deviceListView.setItems(deviceViews);
//        deviceViews.add(new DeviceListItemView(DeviceItemViewBean.createConnectLoginBean("", "朴素不朴素", "")));
//        deviceViews.add(new DeviceListItemView(DeviceItemViewBean.createConnectUnLoginBean("", "")));
//        deviceViews.add(new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
//        deviceListView.setItems(deviceViews);
//        long s = System.currentTimeMillis();
//        List<ProductItemViewBean> datas = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            if (datas.size() == 4 || (i == 14  && datas.size() > 0)) {
//                productViews.add(new ProductListItemView(datas));
//                datas.clear();
//            }
//            datas.add(ProductItemViewBean.create(
//                    "TestUrl",
//                "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg",
//                "商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：商品：" + i,
//                "2020/02/13",
//                "发布成功",
//                "#26A426",
//                "" + i + 1000,
//                "" + i * 2  + 1000,
//                "" + i  + 1000
//            ));
//        }
//
//        ThreadUtil.runInMain(new Runnable() {
//            @Override
//            public void run() {
////                deviceViews.add(0, new DeviceListItemView(DeviceItemViewBean.createDisconnectLoginBean("", "朴素不朴素")));
//                deviceViews.replaceAll(new UnaryOperator<DeviceListItemView>() {
//                    @Override
//                    public DeviceListItemView apply(DeviceListItemView deviceListItemView) {
//                        if (BeanUtil.deviceItemIsConnectLogout(deviceListItemView)) {
//                            return new DeviceListItemView(
//                                    BeanUtil.deviceItemViewBeanConvertToConnectLogin(deviceListItemView, "new UserName")
//                            );
//                        }
//                        return deviceListItemView;
//                    }
//                });
//            }
//        }, 3000);
//
//        long e = System.currentTimeMillis();
//        System.out.println((e - s));

        productListView.setStyle("-fx-background-color: #eeeeee");
        productListView.setCellFactory(new BaseListViewAdapter<>());
//        productListView.setItems(productViews);

    }

    private void setBtIco(JFXButton bt, String icoName) throws Exception {
        SVGGlyph icoMoonGlyph = SVGGlyphLoader.getIcoMoonGlyph(icoName);
        icoMoonGlyph.setFill(Color.WHITE);
        icoMoonGlyph.setSize(20, 20);
        bt.setGraphic(icoMoonGlyph);
    }

    @Subscribe()
    public void deviceClick(DeviceListItemClickEvent deviceListItemClickEvent) {
        // todo 点击设备
        LogUtil.i(TAG, "点击设备：" + deviceListItemClickEvent.getDataBean());
        ObservableList<ProductListItemView> newProducts = deviceProductMap.get(deviceListItemClickEvent.getView());
        LogUtil.i(TAG, "获得商品列表：" + newProducts);
        if (newProducts != null) {
            productListView.setItems(newProducts);
        }
    }

    @Subscribe
    public void productClick(ProductItemClickEvent deviceListItemClickEvent) {
        System.out.println("点击商品：" + deviceListItemClickEvent.getProductItemViewBean());
//        todo 点击商品
    }

    @Subscribe
    public void productDelClick(ProductItemDelClickEvent delClickEvent) {
        System.out.println("点击删除商品：" + delClickEvent.getProductItemViewBean());
//        todo 点击删除商品
    }

    @Subscribe
    public void productEditClick(ProductItemEditClickEvent editClickEvent) {
        System.out.println("点击编辑商品：" + editClickEvent.getProductItemViewBean());
//        todo 点击编辑商品
    }

    @Override
    public void loadUserConfigOver(List<UIDevAppProductBindBean> devAppProductBindBeans) {
//        初始化设备列表， 全部未登录

        List<ProductItemViewBean> childViewBeanTemps = new ArrayList<>(4);

        devAppProductBindBeans.forEach(bean -> {
            ObservableList<ProductListItemView> productViewsTemps  = FXCollections.observableArrayList();

            DeviceListItemView deviceListItemView = new DeviceListItemView(
                    DeviceItemViewBean.createDisconnectLoginBean(bean, bean.getUserName().getName()));

            List<CacheIdleFishUserConfigBean.CacheIdleFishProductBean> productBeans = bean.getProducts();

            int size = productBeans.size();

            for (int i = 0, lineMaxCount = 0; i < size; i++, lineMaxCount++) {
                if (lineMaxCount == 4 || (i == size - 1  && lineMaxCount > 0)) {
                    productViewsTemps.add(new ProductListItemView(new ArrayList<>(childViewBeanTemps)));
                    childViewBeanTemps.clear();
                    lineMaxCount = 0;
                }
                CacheIdleFishUserConfigBean.CacheIdleFishProductBean idleFishProductBean = productBeans.get(i);
                ProductItemViewBean productItemViewBean = ProductItemViewBean.create(idleFishProductBean, idleFishProductBean);
                childViewBeanTemps.add(productItemViewBean);
            }

            deviceProductMap.put(deviceListItemView, productViewsTemps);
            childViewBeanTemps.clear();

        });


        LogUtil.i(TAG, "完成用户缓存数据到View层转换：个数 = " + deviceProductMap.size() + "__" + deviceProductMap);
        deviceListView.setItems(FXCollections.observableArrayList(deviceProductMap.keySet()));

    }

    @Override
    public void deviceConnectLogout(List<UIDevAppProductBindBean> devAppProductBindBeans, UIDevAppProductBindBean bean) {
//        todo 有设备连接， 未登录闲鱼用户
    }

    @Override
    public void deviceConnectLogin(List<UIDevAppProductBindBean> devAppProductBindBeans, UIDevAppProductBindBean bean) {
//        todo 有设备连接， 并登录了闲鱼用户
    }

    @Override
    public void deviceDisconnectLogin(List<UIDevAppProductBindBean> devAppProductBindBeans, UIDevAppProductBindBean bean) {
//        todo 从连接登录状态  转为  未连接状态
    }

    @Override
    public void deviceDisconnectLogout(List<UIDevAppProductBindBean> devAppProductBindBeans, UIDevAppProductBindBean bean) {
//        todo 设备未登录状态下断开连接， 移除设备UI即可
    }

}
