package immortal.half.wu.http;

import immortal.half.wu.JsonUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public abstract class SimpleCallBack<T> implements Callback {

    @Override
    public void onFailure(Call call, @NotNull IOException e) {
        e.printStackTrace();
        onFail(call, e);
    }

    @Override
    public void onResponse(Call call, @Nullable Response response) throws IOException {
        if (response == null || response.body() == null) {
            onFailure(call, new IOException("response.body() == null"));
            return;
        }
        String string = Objects.requireNonNull(response.body()).string();
        Class<T> t = getT();
        T t1 = JsonUtil.fromJson(t, string);
        onResponse(call, t1, string);
    }

    public abstract void onResponse(Call call, T resultBean, String resultString) throws IOException;
    public abstract void onFail(Call call, IOException e);

    @NotNull
    private Class<T> getT() {
        Type sType = getClass().getGenericSuperclass();
        Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
        return (Class<T>) (generics[0]);
    }

}
