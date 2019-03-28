package cn.logcode.traffic.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import cn.logcode.traffic.App;

public class SPUtils {
    public static SharedPreferences getDefault(){
        return PreferenceManager.getDefaultSharedPreferences(App.INSTANCE.getApplicationContext());
    }

    public static SharedPreferences.Editor getEditor(SharedPreferences sp){
        return sp.edit();
    }

    public static SharedPreferences getDefault(String name){
        return App.INSTANCE.getSharedPreferences(name,App.MODE_PRIVATE);
    }

}
