package immotal.half.wu.appManager.interfaces;

public interface IAppCallBack<T> {

    void onError(Exception e);
    void onComplete(T result);

}
