package immortal.half.wu.beans.results;

import java.util.List;

public class JsonProductUserSaveBean {

    /**
     * data : {"api_type":"领券优惠v1.4","update_time":"2019/10/10 19:01","total_num":1,"api_content":"网站专用API数据接口","result":[{"GoodsID":"543405404720","Title":"嘻螺会柳州正宗螺蛳粉广西特产螺狮粉5包邮螺丝粉速食米线酸辣粉","D_title":"嘻螺会广西柳州特产正宗螺蛳粉5包装","Pic":"https://img.alicdn.com/imgextra/i4/3023094052/O1CN01PlBJ1N1fnrPfH0DGu_!!3023094052.png","Cid":"6","Org_Price":"29.90","Price":24.9,"IsTmall":"1","Sales_num":"143101","Dsr":"4.8","SellerID":"3023094052","Commission_jihua":"30.00","Commission_queqiao":"0.00","Jihua_link":"","Introduce":"精选食材，配料齐全，酸辣鲜爽，Q弹劲道，爽滑入味，健康营养，舌尖上的中国特别推荐美食，300g*5袋","Quan_id":"55cc8ff4119e45dbb26e46cab65c1eab","Quan_price":"5.00","Quan_time":"2019-10-13 23:59:59","Quan_surplus":"53000","Quan_receive":"47000","Quan_condition":"29","Quan_link":"https://uland.taobao.com/quan/detail?sellerId=3023094052&activityId=55cc8ff4119e45dbb26e46cab65c1eab","Quan_m_link":null,"Yongjin_type":"3","ali_click":"https://detail.tmall.com/item.htm?id=543405404720"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * api_type : 领券优惠v1.4
         * update_time : 2019/10/10 19:01
         * total_num : 1
         * api_content : 网站专用API数据接口
         * result : [{"GoodsID":"543405404720","Title":"嘻螺会柳州正宗螺蛳粉广西特产螺狮粉5包邮螺丝粉速食米线酸辣粉","D_title":"嘻螺会广西柳州特产正宗螺蛳粉5包装","Pic":"https://img.alicdn.com/imgextra/i4/3023094052/O1CN01PlBJ1N1fnrPfH0DGu_!!3023094052.png","Cid":"6","Org_Price":"29.90","Price":24.9,"IsTmall":"1","Sales_num":"143101","Dsr":"4.8","SellerID":"3023094052","Commission_jihua":"30.00","Commission_queqiao":"0.00","Jihua_link":"","Introduce":"精选食材，配料齐全，酸辣鲜爽，Q弹劲道，爽滑入味，健康营养，舌尖上的中国特别推荐美食，300g*5袋","Quan_id":"55cc8ff4119e45dbb26e46cab65c1eab","Quan_price":"5.00","Quan_time":"2019-10-13 23:59:59","Quan_surplus":"53000","Quan_receive":"47000","Quan_condition":"29","Quan_link":"https://uland.taobao.com/quan/detail?sellerId=3023094052&activityId=55cc8ff4119e45dbb26e46cab65c1eab","Quan_m_link":null,"Yongjin_type":"3","ali_click":"https://detail.tmall.com/item.htm?id=543405404720"}]
         */

        private String api_type;
        private String update_time;
        private int total_num;
        private String api_content;
        private List<ResultBean> result;

        public String getApi_type() {
            return api_type;
        }

