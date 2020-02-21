package immortal.half.wu.idlefish.pagers.posted;


import org.dom4j.Element;

public interface ProcessPostedProductCallBack {

    boolean process(String productName);

    boolean process(String productName, int price);

    boolean process(String productName, int price, Element refresh);

    boolean process(String productName, int price, Element refresh, Element more);

    void over();

    void notFound();

}
