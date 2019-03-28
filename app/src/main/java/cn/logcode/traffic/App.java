package cn.logcode.traffic;

import android.app.Application;

import cn.logcode.traffic.utils.LiteUtil;

public class App extends Application {
    public static App INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        LiteUtil.init();
    }
}
