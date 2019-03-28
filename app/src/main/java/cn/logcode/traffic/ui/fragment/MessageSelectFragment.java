package cn.logcode.traffic.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.MessageSelectAdapter;
import cn.logcode.traffic.base.BaseFragment;
import cn.logcode.traffic.utils.ToasUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageSelectFragment extends BaseFragment {
    //listView
    private ListView list_select;
    //spinner
    private Spinner spinner_select;
    
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        list_select=view.findViewById(R.id.list_select);
        spinner_select=view.findViewById(R.id.spinner_select);
        initData();
        initListen();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_message_select;
    }
    public void initListen() {
        spinner_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    //全部
                    case 0:
                        ToasUtils.showToast(getContext(),"点击了"+parent.getItemAtPosition(position).toString());
                        break;
                    //温度
                    case 1:
                        ToasUtils.showToast(getContext(),"点击了"+parent.getItemAtPosition(position).toString());
                        break;
                    //湿度
                    case 2:
                        ToasUtils.showToast(getContext(),"点击了"+parent.getItemAtPosition(position).toString());
                        break;
                    //co2
                    case 3:
                        ToasUtils.showToast(getContext(),"点击了"+parent.getItemAtPosition(position).toString());
                        break;
                    //光照
                    case 4:
                        ToasUtils.showToast(getContext(),"点击了"+parent.getItemAtPosition(position).toString());
                        break;
                    //pm2.5
                    case 5:
                        ToasUtils.showToast(getContext(),"点击了"+parent.getItemAtPosition(position).toString());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 加载适配器
     */
    private void initData() {
        list_select.setAdapter(new MessageSelectAdapter());
    }

}
