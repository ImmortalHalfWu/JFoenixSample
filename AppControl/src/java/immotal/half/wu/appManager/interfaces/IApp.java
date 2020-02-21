package immotal.half.wu.appManager.interfaces;

import java.util.ArrayList;

public interface IApp<ProductInfo,LoginUserInfo> {

    void startApp(IAppCallBack<Boolean> callBack);
    void isLogin(IAppCallBack<Boolean> callBack);
    void postProduct(ProductInfo productInfo, IAppCallBack<Boolean> callBack);
    void deleteProduct(String name, IAppCallBack<Boolean> callBack);
    void getPostedProductsName(IAppCallBack<ArrayList<String>> callBack);
    void getUserName(IAppCallBack<LoginUserInfo> callBack);
    void refreshPostedProduct(IAppCallBack<Boolean> callBack);
    void disconnect();
}
