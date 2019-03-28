package cn.logcode.traffic.utils;

import android.app.Notification;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.logcode.traffic.App;
import cn.logcode.traffic.R;

/**
 * Created by 24327 on 2018/4/27.
 */

public class Utils {
    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断网络工具
     * @param context
     * @return
     */
    public static boolean isNetWork(Context context){


        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null){
//            ToasUtils.showToast(context,"当前网络"+info.getTypeName());
            return true;
        }else {
            ToasUtils.showToast(context,"无网络");
        }

        return false;
    }

    /**
     * 生成一个通知对象
     * @param title 通知的标题
     * @param msg  通知的信息
     * @return
     */
    public static Notification buildNotification(String title,String msg){
        Notification notification = new NotificationCompat.Builder(App.INSTANCE.getApplicationContext(),"通知")
                .setContentText(msg)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher).build();
        return notification;
    }
}
