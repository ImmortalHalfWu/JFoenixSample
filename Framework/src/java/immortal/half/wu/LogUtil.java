package immortal.half.wu;

import org.apache.log4j.Logger;

public class LogUtil {

    private static LogUtil logUtil;

    private Logger logger;

    private LogUtil() {
        this.logger = Logger.getRootLogger();
    }

    public static LogUtil init() {
        if (logUtil == null) {
            synchronized (LogUtil.class) {
                if (logUtil == null) {
                    logUtil = new LogUtil();
                }
            }
        }
        return logUtil;
    }

    private void info(Object object) {
        logger.info(object);
    }

    private void warn(Object object) {
        logger.warn(object);
    }

    private void debug(Object object) {
        logger.debug(object);
    }

    private void err(Object object) {
        logger.error(object);
    }

    private void err(Object object, Throwable throwable) {
        logger.error(object, throwable);
    }

    public static void i(String tab, Object object) {
        LogUtil.init().info(tab + ": " + object);
    }

    public static void e(String tab, Object object) {
        LogUtil.init().err(tab + ": " + object);
    }

    public static void e(String tab, Object object, Throwable throwable) {
        LogUtil.init().err((tab + ": " + object), throwable);
    }

    public static void w(String tab, Object object) {
        LogUtil.init().warn(tab + ": " + object);
    }

    public static void d(String tab, Object object) {
        LogUtil.init().debug(tab + ": " + object);
    }


}
