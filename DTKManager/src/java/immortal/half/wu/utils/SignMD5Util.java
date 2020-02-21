package immortal.half.wu.utils;

import immortal.half.wu.JsonUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class SignMD5Util {

    /**
     * 获取签名的util
     * @param params 请求参数
     * @param secretKey 密钥
     */
    @NotNull
    public static Map<String, String> getSignMap(@Nullable Object params, String secretKey, String appKey) {

        TreeMap<String, String> paramsSignMap = new TreeMap<>();
        if (params == null){
            return paramsSignMap;
        }

        try {

            TreeMap<String, String> paramsMap = JsonUtil.objectToMap(params);
            paramsMap.put("appKey", appKey);

            System.out.println(paramsMap);
            String signStr = getSignStr(paramsMap, secretKey);

            paramsSignMap.putAll(paramsMap);
            paramsSignMap.put("sign", signStr);

            return paramsSignMap;

        } catch (@NotNull IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return paramsSignMap;
    }


    /**
     * 获取签名的util
     * @param map 请求参数
     * @param secretKey 密钥
     */
    private static String getSignStr(TreeMap<String, String> map, String secretKey){

        if (map.size() == 0 ){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            sb.append("&").append(key).append("=").append(map.get(key));
        }
        sb.deleteCharAt(0);
        return sign(sb.toString(), secretKey);
    }

    private static String sign(String content, String key)
    {
        String signStr;
        signStr = content + "&key=" + key;
        //MD5加密后，字符串所有字符转换为大写
        return Objects.requireNonNull(MD5(signStr)).toUpperCase();
    }

    /**
     *
     * MD5加密算法
     *
     * @see [类、类#方法、类#成员]
     */
    private static String MD5(@NotNull String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

