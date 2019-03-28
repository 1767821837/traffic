package cn.logcode.traffic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.logcode.traffic.R;

/**
 * Created by 24327 on 2018/4/26.
 */

public class ToasUtils {


    /**
     * 自定义Toast
     * 短时间toast
     */

    static Toast toast;
    public static void showToast(Context context,String msg){
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.toast_layout,null);
        TextView tv_show=view.findViewById(R.id.tv_toast);
        tv_show.setText(msg);

        if (toast != null) {
            toast.setView(view);
        } else {

            toast = new Toast(context);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        }
        toast.show();
    }

    /**
     * 长时间toast
     * @param context
     * @param msg
     */
    public static void showToastLong(Context context,String msg){
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.toast_layout,null);
        TextView tv_show=view.findViewById(R.id.tv_toast);
        tv_show.setText(msg);
        if (toast != null) {
            toast.setView(view);
        } else {
            toast = new Toast(context);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        }
        toast.show();
    }

}
