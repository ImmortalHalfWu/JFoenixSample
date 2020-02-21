package immortal.half.wu;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.dom4j.*;

import java.awt.*;
import java.util.List;
import java.util.*;

public class XMLUtil {


    private static Element findRootElement(@NotNull String xml) throws DocumentException {
        return DocumentHelper.parseText(xml).getRootElement();
    }

    interface LoopXMLNodeCallBack {
        /**
         * @param element 遍历中，当前节点
         * @return 是否结束， true则停止遍历
         */
        boolean element(int index, Element element);
    }

    interface LoopItemListener {
        /**
         * @param elementIndex 元素下表
         * @param nodeName 元素名称
         * @param paramKey 元素下的属性名
         * @param paramValue 元素下属性值
         * @return 是否停止遍历
         */
        boolean itemInfo(int elementIndex, String nodeName, Element element, String paramKey, String paramValue);
    }


    /**
     * 获取文档中的所有指定元素的所有属性
     * @param xmlString xml字符串
     * @param listener 接受回调
     * @throws DocumentException 文档解析异常
     */
    public static void loopXMLAllAttr(@NotNull String xmlString, @NotNull LoopItemListener listener) throws DocumentException {

        loopXMLAllElement(0, findRootElement(xmlString), (index, element) -> {

            List<Attribute> attributes = element.attributes();
            for (Attribute attr :
                    attributes) {
                String name = attr.getName();
                String value = attr.getValue();
                if (listener.itemInfo(index, element.getName(), element, name, value)) {
                    return true;
                }
            }

            return false;
        });

    }



