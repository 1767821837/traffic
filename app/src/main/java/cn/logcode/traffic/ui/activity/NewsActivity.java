package cn.logcode.traffic.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.NewsAdapte;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.ui.fragment.newsfragment.NewsFragment1;
import cn.logcode.traffic.ui.fragment.newsfragment.NewsFragment2;
import cn.logcode.traffic.ui.fragment.newsfragment.NewsFragment3;

public class NewsActivity extends BaseActivity {

    private LinearLayout ll_view;
    private View view;
    private ViewPager news_viewpager;
    private int leftmax;
    private int widths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setLeft_menu(R.mipmap.back,view ->onBackPressed());
        setTitle("新闻媒体");
        initView();

    }

    private void initView() {
        ll_view = findViewById(R.id.ll_view);
        view = findViewById(R.id.view);
        news_viewpager = findViewById(R.id.news_viewpager);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment1());
        fragmentList.add(new NewsFragment2());
        fragmentList.add(new NewsFragment3());
        news_viewpager.setAdapter(new NewsAdapte(getSupportFragmentManager(),fragmentList));
        news_viewpager.setOffscreenPageLimit(3);
        news_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
                params.weight = position + positionOffset;
                view.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}
