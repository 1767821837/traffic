package cn.logcode.traffic.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.logcode.traffic.base.BaseFragment;

/**
 * Created by Planet on 2018/4/26.
 */

public class DataAnalysisVPAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> lists;

    public DataAnalysisVPAdapter(FragmentManager fm,List<BaseFragment> lists) {
        super(fm);
        this.lists = lists;
    }


    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
