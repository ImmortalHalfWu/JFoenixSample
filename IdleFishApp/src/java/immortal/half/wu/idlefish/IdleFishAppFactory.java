package immortal.half.wu.idlefish;

import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import immotal.half.wu.appManager.beans.IdleFishProductBean;
import immotal.half.wu.appManager.interfaces.IApp;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import org.jetbrains.annotations.Contract;

public class IdleFishAppFactory {

    @org.jetbrains.annotations.NotNull
    @Contract("_, _ -> new")
    public static IApp<IdleFishProductBean, UserInfoBean> createFishIdleApp(
            @org.jetbrains.annotations.NotNull String deviceId,
            @org.jetbrains.annotations.NotNull ITimeOutExecutorService executorService) throws NullPointerException {

        return new FishIdleApp(deviceId, executorService);
    }

}
