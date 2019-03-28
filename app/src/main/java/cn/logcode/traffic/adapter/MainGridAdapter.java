package cn.logcode.traffic.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.utils.SPUtils;

public class MainGridAdapter extends BaseAdapter {
    SharedPreferences sp = SPUtils.getDefault("song");
    Context context;
    List<Integer> datas;
    String title[] = {"温度", "湿度", "光照", "CO2", "PM2.5", "道路状态"};


    public MainGridAdapter(Context context, List<Integer> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public int getCount() {
        return 6;
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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_mainactivity, null);
        }
        TextView tv_value = convertView.findViewById(R.id.tv_value);
        RelativeLayout tv_state = convertView.findViewById(R.id.rl);
        TextView tv_title = convertView.findViewById(R.id.tv_title);
        tv_title.setText(title[position]);
        tv_value.setText(datas.get(position) + "");
        String mun = sp.getString(title[position], "200");
        if (Integer.parseInt(mun) < datas.get(position)&&sp.getBoolean("fazhistate",false)) {
            tv_state.setBackgroundColor(Color.parseColor("#ffcc0000"));
        } else {
            tv_state.setBackgroundColor(Color.parseColor("#ff99cc00"));
        }
        return convertView;
    }


}
