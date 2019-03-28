package cn.logcode.traffic.http.request;


public interface myRequestListener<T> {
    void success(String s);
    void failed(Exception e, String errorMsg);
}