package immotal.half.wu.appManager.controls;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.interfaces.IJobWithTimeOut;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.intefaces.IPage;

import java.util.ArrayList;
import java.util.List;

public class JobPagerControl<DoResultType> implements IJobWithTimeOut<DoResultType> {

    private final static int tryAgainCount = 3;

    private final @NotNull List<IPage<?>> iPages;
    private final @NotNull ADBManager adbManager;
    private final @NotNull DeviceInfoBean deviceInfoBean;

    public JobPagerControl(
            @NotNull ADBManager adbManager,
            @NotNull DeviceInfoBean deviceInfoBean) {

        this.adbManager = adbManager;
        this.deviceInfoBean = deviceInfoBean;
        this.iPages = new ArrayList<>();
    }

    public void addPager(@Nullable IPage<?> page) {
        if (page != null) {
            iPages.add(page);
        }
    }

    @Override
    public DoResultType run() throws Exception {

        LogUtil.d(AppManagerUtil.TAG, "开始轮询IPager任务，共" + tryAgainCount + "次，" + iPages);

        for (int i = 0; i < tryAgainCount; i++) {

            LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务第" + (i + 1) + "次");
            DoResultType o = looperIPager();
            LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务结束，结果：" + o);
            if (o != null) {
                return o;
            }
        }

        throw new IllegalStateException("轮询IPager任务失败，未得到正确结果：" + iPages);

    }

    private @Nullable DoResultType looperIPager() throws InterruptedException {

        if (iPages.isEmpty()) {
            throw new IllegalStateException("轮询IPager任务异常，任务队列为null ：" + iPages);
        }

        String xml = null;
        IPage page = null;
        Object result = null;

        for (IPage iPage : iPages) {

            do {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }

                page = iPage;

                if (xml != null && page.check(xml, deviceInfoBean, adbManager)) {
//                    LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务，" + iPage + "复用xml check成功");
                } else {
                    xml = AppManagerUtil.readUiInfo(deviceInfoBean, adbManager);
                    if (!page.check(xml, deviceInfoBean, adbManager)) {
                        LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务，" + iPage + "check失败");
//                    throw new IllegalStateException("轮询IPager任务，" + iPage + "check失败");
                        return null;
//                    break;
                    }
//                    LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务，" + iPage + "check成功");
                }


                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }

                result = page.doPageProcess(xml, deviceInfoBean, adbManager);

                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }

//                LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务," + iPage + "process成功：" + result );

            } while (!page.isComplete(xml, deviceInfoBean, adbManager));

        }

        Class<DoResultType> resultType = page.getResultType();
        if (resultType != null) {
            return resultType.cast(result);
        } else {
            return (DoResultType) result;
        }

    }
}
