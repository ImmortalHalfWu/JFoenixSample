package immortal.half.wu.idlefish;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import immotal.half.wu.appManager.beans.IdleFishProductBean;
import immotal.half.wu.appManager.controls.JobPagerControl;
import immotal.half.wu.appManager.interfaces.IApp;
import immotal.half.wu.appManager.interfaces.IAppCallBack;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FishIdleApp implements IApp<IdleFishProductBean, UserInfoBean> {

    @NotNull
    private final IdleFishModel idleFishControl;
    @NotNull
    private final DeviceInfoBean deviceInfoBean;
    @NotNull
    private final ITimeOutExecutorService timeOutExecutorService;

    FishIdleApp(@NotNull String deviceId, @NotNull ITimeOutExecutorService executorService) {
        IdleFishUtil.initFishIdleApp(deviceId);
        idleFishControl = new IdleFishModel();
        deviceInfoBean = new DeviceInfoBean(deviceId, ADBManager.getInstance().getDxSize(deviceId));
        timeOutExecutorService = executorService;
    }

    @Override
    public void startApp(@NotNull IAppCallBack<Boolean> callBack) {
        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(idleFishControl.去主页面);
        control.addPager(idleFishControl.取消升级);
        runJobPager(control, callBack);
    }

    @Override
    public void isLogin(@NotNull IAppCallBack<Boolean> callBack) {

        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(idleFishControl.去主页面);
        control.addPager(idleFishControl.取消升级);
        control.addPager(idleFishControl.前往首页我的);
        control.addPager(idleFishControl.是否登录fishIdle);
        runJobPager(control, callBack);
    }

    @Override
    public void postProduct(@NotNull IdleFishProductBean idleFishProductBean, @NotNull IAppCallBack<Boolean> callBack) {

        List<File> imageList = idleFishProductBean.getImageList();
        ADBManager adb = ADBManager.getInstance();
        for (File imgFile :
                imageList) {
            adb.adbPushFile(deviceInfoBean.getDeviceId(), imgFile.getAbsolutePath(), imgFile.getName());
            adb.adbScanFile(deviceInfoBean.getDeviceId(), imgFile.getName());
        }

        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(idleFishControl.去主页面);
        control.addPager(idleFishControl.取消升级);
        control.addPager(idleFishControl.前往发布类型选择页);
        control.addPager(idleFishControl.前往发布闲置);
        control.addPager(idleFishControl.发布闲置图片选择imgs(9));
        control.addPager(idleFishControl.前往图片编辑页);

        List<String> imgTag = idleFishProductBean.getImgTag();
        for (String tag : imgTag) {
            control.addPager(idleFishControl.前往图片标签选择页);
            control.addPager(idleFishControl.输入商品Tag(tag));
            control.addPager(idleFishControl.选择第一个imgTAG(tag));
        }

        control.addPager(idleFishControl.前往发布商品信息页);
        control.addPager(idleFishControl.商品信息页输入商品信息info(idleFishProductBean.getTitle() + idleFishProductBean.getInfo()));
        control.addPager(idleFishControl.商品信息页前往金额设置);
        control.addPager(idleFishControl.商品信息页设置包邮);
        control.addPager(idleFishControl.商品信息页设置入手价);
        control.addPager(idleFishControl.调用发布闲置键盘输入价格price(idleFishProductBean.getOldPrice()));
        control.addPager(idleFishControl.商品信息页设置一口价);
        control.addPager(idleFishControl.调用发布闲置键盘输入价格price(idleFishProductBean.getPrice()));
        control.addPager(idleFishControl.商品信息页完成金额设置);
        control.addPager(idleFishControl.商品信息页前往其他选项);
        control.addPager(idleFishControl.商品信息页设置其他选项);
        control.addPager(idleFishControl.完成闲置商品设置并发布);

        runJobPager(control, callBack);
    }

    @Override
    public void deleteProduct(@NotNull String name, @NotNull IAppCallBack<Boolean> callBack) {
        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(idleFishControl.去主页面);
        control.addPager(idleFishControl.取消升级);
        control.addPager(idleFishControl.前往首页我的);
        control.addPager(idleFishControl.前往已发布页);
        control.addPager(idleFishControl.选择删除指定商品del(name));
        control.addPager(idleFishControl.进入更多列表);
        control.addPager(idleFishControl.确定删除商品);
        runJobPager(control, callBack);
    }

    @Override
    public void getPostedProductsName(@NotNull IAppCallBack<ArrayList<String>> callBack) {
        JobPagerControl<ArrayList<String>> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(idleFishControl.去主页面);
        control.addPager(idleFishControl.取消升级);
        control.addPager(idleFishControl.前往首页我的);
        control.addPager(idleFishControl.前往已发布页);
        control.addPager(idleFishControl.获取已发布商品);
        runJobPager(control, callBack);
    }

    @Override
    public void getUserName(@NotNull IAppCallBack<UserInfoBean> callBack) {
        JobPagerControl<UserInfoBean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(idleFishControl.去主页面);
        control.addPager(idleFishControl.取消升级);
        control.addPager(idleFishControl.前往首页我的);
        control.addPager(idleFishControl.获取登录信息);
        runJobPager(control, callBack);
    }

    @Override
    public void refreshPostedProduct(@NotNull IAppCallBack<Boolean> callBack) {
        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(idleFishControl.去主页面);
        control.addPager(idleFishControl.取消升级);
        control.addPager(idleFishControl.前往首页我的);
        control.addPager(idleFishControl.前往已发布页);
        control.addPager(idleFishControl.刷新所有商品);
        runJobPager(control, callBack);
    }

    @Override
    public void disconnect() {
        try {
            if (!timeOutExecutorService.isShutdown()) {
                timeOutExecutorService.shutdownNow().clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <ResultType> void runJobPager(JobPagerControl<ResultType> control, @NotNull IAppCallBack<ResultType> appCallBack) {
        timeOutExecutorService.executeTimeOut(control, new IJobListener<ResultType>() {
            @Override
            public void onError(Exception e) {
                appCallBack.onError(e);
            }

            @Override
            public void onComplete(ResultType result) {
                appCallBack.onComplete(result);
            }
        });
    }
}
