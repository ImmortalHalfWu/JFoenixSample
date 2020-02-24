package wu.ui.models;

import com.sun.istack.internal.Nullable;
import immortal.half.wu.FileUtils;
import immortal.half.wu.JsonUtil;
import immortal.half.wu.LogUtil;
import wu.ui.models.beans.CacheIdleFishUserConfigBean;
import wu.ui.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private final CacheIdleFishUserConfigBean idleFishUserInfoBean;

    /**
     * 用户与商品映射表
     */
    private Map<CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean, List<CacheIdleFishUserConfigBean.CacheIdleFishProductBean>> userProductMap;

    private UserInfoManager() {
        // 初始化文件
        FileUtils.mkFile(PATH_CACHE_FILE);

        CacheIdleFishUserConfigBean userConfigTemp;
        try {
            userConfigTemp = JsonUtil.fromJson(CacheIdleFishUserConfigBean.class, FileUtils.readFile(PATH_CACHE_FILE));
        } catch (Exception e) {
            userConfigTemp = new CacheIdleFishUserConfigBean(new ArrayList<>());
        }
        // 读取本地文件转bean
        this.idleFishUserInfoBean = userConfigTemp;

//        List<CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean> userInfoBeans = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            List<CacheIdleFishUserConfigBean.CacheIdleFishProductBean> productBeans = new ArrayList<>();
//            for (int j = 0; j < i * 5 + 1; j++) {
//                productBeans.add(
//                    new CacheIdleFishUserConfigBean.CacheIdleFishProductBean(
//                        "url",
//                        "http://img14.360buyimg.com/imgzone/jfs/t1/91657/8/11629/198826/5e37e559E5ee5ce2a/cfb8d362d9d01210.jpg?imageMogr2/strip/format/jpg",
//                        "name" + i + j, "2020/02/01", (i+j) % 2 == 0 ? "发布成功" : "发布失败",
//                        "123.1", "123", "0.1", new ArrayList<>()
//                    )
//                );
//            }
//            userInfoBeans.add(new CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean(
//                "user" + i, productBeans
//            ));
//        }
//        this.idleFishUserInfoBean = new CacheIdleFishUserConfigBean(userInfoBeans);
    }

    List<CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean> getUserInfos() {
        return idleFishUserInfoBean == null ? new ArrayList<>() : idleFishUserInfoBean.getIdleFishUserInfoBeans();
    }

    /**
     * 添加一个新用户，如果缓存中没有，则插入
     * @param userName 新用户的用户名
     */
    public void addUser(String userName) {
        List<CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean> userInfoBeans =
                idleFishUserInfoBean.getIdleFishUserInfoBeans();

        ThreadUtil.runInWork(() -> {
            for (CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean userInfoBean : userInfoBeans) {
                if (userInfoBean.getUserName().equals(userName)) {
                    return;
                }
            }
            userInfoBeans.add(new CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean(
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
    public void addProduct(String userName, CacheIdleFishUserConfigBean.CacheIdleFishProductBean newProductBean) {
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
    public void removeProduct(String userName, CacheIdleFishUserConfigBean.CacheIdleFishProductBean newProductBean) {
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
    public void updataProduct(String userName, CacheIdleFishUserConfigBean.CacheIdleFishProductBean newProductBean) {
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
            CacheIdleFishUserConfigBean.CacheIdleFishProductBean newProductBean,
            ForEachCallback callBack) {

        List<CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean> userInfoBeans = idleFishUserInfoBean.getIdleFishUserInfoBeans();
        for (CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean userInfoBean : userInfoBeans){
            if (userInfoBean.getUserName().equals(userName)) {
                List<CacheIdleFishUserConfigBean.CacheIdleFishProductBean> productBeans = userInfoBean.getIdleFishProductModelBeans();
                for (CacheIdleFishUserConfigBean.CacheIdleFishProductBean productBean : productBeans) {
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
            List<CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean> userInfoBeans,
            @Nullable
                CacheIdleFishUserConfigBean.CacheIdleFishUserInfoBean userInfoBean,
            @Nullable
                List<CacheIdleFishUserConfigBean.CacheIdleFishProductBean> productBeans,
            @Nullable
                CacheIdleFishUserConfigBean.CacheIdleFishProductBean productBean);
    }
}