    /**
     * 获取文档中的所有指定元素
     *
     * @param rootElement 根元素
     */
    private static void loopXMLAllElement(
            int elementIndex,
            @NotNull Element rootElement,
            LoopXMLNodeCallBack callBack
    ) {

        if (callBack.element(elementIndex, rootElement)) {
            return;
        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement = rootElement.elements();//所有一级子节点的list
        for (Element e : listElement) {//遍历所有一级子节点
            loopXMLAllElement(++elementIndex, e, callBack);//递归
        }

    }


    /**
     * 获取文档中的所有指定元素
     *
     * @param xmlString xml
     * @return 文档中的所有指定元素
     */
    @NotNull
    public static Map<String, Point> findAllPointByAttrKeyValue(
            @NotNull String xmlString,
            @NotNull Map<String, Map<String, String>> filterMap) throws DocumentException {

        Map<String, Point> resultMap = new HashMap<>();

        loopXMLAllElement(
                0,
                findRootElement(xmlString),
                new LoopXMLNodeCallBack() {
                    @Override
                    public boolean element(int index, @NotNull Element element) {

                        //当前节点的名称、文本内容和属性
                        Set<String> resultKeys = filterMap.keySet();

                        for (String resultKey :
                                resultKeys) {

                            if (resultMap.get(resultKey) != null) {
                                continue;
                            }

                            Map<String, String> filterMaps = filterMap.get(resultKey);

                            for (Iterator<String> iterator = filterMaps.keySet().iterator(); ; ) {

                                String filterKey = iterator.next();

                                Attribute attribute = element.attribute(QName.get(filterKey));
                                if (attribute == null || !attribute.getValue().startsWith(filterMaps.get(filterKey))) {
                                    break;
                                }

//                                LogUtil.i(AppManagerUtil.TAG, "找到" + filterKey + " = " + attribute.getValue());
                                if (!iterator.hasNext()) {
                                    resultMap.put(resultKey, getElementBoundsCenter(element));
                                    if (resultMap.size() == filterMap.size()) {
//                                        LogUtil.i(AppManagerUtil.TAG, "完成查找");
                                        return true;
                                    }
                                    break;
                                }

                            }
                        }

                        return resultMap.size() == filterMap.size();
                    }
                }
        );

        return resultMap;
    }


    /**
     * 获取文档中的所有指定元素
     */
    @NotNull
    public static List<Point> findAllPointByAttrKeyValue(
            @NotNull String xml,
            String key,
            String value) {

        List<Point> resultPoints = new ArrayList<>();
        try {
            loopXMLAllElement(0, findRootElement(xml), new LoopXMLNodeCallBack() {
                @Override
                public boolean element(int index, @NotNull Element element) {
                    Attribute attribute = element.attribute(QName.get(key));

                    if (attribute != null && attribute.getValue().equals(value)) {
                        resultPoints.add(getElementBoundsCenter(element));
                    }

                    return false;
                }
            });
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return resultPoints;
    }


    @NotNull
    public static Map<String, String> getTextByUIXML(
            @NotNull String xmlString,
            @NotNull Map<String, Map<String, String>> pointFilterBean) {

        if (!FileUtils.isEmpty(xmlString) && !pointFilterBean.isEmpty()) {

            try {

                return XMLUtil.findTextByAttrKeyValues(
                        XMLUtil.findRootElement(xmlString),
                        new HashMap<>(pointFilterBean.size()),
                        pointFilterBean
                );

            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }

        return new HashMap<>(0);
    }


    /**
     * 获取文档中的所有指定元素
     */
    @NotNull
    private static Map<String, String> findTextByAttrKeyValues(
            @NotNull Element rootElement,
            @NotNull Map<String, String> resultMap,
            @NotNull Map<String, Map<String, String>> filterMap) {

        loopXMLAllElement(0, rootElement, new LoopXMLNodeCallBack() {
            @Override
            public boolean element(int index, @NotNull Element element) {


                //当前节点的名称、文本内容和属性
                Set<String> resultKeys = filterMap.keySet();

                for (String resultKey :
                        resultKeys) {

                    if (resultMap.get(resultKey) != null) {
                        continue;
                    }

                    Map<String, String> filterMaps = filterMap.get(resultKey);

                    for (Iterator<String> iterator = filterMaps.keySet().iterator(); ; ) {

                        String filterKey = iterator.next();

                        Attribute attribute = element.attribute(QName.get(filterKey));

                        if (attribute == null || !attribute.getValue().startsWith(filterMaps.get(filterKey))) {
                            break;
                        }


                        if (!iterator.hasNext()) {
                            resultMap.put(resultKey, element.attributeValue("text"));
                            if (resultMap.size() == filterMap.size()) {
                                return true;
                            }
                            break;
                        }

                    }
                }

                return resultMap.size() == filterMap.size();
            }
        });

        return resultMap;
    }

    /**
     * @param xml 根元素
     * @param key         查找第一个，attr中包含指定键值对的元素
     * @param value       键值对
     * @return 查找第一个，attr中包含指定键值对的元素
     */
    public static Element findElementByNodeKeyValue(@NotNull String xml, String key, String value) {

        final Element[] result = new Element[1];

        try {
            loopXMLAllElement(0, findRootElement(xml), (index, element) -> {

                Attribute attribute = element.attribute(key);
                if (attribute != null && attribute.getValue() != null && attribute.getValue().equals(value)) {
                    result[0] =  element;
                    return true;
                }
                return false;
            });
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return result[0];
    }


    /**
     * 获取文档中的所有指定元素
     *
     * @param rootElement 根元素
     * @param tagName     指定元素名称
     * @return 文档中的所有指定元素
     */
    @NotNull
    public static List<Element> findAllElementByTagName(@NotNull Element rootElement, String tagName) {

        List<Element> elements = new ArrayList<>();
        loopXMLAllElement(0, rootElement, new LoopXMLNodeCallBack() {
            @Override
            public boolean element(int index, @NotNull Element element) {
                if (element.getName().equals(tagName)) {
                    elements.add(element);
                }
                return false;
            }
        });
        return elements;
    }

    /**
     * @param nodes     指定元素列表
     * @param filterMap 指定文本内容
     * @return 删除元素列表中，content-desc 或 text 包含 指定string的元素 或者 为空
     */
    @NotNull
    public static List<Element> removeElementByAttrTextWithNull(List<Element> nodes, @NotNull List<String> filterMap) {

        List<Element> removeElement = new ArrayList<>();

        String string;
        for (Element element : nodes) {
            if (!FileUtils.isEmpty(string = element.attribute("text").getValue()) || !FileUtils.isEmpty(string = element.attribute("content-desc").getValue())) {

                for (String item : filterMap) {
                    if (string.contains(item)) {
                        removeElement.add(element);
                    }
                }

            } else {
                removeElement.add(element);
            }
        }
        nodes.removeAll(removeElement);
        return nodes;
    }

    /**
     * 获取文档中的所有指定元素
     */
    public static @Nullable Point findPointByAttrKeyValueEndWith(
            @NotNull String xml,
            String key,
            @NotNull String value) {

        final Point[] point = new Point[1];

        try {
            loopXMLAllElement(0, findRootElement(xml), new LoopXMLNodeCallBack() {
                @Override
                public boolean element(int index, @NotNull Element element) {
                    Attribute attribute = element.attribute(QName.get(key));
                    if (attribute != null && attribute.getValue().endsWith(value)) {
                        Point elementBoundsCenter = getElementBoundsCenter(element);
                        if (elementBoundsCenter.x != 0 && elementBoundsCenter.y != 0) {
                            point[0] = elementBoundsCenter;
                            return true;
                        }
                    }
                    return false;
                }
            });
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return point[0];
    }








    /**
     * @param element 指定元素
     * @return bounds属性是否为null
     */
    public static boolean attrBoundsIsNull(Element element) {
        Attribute bounds = element.attribute("bounds");
        return bounds == null ||
                bounds.getValue().length() == 0 ||
                bounds.getValue().equals("[0,0][0,0]");
    }


    /**
     * @return 根据四边，计算中心点
     */
    public static Point getCenterPoint(int left, int top, int right, int bottom) {
        int x = left + (right - left) / 2;
        int y = top + (bottom - top) / 2;
        return new Point(x, y);
    }


    /**
     * @param element 指定元素
     * @return 获取指定元素bounds属性记录的中心位置
     */
    public static Point getElementBoundsCenter(@NotNull Element element) {
        if (attrBoundsIsNull(element)) {
            return new Point(0, 0);
        }
        Point elementLeftTopPoint = getElementLeftTopPoint(element);
        Point elementRightBottomPoint = getElementRightBottomPoint(element);

        return getCenterPoint(
                elementLeftTopPoint.x,
                elementLeftTopPoint.y,
                elementRightBottomPoint.x,
                elementRightBottomPoint.y);
    }



    public static Point getElementLeftTopPoint(@NotNull Element element) {
        if (attrBoundsIsNull(element)) {
            return new Point(0, 0);
        }

        String[] lifeTop = getElementBoundsSplit(element)[0].split(",");
        int left = Integer.parseInt(lifeTop[0]);
        int top = Integer.parseInt(lifeTop[1]);

        return new Point(left, top);
    }

    public static Point getElementRightBottomPoint(@NotNull Element element) {
        if (attrBoundsIsNull(element)) {
            return new Point(0, 0);
        }

        String[] rightBottom = getElementBoundsSplit(element)[1].split(",");
        int right = Integer.parseInt(rightBottom[0]);
        int bottom = Integer.parseInt(rightBottom[1]);

        return new Point(right, bottom);

    }

    @NotNull
    private static String[] getElementBoundsSplit(Element element) {

        Attribute bounds = element.attribute("bounds");
        String value = bounds.getValue();
        value = value.replace("][", "@@");
        String[] split = value.split("@@");
        split[0] = split[0].replace("[", "");
        split[1] = split[1].replace("]", "");
        return split;
    }

}
