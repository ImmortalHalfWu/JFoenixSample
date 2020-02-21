package wu.ui.utils;

import com.google.common.eventbus.EventBus;


/**
 * EventBus工具类
 */
public class MEventBus {

    private static EventBus defaultEventBus;

    private static EventBus get() {
        if (defaultEventBus == null) {
            synchronized (MEventBus.class) {
                if (defaultEventBus == null) {
                    defaultEventBus = new EventBus();
                }
            }
        }
        return defaultEventBus;
    }

    public static void post(Object event) {
        get().post(event);
    }

    public static void register(Object sub) {
        get().register(sub);
    }

    public static void unRegister(Object sub) {
        get().unregister(sub);
    }

}
