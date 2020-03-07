package wu.ui.models;

import com.sun.istack.internal.Nullable;
import immortal.half.wu.FileUtils;
import immortal.half.wu.JsonUtil;
import immortal.half.wu.LogUtil;
import wu.ui.models.beans.CacheIdleFishUserInfoBean;
import wu.ui.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 闲鱼用户数据缓存工具类
 */
class UserInfoManager {

    private static UserInfoManager instance;

    static UserInfoManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new UserInfoManager();
                }
            }
        }
        return instance;
    }

    private static final String TAG = "UserInfoManager";
    /**
     * 缓存文件名
     */
    private final static String PATH_CACHE_FILE = FileUtils.DIR_PATH_JSON + "UserInfo.json";
    /**
     * 缓存bean
     */
    private final List<CacheIdleFishUserInfoBean> idleFishUserInfoBean;

    private UserInfoManager() {
        // 初始化文件
        FileUtils.mkFile(PATH_CACHE_FILE);
        // 读取本地文件转bean
//        this.idleFishUserInfoBean = userConfigTemp;

        List<CacheIdleFishUserInfoBean> userInfoBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<CacheIdleFishUserInfoBean.CacheIdleFishProductBean> productBeans = new ArrayList<>();
            for (int j = 0; j < i * 5 + 1; j++) {
                productBeans.add(
                    new CacheIdleFishUserInfoBean.CacheIdleFishProductBean(
                        "url",
                        "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg",
                        "name" + i + j, "2020/02/01", (i+j) % 2 == 0 ? "发布成功" : "发布失败",
                        "123.1", "123", "0.1", new ArrayList<>()
                    )
                );
            }
            userInfoBeans.add(new CacheIdleFishUserInfoBean(
                "user" + i, productBeans
            ));
        }
        this.idleFishUserInfoBean = userInfoBeans;
    }

    List<CacheIdleFishUserInfoBean> getUserInfos() {
        return idleFishUserInfoBean;
    }

    /**
     * 添加一个新用户，如果缓存中没有，则插入
     * @param userName 新用户的用户名
     */
    public void addUser(String userName) {

        ThreadUtil.runInWork(() -> {
            for (CacheIdleFishUserInfoBean userInfoBean : idleFishUserInfoBean) {
                if (userInfoBean.getUserName().equals(userName)) {
                    return;
                }
            }
            idleFishUserInfoBean.add(new CacheIdleFishUserInfoBean(
                    userName, new ArrayList<>()
            ));
            saveConfigFile.run();
        });

    }

    /**
     * 像指定用户插入指定商品数据
     * @param userName 指定用户
     * @param newProductBean 指定商品数据
     */
    public void addProduct(String userName, CacheIdleFishUserInfoBean.CacheIdleFishProductBean newProductBean) {
        ThreadUtil.runInMain(() -> {
            findProductBean(userName, newProductBean, (isFind, userInfoBeans, userInfoBean, productBeans, productBean) -> {
                if (productBean == null) {
                    productBeans.add(newProductBean);
                }
            });
            saveConfigFile.run();
        });
    }

    /**
     * 删除指定用户下指定商品数据
     * @param userName 指定用户
     * @param newProductBean 指定商品数据
     */
    public void removeProduct(String userName, CacheIdleFishUserInfoBean.CacheIdleFishProductBean newProductBean) {
        ThreadUtil.runInMain(() -> {

            findProductBean(userName, newProductBean, (isFind, userInfoBeans, userInfoBean, productBeans, productBean) -> {
                if (productBean != null) {
                    productBeans.remove(productBean);
                }
            });
            saveConfigFile.run();

        });
    }

    /**
     * 更新指定用户下指定商品数据
     * @param userName 指定用户
     * @param newProductBean 指定商品数据
     */
    public void updataProduct(String userName, CacheIdleFishUserInfoBean.CacheIdleFishProductBean newProductBean) {
        ThreadUtil.runInWork(() -> {
            findProductBean(userName, newProductBean, (isFind, userInfoBeans, userInfoBean, productBeans, productBean) -> {
                if (productBean != null) {
                    int index = productBeans.indexOf(productBean);
                    productBeans.remove(index);
                    productBeans.add(index, newProductBean);
                }
            });
            saveConfigFile.run();
        });
    }

    /**
     * 遍历缓存数据， 查找指定用户下指定商品数据bean
     */
    private void findProductBean(
            String userName,
            CacheIdleFishUserInfoBean.CacheIdleFishProductBean newProductBean,
            ForEachCallback callBack) {

        List<CacheIdleFishUserInfoBean> userInfoBeans = idleFishUserInfoBean;
        for (CacheIdleFishUserInfoBean userInfoBean : userInfoBeans){
            if (userInfoBean.getUserName().equals(userName)) {
                List<CacheIdleFishUserInfoBean.CacheIdleFishProductBean> productBeans = userInfoBean.getIdleFishProductModelBeans();
                for (CacheIdleFishUserInfoBean.CacheIdleFishProductBean productBean : productBeans) {
                    if (productBean.getProductUrl().equals(newProductBean.getProductUrl())) {
                        callBack.accept(
                                true,
                                userInfoBeans,
                                userInfoBean,
                                productBeans,
                                productBean
                        );
                        return;
                    }
                }

                callBack.accept(
                        false,
                        userInfoBeans,
                        userInfoBean,
                        productBeans,
                        null
                );
                return;
            }
        }

        callBack.accept(
                false,
                userInfoBeans,
                null,
                null,
                null
        );
    }

    /**
     * 将数据同步到本地
     */
    private volatile Runnable saveConfigFile = new Runnable() {
        @Override
        public void run() {
            try {
                String json = JsonUtil.toJson(idleFishUserInfoBean);
                FileUtils.writeText(PATH_CACHE_FILE, json, false);
            } catch (Exception e) {
                LogUtil.e(TAG, "用户配置文件保存异常：", e);
            }
        }
    };

    private interface ForEachCallback {
        void accept(
            boolean isFind,
            List<CacheIdleFishUserInfoBean> userInfoBeans,
            @Nullable
                CacheIdleFishUserInfoBean userInfoBean,
            @Nullable
                List<CacheIdleFishUserInfoBean.CacheIdleFishProductBean> productBeans,
            @Nullable
                CacheIdleFishUserInfoBean.CacheIdleFishProductBean productBean);
    }
}
