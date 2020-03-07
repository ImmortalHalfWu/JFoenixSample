package wu.device.division;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.devices.interfaces.IAndroidDevice;
import immortal.half.wu.idlefish.IdleFishAppFactory;
import immotal.half.wu.appManager.beans.IdleFishProductBean;
import immotal.half.wu.appManager.interfaces.IApp;
import immotal.half.wu.appManager.interfaces.IAppCallBack;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import wu.device.division.interfaces.IUiApp;
import wu.device.division.interfaces.IUiAppCallBack;

import java.util.ArrayList;

/**
 * see {@link immortal.half.wu.idlefish.FishIdleApp}
 */
public class UiFishIdleAppImpl implements IUiApp<UIIdleFishProductBean, UIUserInfoBean> {

    private final IApp<IdleFishProductBean, UserInfoBean> fishIdleApp;

    public UiFishIdleAppImpl(@NotNull IAndroidDevice androidDevice) {
        this.fishIdleApp = IdleFishAppFactory.createFishIdleApp(androidDevice.getDeviceId(), androidDevice.getExecutorService());
    }

    
    public void startApp(IUiAppCallBack<Boolean> callBack) {
        fishIdleApp.startApp(new MAppCallBack<>(callBack));
    }

    
    public void isLogin(IUiAppCallBack<UIUserInfoBean> callBack) {
        getUserName(callBack);
    }


    public void postProduct(UIIdleFishProductBean UIIdleFishProductBean, IUiAppCallBack<Boolean> callBack) {
        fishIdleApp.postProduct(UIIdleFishProductBean.getProductBean(), new MAppCallBack<>(callBack));
    }

    
    public void deleteProduct(String name, IUiAppCallBack<Boolean> callBack) {
        fishIdleApp.deleteProduct(name, new MAppCallBack<>(callBack));
    }

    
    public void getPostedProductsName(IUiAppCallBack<ArrayList<String>> callBack) {
        fishIdleApp.getPostedProductsName(new MAppCallBack<>(callBack));
    }


    public void getUserName(IUiAppCallBack<UIUserInfoBean> callBack) {
        fishIdleApp.isLogin(new MAppCallBack<>(new IUiAppCallBack<Boolean>() {
            @Override
            public void onError(Exception e) {
                callBack.onError(e);
            }

            @Override
            public void onComplete(Boolean result) {
                if (!result) {
                    callBack.onComplete(UIUserInfoBean.createLogout());
                    return;
                }
                fishIdleApp.getUserName(new IAppCallBack<UserInfoBean>() {
                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete(UserInfoBean result) {
                        callBack.onComplete(UIUserInfoBean.create(result.getName(), true));
                    }
                });
            }
        }));
    }

    
    public void refreshPostedProduct(IUiAppCallBack<Boolean> callBack) {
        fishIdleApp.refreshPostedProduct(new MAppCallBack<>(callBack));
    }

    @Override
    public void disconnect() {
        fishIdleApp.disconnect();
    }

    private static class MAppCallBack<T> implements IAppCallBack<T> {

        private IUiAppCallBack<T> callBack;

        private MAppCallBack(IUiAppCallBack<T> callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onError(Exception e) {
            callBack.onError(e);
        }

        @Override
        public void onComplete(T result) {
            callBack.onComplete(result);
        }
    }
}
