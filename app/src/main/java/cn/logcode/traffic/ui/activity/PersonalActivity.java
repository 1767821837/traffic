package cn.logcode.traffic.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.PersonalViewPagerAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.ui.fragment.PersonMessageFragment;
import cn.logcode.traffic.ui.fragment.RecahrgeRecordFragment;
import cn.logcode.traffic.ui.fragment.ThresholdFragment;

public class PersonalActivity extends BaseActivity {
    private String titles[]={"个人信息","充值记录","阀值设置"};
    private TabLayout tl_title;
    private ViewPager vp_index;
    private List<Fragment> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        setTitle("个人中心");
        setLeft_menu(R.mipmap.back,view ->onBackPressed());
        findView();
        initData();

    }

    /**
     * 初始化ID
     */
    private void findView() {
        tl_title=findViewById(R.id.tab_title);
        vp_index=findViewById(R.id.vp_index);
    }

    /**
     * 设置关联,与数据
     */
    private void initData() {
        lists=new ArrayList<>();
        lists.add(new PersonMessageFragment());
        lists.add(new RecahrgeRecordFragment());
        lists.add(new ThresholdFragment());
        vp_index.setAdapter(new PersonalViewPagerAdapter(getSupportFragmentManager(),lists,titles));
        tl_title.setupWithViewPager(vp_index);
        vp_index.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl_title));
    }
}
