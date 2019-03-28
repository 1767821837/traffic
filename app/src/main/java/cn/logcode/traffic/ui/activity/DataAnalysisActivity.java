package cn.logcode.traffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.DataAnalysisVPAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.base.BaseFragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA1Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA2Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA3Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA4Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA5Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA6Fragment;
import cn.logcode.traffic.ui.fragment.DAfragments.DA7Fragment;

/**
 * Created by 24327 on 2018/4/28.
 */

public class DataAnalysisActivity extends BaseActivity {

    private ViewPager vp;
    private TextView c1;
    private TextView c2;
    private TextView c3;
    private TextView c4;
    private TextView c5;
    private TextView c6;
    private TextView c7;
    private TextView[] ys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        vp.setAdapter(new DataAnalysisVPAdapter(getSupportFragmentManager(),getDataAnalysiss()));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < ys.length; i++) {
                    if (i==position){
                        ys[i].setBackgroundResource(R.drawable.tv_circular_red);
                    }else {
                        ys[i].setBackgroundResource(R.drawable.tv_circular);
                    }
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        setContentView(R.layout.activity_data_analysis);
        setTitle("数据分析");
        setLeft_menu(R.mipmap.back,view -> onBackPressed());
        vp = findViewById(R.id.da_vp);
        c1 = findViewById(R.id.tv_circular_1);
        c2 = findViewById(R.id.tv_circular_2);
        c3 = findViewById(R.id.tv_circular_3);
        c4 = findViewById(R.id.tv_circular_4);
        c5 = findViewById(R.id.tv_circular_5);
        c6 = findViewById(R.id.tv_circular_6);
        c7 = findViewById(R.id.tv_circular_7);
        ys= new TextView[]{c1,c2,c3,c4,c5,c6,c7};
        ys[0].setBackgroundResource(R.drawable.tv_circular_red);

    }

    public List<BaseFragment> getDataAnalysiss(){
        List<BaseFragment> fragments = new ArrayList<>();
        DA1Fragment f1 = new DA1Fragment();
        DA2Fragment f2 = new DA2Fragment();
        DA3Fragment f3 = new DA3Fragment();
        DA4Fragment f4 = new DA4Fragment();
        DA5Fragment f5 = new DA5Fragment();
        DA6Fragment f6 = new DA6Fragment();
        DA7Fragment f7 = new DA7Fragment();
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        fragments.add(f5);
        fragments.add(f6);
        fragments.add(f7);
        return fragments;

    }
}
