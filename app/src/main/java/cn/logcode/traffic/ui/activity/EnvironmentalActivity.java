package cn.logcode.traffic.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import  cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.ViepageEnvironmentAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.ui.fragment.DAfragments.DA1Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA2Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA3Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA4Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA5Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA6Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA7Fragment;

public class EnvironmentalActivity extends BaseActivity {
ViewPager viepage_environment;
List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_);
        setLeft_menu(R.mipmap.back,view ->onBackPressed());
        setTitle("数据分析");
        viepage_environment = findViewById(R.id.viepage_environment);
        fragmentList = new ArrayList<>();
        fragmentList.add(new DA1Fragment());
        fragmentList.add(new DA2Fragment());
        fragmentList.add(new DA3Fragment());
        fragmentList.add(new DA4Fragment());
        fragmentList.add(new DA5Fragment());
        fragmentList.add(new DA6Fragment());

        ViepageEnvironmentAdapter adapter = new ViepageEnvironmentAdapter(getSupportFragmentManager(),fragmentList);
       viepage_environment.setOffscreenPageLimit(fragmentList.size());
        viepage_environment.setAdapter(adapter);
    }
}
