package cn.logcode.traffic.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.logcode.traffic.App;
import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.CarBean;

/**
 * Created by Planet on 2018/4/28.
 */

/**
 * tv_order_num 序号
 * tv_car_num 车号
 * tv_recharge 充值金额
 * tv_operaction 操作人
 * tv_time 充值事件
 */

public class BIllAdapter extends BaseAdapter {
    List<CarBean> beans;

    public BIllAdapter(List<CarBean> beans) {
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null){
            view = View.inflate(App.INSTANCE.getApplicationContext(), R.layout.item_bill, null);
        }else{
            view = convertView;
        }
        TextView tv_order_num = view.findViewById(R.id.tv_order_num);
        TextView tv_car_num = view.findViewById(R.id.tv_car_num);
        TextView tv_recharge = view.findViewById(R.id.tv_recharge);
        TextView tv_operaction = view.findViewById(R.id.tv_operaction);
        TextView tv_time = view.findViewById(R.id.tv_time);
        tv_order_num.setText(beans.get(position).getId()+"");
        tv_car_num.setText(beans.get(position).getCarId()+"");
        tv_recharge.setText(beans.get(position).getMoney()+"");
        tv_operaction.setText(beans.get(position).getUsername());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tv_time.setText(format.format(beans.get(position).getDate()));

        return view;
    }
}
