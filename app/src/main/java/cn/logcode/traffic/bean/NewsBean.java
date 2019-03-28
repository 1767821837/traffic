package cn.logcode.traffic.bean;

import java.util.List;

public class NewsBean  {
    public String reason;
    public ResultBean result;
    public int error_code;
    public static class ResultBean {
        public String stat;
        public List<DataBean> data;
        public static class DataBean {
            public String uniquekey;
            public String title;
            public String date;
            public String category;
            public String author_name;
            public String url;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;
            public String thumbnail_pic_s03;
        }
    }
}