        public void setApi_type(String api_type) {
            this.api_type = api_type;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public String getApi_content() {
            return api_content;
        }

        public void setApi_content(String api_content) {
            this.api_content = api_content;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * GoodsID : 543405404720
             * Title : 嘻螺会柳州正宗螺蛳粉广西特产螺狮粉5包邮螺丝粉速食米线酸辣粉
             * D_title : 嘻螺会广西柳州特产正宗螺蛳粉5包装
             * Pic : https://img.alicdn.com/imgextra/i4/3023094052/O1CN01PlBJ1N1fnrPfH0DGu_!!3023094052.png
             * Cid : 6
             * Org_Price : 29.90
             * Price : 24.9
             * IsTmall : 1
             * Sales_num : 143101
             * Dsr : 4.8
             * SellerID : 3023094052
             * Commission_jihua : 30.00
             * Commission_queqiao : 0.00
             * Jihua_link :
             * Introduce : 精选食材，配料齐全，酸辣鲜爽，Q弹劲道，爽滑入味，健康营养，舌尖上的中国特别推荐美食，300g*5袋
             * Quan_id : 55cc8ff4119e45dbb26e46cab65c1eab
             * Quan_price : 5.00
             * Quan_time : 2019-10-13 23:59:59
             * Quan_surplus : 53000
             * Quan_receive : 47000
             * Quan_condition : 29
             * Quan_link : https://uland.taobao.com/quan/detail?sellerId=3023094052&activityId=55cc8ff4119e45dbb26e46cab65c1eab
             * Quan_m_link : null
             * Yongjin_type : 3
             * ali_click : https://detail.tmall.com/item.htm?id=543405404720
             */

            private String GoodsID;
            private String Title;
            private String D_title;
            private String Pic;
            private String Cid;
            private String Org_Price;
            private double Price;
            private String IsTmall;
            private String Sales_num;
            private String Dsr;
            private String SellerID;
            private String Commission_jihua;
            private String Commission_queqiao;
            private String Jihua_link;
            private String Introduce;
            private String Quan_id;
            private String Quan_price;
            private String Quan_time;
            private String Quan_surplus;
            private String Quan_receive;
            private String Quan_condition;
            private String Quan_link;
            private Object Quan_m_link;
            private String Yongjin_type;
            private String ali_click;

            public String getGoodsID() {
                return GoodsID;
            }

            public void setGoodsID(String GoodsID) {
                this.GoodsID = GoodsID;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getD_title() {
                return D_title;
            }

            public void setD_title(String D_title) {
                this.D_title = D_title;
            }

            public String getPic() {
                return Pic;
            }

            public void setPic(String Pic) {
                this.Pic = Pic;
            }

            public String getCid() {
                return Cid;
            }

            public void setCid(String Cid) {
                this.Cid = Cid;
            }

            public String getOrg_Price() {
                return Org_Price;
            }

            public void setOrg_Price(String Org_Price) {
                this.Org_Price = Org_Price;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(double Price) {
                this.Price = Price;
            }

            public String getIsTmall() {
                return IsTmall;
            }

            public void setIsTmall(String IsTmall) {
                this.IsTmall = IsTmall;
            }

            public String getSales_num() {
                return Sales_num;
            }

            public void setSales_num(String Sales_num) {
                this.Sales_num = Sales_num;
            }

            public String getDsr() {
                return Dsr;
            }

            public void setDsr(String Dsr) {
                this.Dsr = Dsr;
            }

            public String getSellerID() {
                return SellerID;
            }

            public void setSellerID(String SellerID) {
                this.SellerID = SellerID;
            }

            public String getCommission_jihua() {
                return Commission_jihua;
            }

            public void setCommission_jihua(String Commission_jihua) {
                this.Commission_jihua = Commission_jihua;
            }

            public String getCommission_queqiao() {
                return Commission_queqiao;
            }

            public void setCommission_queqiao(String Commission_queqiao) {
                this.Commission_queqiao = Commission_queqiao;
            }

            public String getJihua_link() {
                return Jihua_link;
            }

            public void setJihua_link(String Jihua_link) {
                this.Jihua_link = Jihua_link;
            }

            public String getIntroduce() {
                return Introduce;
            }

            public void setIntroduce(String Introduce) {
                this.Introduce = Introduce;
            }

            public String getQuan_id() {
                return Quan_id;
            }

            public void setQuan_id(String Quan_id) {
                this.Quan_id = Quan_id;
            }

            public String getQuan_price() {
                return Quan_price;
            }

            public void setQuan_price(String Quan_price) {
                this.Quan_price = Quan_price;
            }

            public String getQuan_time() {
                return Quan_time;
            }

            public void setQuan_time(String Quan_time) {
                this.Quan_time = Quan_time;
            }

            public String getQuan_surplus() {
                return Quan_surplus;
            }

            public void setQuan_surplus(String Quan_surplus) {
                this.Quan_surplus = Quan_surplus;
            }

            public String getQuan_receive() {
                return Quan_receive;
            }

            public void setQuan_receive(String Quan_receive) {
                this.Quan_receive = Quan_receive;
            }

            public String getQuan_condition() {
                return Quan_condition;
            }

            public void setQuan_condition(String Quan_condition) {
                this.Quan_condition = Quan_condition;
            }

            public String getQuan_link() {
                return Quan_link;
            }

            public void setQuan_link(String Quan_link) {
                this.Quan_link = Quan_link;
            }

            public Object getQuan_m_link() {
                return Quan_m_link;
            }

            public void setQuan_m_link(Object Quan_m_link) {
                this.Quan_m_link = Quan_m_link;
            }

            public String getYongjin_type() {
                return Yongjin_type;
            }

            public void setYongjin_type(String Yongjin_type) {
                this.Yongjin_type = Yongjin_type;
            }

            public String getAli_click() {
                return ali_click;
            }

            public void setAli_click(String ali_click) {
                this.ali_click = ali_click;
            }
        }
    }

}
