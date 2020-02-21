package immortal.half.wu.idlefish;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.FileUtils;
import immortal.half.wu.LogUtil;
import immortal.half.wu.XMLUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.idlefish.pagers.posted.ProcessPostedProductCallBack;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import org.dom4j.Element;
import org.dom4j.QName;

import java.util.ArrayList;
import java.util.List;

import static immortal.half.wu.idlefish.IdleFishModel.IDLE_FISH_PACKAGE_NAME;
import static immotal.half.wu.appManager.AppManagerUtil.TAG;

public class IdleFishUtil {

    private static String getIdleFishApkPath() {
        return IdleFishUtil.class.getResource("res/IdleFish.apk").getPath();
    }

    /**
     * 检测并安装咸鱼App
     */
    static void initFishIdleApp(@org.jetbrains.annotations.NotNull String deviceId) {
        ADBManager adb = ADBManager.getInstance();
        if (!adb.isInstallApp(deviceId, IDLE_FISH_PACKAGE_NAME)) {
            LogUtil.i(TAG, "为设备" + deviceId + "安装咸鱼App");
            adb.installApp(deviceId, getIdleFishApkPath(), IDLE_FISH_PACKAGE_NAME);
        }
    }

    public static void processPostedProduct(
            @org.jetbrains.annotations.NotNull @NotNull String uiXmlString,
            @NotNull DeviceInfoBean deviceId,
            @org.jetbrains.annotations.Nullable @Nullable ProcessPostedProductCallBack callBack) {

        if (callBack == null) {
            return;
        }

        try {

            Element recyclerView = XMLUtil.findElementByNodeKeyValue(
                    uiXmlString,
                    "class",
                    "android.support.v7.widget.RecyclerView"
            );

            if (recyclerView == null) {
                callBack.notFound();
                return;
            }

            List<Element> items = XMLUtil.findAllElementByTagName(
                    recyclerView,
                    "node"
            );

            ArrayList<String> objects = new ArrayList<>();
            objects.add("曝光");
            objects.add("游戏");
            objects.add("我的问答");
            objects.add("宝贝图片");
            objects.add("¥");
            objects.add("留言");
            objects.add("浏览");
            objects.add("划重点");
            objects.add("+");
            objects.add("降价");
            objects.add("编辑");
            objects.add("已擦亮");
            objects.add("刚刚擦亮");
            objects.add("去发布");
            objects.add("再发个宝贝");


            List<Element> elements = XMLUtil.removeElementByAttrTextWithNull(
                    items,
                    objects
            );

            if (elements.size() <= 1) {
                callBack.notFound();
                return;
            }

            Element firstElement = elements.get(0);

            try {
                Integer.valueOf(firstElement.attributeValue(QName.get("content-desc")));
                elements.remove(0);
            } catch (Exception ignored) {
            }


            String nameElement = "";
            int priceElement = 0;
            Element refreshElement = null, moreElement;

            for (Element element : elements) {

                String value = element.attributeValue(QName.get("content-desc"));

                if (value.equals("更多") && !FileUtils.isEmpty(nameElement) && priceElement != 0) {
                    moreElement = element;
                    if (callBack.process(nameElement, priceElement, refreshElement, moreElement)) {
                        callBack.over();
                        return;
                    }
                    nameElement = "";
                    priceElement = 0;
                    continue;
                }

                if (value.equals("擦亮") && !FileUtils.isEmpty(nameElement) && priceElement != 0) {
                    refreshElement = element;
                    if (callBack.process(nameElement, priceElement, refreshElement)) {
                        callBack.over();
                        return;
                    }
                    continue;
                }

                try {
                    priceElement = Integer.valueOf(value);
                    if (!FileUtils.isEmpty(nameElement) && callBack.process(nameElement, priceElement)) {
                        callBack.over();
                        return;
                    }
                    continue;
                } catch (Exception ignored) {
                }

                nameElement = value;
                if (callBack.process(nameElement)) {
                    callBack.over();
                    return;
                }

            }

            callBack.over();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
