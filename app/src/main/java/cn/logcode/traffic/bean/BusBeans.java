package cn.logcode.traffic.bean;

import java.util.List;

/**
 * Created by 24327 on 2018/4/27.
 */

public class BusBeans {

    @Override
    public String toString() {
        return "BusBeans{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    /**
     * code : 1
     * data : [{"id":1,"zhantai":"一号站台","bus":[{"name":"1号公交车","distance":552},{"name":"2号公交车","distance":808},{"name":"3号公交车","distance":470}]},{"id":2,"zhantai":"二号站台","bus":[{"name":"1号公交车","distance":315},{"name":"2号公交车","distance":424},{"name":"3号公交车","distance":213}]},{"id":3,"zhantai":"三号站台","bus":[{"name":"1号公交车","distance":869},{"name":"2号公交车","distance":439},{"name":"3号公交车","distance":1304}]}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", zhantai='" + zhantai + '\'' +
                    ", bus=" + bus +
                    '}';
        }

        /**
         * id : 1
         * zhantai : 一号站台
         * bus : [{"name":"1号公交车","distance":552},{"name":"2号公交车","distance":808},{"name":"3号公交车","distance":470}]
         */

        private int id;
        private String zhantai;
        private List<BusBean> bus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getZhantai() {
            return zhantai;
        }

        public void setZhantai(String zhantai) {
            this.zhantai = zhantai;
        }

        public List<BusBean> getBus() {
            return bus;
        }

        public void setBus(List<BusBean> bus) {
            this.bus = bus;
        }

        public static class BusBean {
            @Override
            public String toString() {
                return "BusBean{" +
                        "name='" + name + '\'' +
                        ", distance=" + distance +
                        '}';
            }

            /**
             * name : 1号公交车
             * distance : 552
             */

            private String name;
            private int distance;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }
        }
    }
}
