package cn.logcode.traffic.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.RechargeRecordAdapter;
import cn.logcode.traffic.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecahrgeRecordFragment extends BaseFragment {
    private ListView list_record;
    private ImageView iv_head;  //头像
    private TextView tv_name, tv_pay_num; //账户名字，账户总支出

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(getLayout(), container, false);
        initView(v, savedInstanceState);
        initData();
        return v;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        list_record = view.findViewById(R.id.list_record);
        iv_head = view.findViewById(R.id.iv_head);
        tv_name = view.findViewById(R.id.tv_name);
        tv_pay_num = view.findViewById(R.id.tv_pay_num);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recahrge_record;
    }
    private void initData() {
        list_record.setAdapter(new RechargeRecordAdapter());
    }

}
