package cn.logcode.traffic.bean;

import java.util.List;

public class WeatherBean {

    public int code;
    public List<DataBean> data;
    public static class DataBean {
        public int id;
        public int now;
        public String day;
    }
}
