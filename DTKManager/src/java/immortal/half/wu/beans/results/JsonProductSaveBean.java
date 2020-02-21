package immortal.half.wu.beans.results;

import java.util.List;

public class JsonProductSaveBean {

    /**
     * time : 1555641972572
     * code : 0
     * msg : 成功
     * data : {"list":[{"id":18972318,"goodsId":"562800541176","trailerGoods":0}],"totalNum":1,"pageId":"9100d736bb7868c4"}
     */

    private long time;
    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":18972318,"goodsId":"562800541176","trailerGoods":0}]
         * totalNum : 1
         * pageId : 9100d736bb7868c4
         */

        private int totalNum;
        private String pageId;
        private List<ListBean> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getPageId() {
            return pageId;
        }

        public void setPageId(String pageId) {
            this.pageId = pageId;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 18972318
             * goodsId : 562800541176
             * trailerGoods : 0
             */

            private int id;
            private String goodsId;
            private int trailerGoods;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public int getTrailerGoods() {
                return trailerGoods;
            }

            public void setTrailerGoods(int trailerGoods) {
                this.trailerGoods = trailerGoods;
            }
        }
    }
}
