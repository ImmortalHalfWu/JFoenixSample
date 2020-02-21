package wu.device.division;

import immortal.half.wu.LogUtil;
import wu.device.division.interfaces.IUiApp;
import wu.device.division.interfaces.IUiAppCallBack;

import java.util.ArrayList;

public class NullUiApp<t1, t2> implements IUiApp<t1, t2> {

    private static final String TAG = "NullUiApp";

    @Override
    public void startApp(IUiAppCallBack<Boolean> callBack) {
        LogUtil.e(TAG, "NullUiApp startApp");
    }

    @Override
    public void isLogin(IUiAppCallBack<t2> callBack) {
        LogUtil.e(TAG, "NullUiApp isLogin");
    }

    @Override
    public void postProduct(t1 t1, IUiAppCallBack<Boolean> callBack) {
        LogUtil.e(TAG, "NullUiApp postProduct");
    }

    @Override
    public void deleteProduct(String name, IUiAppCallBack<Boolean> callBack) {
        LogUtil.e(TAG, "NullUiApp deleteProduct");
    }

    @Override
    public void getPostedProductsName(IUiAppCallBack<ArrayList<String>> callBack) {
        LogUtil.e(TAG, "NullUiApp getPostedProductsName");
    }

    @Override
    public void getUserName(IUiAppCallBack<t2> callBack) {
        LogUtil.e(TAG, "NullUiApp getUserName");
    }

    @Override
    public void refreshPostedProduct(IUiAppCallBack<Boolean> callBack) {
        LogUtil.e(TAG, "NullUiApp refreshPostedProduct");
    }

    @Override
    public void disconnect() {
        LogUtil.e(TAG, "NullUiApp disconnect");
    }
}
