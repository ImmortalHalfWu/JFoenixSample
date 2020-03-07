package immortal.half.wu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.Map;
import java.util.TreeMap;

public class JsonUtil {

    public static <T> T fromJson(@NotNull Class<T> tClass, String jsonString) {

        try {
            return getGson().fromJson(jsonString, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return tClass.newInstance();
        } catch (@NotNull InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T fromJson(String jsonString, TypeToken<T> token) {
        try {
            return getGson().fromJson(jsonString, token.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return (T)token.getRawType().newInstance();
        } catch (@NotNull InstantiationException | ClassCastException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String toJson(Object o) {
        return getGson().toJson(o);
    }


    @Nullable
    public static Object mapToObject(@Nullable Map<String, Object> map, @NotNull Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

//    public static Map<String, String> objectToMap(Object obj) throws Exception {
//        if(obj == null){
//            return null;
//        }
//
//        Map<String, String> map = new HashMap<>();
//
//        Field[] declaredFields = obj.getClass().getDeclaredFields();
//        for (Field field : declaredFields) {
//            field.setAccessible(true);
//            String string = field.get(obj).toString();
//            if (string != null && string.length() > 0) {
//                map.put(field.getName(), field.get(obj).toString());
//            }
//        }
//
//        return map;
//    }
    
    @NotNull
    public static TreeMap<String, String> objectToMap(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {

        Class type = bean.getClass();
        TreeMap<String, String> returnMap = new TreeMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null && result.toString().length() > 0) {
                    returnMap.put(propertyName, result.toString());
                }
//                else {
//                    returnMap.put(propertyName, "");
//                }
            }
        }
        return returnMap;
    }


    private static Gson mGson;
    private static Gson getGson() {
        if (mGson == null) {
            synchronized (JsonUtil.class) {
                if (mGson == null) {
                    mGson = new Gson();
                }
            }
        }
        return mGson;
    }

}
