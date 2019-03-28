package cn.logcode.traffic.http;


public interface RequestListener<T> {
    void success(T bean);
    void failed(Exception e,String errorMsg);
}