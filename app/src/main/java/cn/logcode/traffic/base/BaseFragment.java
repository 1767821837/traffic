package cn.logcode.traffic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Planet on 2018/4/26.
 */

public abstract class BaseFragment extends Fragment {
    FragmentManager manager;
    FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(getLayout(),container,false);
        initView(view,savedInstanceState);
        return view;
    }

    protected abstract void initView(View view,Bundle savedInstanceState);
    protected abstract int getLayout();

    /**
     * 添加fragmen
     * @param containerViewId
     * @param fragment
     */
    public void replaceFragment(int containerViewId,Fragment fragment){
        manager=getActivity().getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(containerViewId,fragment);
        transaction.commit();
    }

    /**
     * 移除Fragment
     * @param fragment
     */
    public void removeFragment(Fragment fragment){
        manager=getActivity().getSupportFragmentManager();
        transaction=manager.beginTransaction();
        if(fragment!=null){
            transaction.remove(fragment);
            transaction.commit();
        }
    }
}
