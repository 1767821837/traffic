package cn.logcode.traffic.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.ui.fragment.MessageSelectFragment;
import cn.logcode.traffic.ui.fragment.RecahrgeRecordFragment;
import cn.logcode.traffic.utils.ToasUtils;

public class MyMessageActivity extends BaseActivity {
    //消息查询  消息分析
    private TextView tv_select,tv_analysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        setLeft_menu(R.mipmap.back,view ->onBackPressed());
        setTitle("我的消息");
    }
}
