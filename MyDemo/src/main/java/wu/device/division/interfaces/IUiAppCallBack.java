package wu.device.division.interfaces;


/**
 * see {@link immotal.half.wu.appManager.interfaces.IAppCallBack}
 */
public interface IUiAppCallBack<T> {
    void onError(Exception e);
    void onComplete(T result);
}
