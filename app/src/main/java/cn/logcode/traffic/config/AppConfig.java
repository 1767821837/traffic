package cn.logcode.traffic.config;

import cn.logcode.traffic.utils.SPUtils;

public class AppConfig {
    public static final String IS_FRIST= "isFrist";
    public static final String USERNAME= "username";
    public static final String USERPW= "userpw";
    public static final String AUTOLOGIN= "autologin";
    public static final String PHONE_NUMBER_REG = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8}$";
    public static String ADDRESS = SPUtils.getDefault("address").getString("ip","");
    public static String PORT = SPUtils.getDefault("address").getString("port","");
    
    public static String BASE_URL= "http://"+ADDRESS+":"+PORT+"/stu/";
    public static final String USER_ID_NOW="userId";
    public static final String USER_STATUS="userstatus";
    public static final String USER_PHONE="phone";
    public static final String USER_spName="user";
}
