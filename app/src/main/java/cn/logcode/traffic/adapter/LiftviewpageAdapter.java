package cn.logcode.traffic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.logcode.traffic.ui.fragment.LifeFragemnt.LifeAtmosphereFragmentf;

public class LiftviewpageAdapter extends FragmentPagerAdapter {
    public LiftviewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new LifeAtmosphereFragmentf();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
