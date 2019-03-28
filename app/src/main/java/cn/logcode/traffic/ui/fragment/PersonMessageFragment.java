package cn.logcode.traffic.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonMessageFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(getLayout(), container, false);
        initView(v,savedInstanceState);
        return v;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_person_message;
    }

}
