package wu.device.division.interfaces;

import java.util.ArrayList;


/**
 * see {@link immotal.half.wu.appManager.interfaces.IApp}
 */
public interface IUiApp<ProductInfo,LoginUserInfo> {
    void startApp(IUiAppCallBack<Boolean> callBack);
    void isLogin(IUiAppCallBack<LoginUserInfo> callBack);
    void postProduct(ProductInfo productInfo, IUiAppCallBack<Boolean> callBack);
    void deleteProduct(String name, IUiAppCallBack<Boolean> callBack);
    void getPostedProductsName(IUiAppCallBack<ArrayList<String>> callBack);
    void getUserName(IUiAppCallBack<LoginUserInfo> callBack);
    void refreshPostedProduct(IUiAppCallBack<Boolean> callBack);
    void disconnect();
}
