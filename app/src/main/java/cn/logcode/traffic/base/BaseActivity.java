package cn.logcode.traffic.base;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.CarsBean;
import cn.logcode.traffic.utils.SPUtils;

public class BaseActivity extends AppCompatActivity {
    private static int statusBar_height = 0;
    public RelativeLayout tool_bar;
    public ImageView left_menu;
    public TextView title;
    public LinearLayout desc_right;
    public FrameLayout container;
    public View status_bar;
    public View mView;
    public DrawerLayout drawerLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_activity);

        initView();
        init();
        initListener();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initView() {
        tool_bar = findViewById(R.id.tool_bar);
        left_menu = findViewById(R.id.left_menu);
        title = findViewById(R.id.title);
        desc_right = findViewById(R.id.desc_right);
        container = findViewById(R.id.container);
        status_bar = findViewById(R.id.status_bar);
        drawerLayout = findViewById(R.id.drawerLayout);

    }

    private void init() {
        tool_bar.setVisibility(View.GONE);
        ViewGroup.LayoutParams params = status_bar.getLayoutParams();
        params.height = getStatusBarHeight();
        status_bar.setLayoutParams(params);


    }

    private void initListener() {
        left_menu.setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mView = View.inflate(this, layoutResID, null);
        container.addView(mView);
    }

    public void setTitle(String title, View.OnClickListener listener) {
        tool_bar.setVisibility(View.VISIBLE);
        this.title.setText(title);
        this.title.setOnClickListener(listener);
    }

    public void setTitle(String title) {
        tool_bar.setVisibility(View.VISIBLE);
        this.title.setText(title);
    }

    public void setLeft_menu(@DrawableRes int id, View.OnClickListener listener) {
        tool_bar.setVisibility(View.VISIBLE);
        left_menu.setImageResource(id);
        left_menu.setOnClickListener(listener);
    }

    public void hideLeftMenu() {
        left_menu.setVisibility(View.GONE);
    }

    //显示网络设置
    public void showDesc() {
        desc_right.setVisibility(View.VISIBLE);
    }

    //关闭锁定侧滑栏
    public void setYesDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    /**
     * 59      *  获取系统状态栏高度
     * 60      * @param context
     * 61      * @return
     * 62
     */
    public int getStatusBarHeight() {

        //反射影响性能
        if (statusBar_height == 0) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBar_height = getResources().getDimensionPixelSize(resourceId);
            }
            Log.i("==height==", statusBar_height + "");
        }
        return statusBar_height;
    }



}
