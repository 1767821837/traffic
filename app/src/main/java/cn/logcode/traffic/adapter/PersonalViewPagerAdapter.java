package cn.logcode.traffic.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Planet on 2018/4/26.
 */

public class PersonalViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> lists;
    private String titles[];
    public PersonalViewPagerAdapter(FragmentManager fm,List<Fragment> lists,String[] titles) {
        super(fm);
        this.lists=lists;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
