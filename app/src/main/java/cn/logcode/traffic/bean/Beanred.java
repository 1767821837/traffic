package cn.logcode.traffic.bean;

import java.util.List;

public class Beanred
{

    /**
     * code : 1
     * data : [{"id":1,"red":13,"green":18,"yellow":10},{"id":2,"red":9,"green":6,"yellow":11},{"id":3,"red":10,"green":9,"yellow":5},{"id":4,"red":16,"green":14,"yellow":14},{"id":5,"red":19,"green":4,"yellow":12}]
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
        /**
         * id : 1
         * red : 13
         * green : 18
         * yellow : 10
         */

        private int id;
        private int red;
        private int green;
        private int yellow;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRed() {
            return red;
        }

        public void setRed(int red) {
            this.red = red;
        }

        public int getGreen() {
            return green;
        }

        public void setGreen(int green) {
            this.green = green;
        }

        public int getYellow() {
            return yellow;
        }

        public void setYellow(int yellow) {
            this.yellow = yellow;
        }
    }
}
