package cn.logcode.traffic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.CarsBean;

public class SpinnerAdapter extends BaseAdapter {
    List<CarsBean.DataBean> data;
    Context context;

    public SpinnerAdapter(List<CarsBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_spinner,null);
        }
        TextView textView = convertView.findViewById(R.id.text);

        textView.setText(data.get(position).getNum());

        return convertView;
    }
}
