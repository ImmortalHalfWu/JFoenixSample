package immortal.half.wu.http;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MHttpClient {

    private static MHttpClient instance;

    public static MHttpClient init(String baseUrl) {
        if (instance == null) {
            synchronized (MHttpClient.class) {
                if (instance == null) {
                    instance = new MHttpClient(baseUrl);
                }
            }
        }
        return instance;
    }


    private final String BASE_URL;
    @NotNull
    private final OkHttpClient okHttpClient;

    private MHttpClient(String baseUrl) {

        BASE_URL = baseUrl;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);//设置出现错误进行重新连接。
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(httpLoggingInterceptor);
        okHttpClient = builder.build();

    }

    /**
     * okHttp get请求异步
     * @param actionUrl  接口地址
     * @param paramsMap   请求参数
     */
    public void sendGetWithBaseUrl(String actionUrl, @NotNull Map<String, String> paramsMap, Callback callback) {
        try {
            sendGet(BASE_URL + actionUrl, paramsMap, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * okHttp get请求异步
     * @param actionUrl  接口地址
     * @param paramsMap   请求参数
     */
    private void sendGet(String actionUrl, @NotNull Map<String, String> paramsMap, Callback callback) {
        StringBuilder tempParams = new StringBuilder();
        try {
            // 添加通用请求参数
            //处理参数
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                //对参数进行URLEncoder
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
            sendGet(requestUrl, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * okHttp get请求异步
     * @param requestUrl  接口地址
     */
    public void sendGet(@NotNull String requestUrl, Callback callback) {
        try {
            //创建一个请求
            Request request = new Request.Builder().url(requestUrl).build();
            //创建一个Call
            final Call call = okHttpClient.newCall(request);
            //执行请求
            call.enqueue(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
