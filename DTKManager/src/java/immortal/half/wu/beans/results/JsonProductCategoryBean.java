package immortal.half.wu.beans.results;

import java.util.List;

public class JsonProductCategoryBean {

    /**
     * time : 1558949325133
     * code : 0
     * msg : 成功
     * data : [{"cid":1,"cname":"女装","cpic":"https://img.alicdn.com/imgextra/i1/2053469401/TB2oX82HL9TBuNjy0FcXXbeiFXa-2053469401.png","subcategories":[{"subcid":105145,"subcname":"连衣裙","scpic":"https://img.alicdn.com/imgextra/i3/2053469401/O1CN01Zetz4W2JJhwGC7oaD-2053469401.png"}]}]
     */

    private long time;
    private int code;
    private String msg;
    private List<DataBean> data;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 1
         * cname : 女装
         * cpic : https://img.alicdn.com/imgextra/i1/2053469401/TB2oX82HL9TBuNjy0FcXXbeiFXa-2053469401.png
         * subcategories : [{"subcid":105145,"subcname":"连衣裙","scpic":"https://img.alicdn.com/imgextra/i3/2053469401/O1CN01Zetz4W2JJhwGC7oaD-2053469401.png"}]
         */

        private int cid;
        private String cname;
        private String cpic;
        private List<SubcategoriesBean> subcategories;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCpic() {
            return cpic;
        }

        public void setCpic(String cpic) {
            this.cpic = cpic;
        }

        public List<SubcategoriesBean> getSubcategories() {
            return subcategories;
        }

        public void setSubcategories(List<SubcategoriesBean> subcategories) {
            this.subcategories = subcategories;
        }

        public static class SubcategoriesBean {
            /**
             * subcid : 105145
             * subcname : 连衣裙
             * scpic : https://img.alicdn.com/imgextra/i3/2053469401/O1CN01Zetz4W2JJhwGC7oaD-2053469401.png
             */

            private int subcid;
            private String subcname;
            private String scpic;

            public int getSubcid() {
                return subcid;
            }

            public void setSubcid(int subcid) {
                this.subcid = subcid;
            }

            public String getSubcname() {
                return subcname;
            }

            public void setSubcname(String subcname) {
                this.subcname = subcname;
            }

            public String getScpic() {
                return scpic;
            }

            public void setScpic(String scpic) {
                this.scpic = scpic;
            }
        }
    }
}
