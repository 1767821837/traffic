package cn.logcode.traffic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import cn.logcode.traffic.App;
import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.Beanred;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.DialogUtils;

public class RGLightMangerListViewAdapter extends BaseAdapter {
    Context context;
    List<Beanred.DataBean> data;
    public RGLightMangerListViewAdapter(Context context,List<Beanred.DataBean> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
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
        if(convertView == null){
            convertView = View.inflate(App.INSTANCE.getApplicationContext(), R.layout.item_rglmanger,null);
        }
        Button setting = convertView.findViewById(R.id.setting);
        TextView lukou = convertView.findViewById(R.id.lukou);
        TextView red_light = convertView.findViewById(R.id.red_light);
        TextView yellow_light = convertView.findViewById(R.id.yellow_light);
        TextView green_light = convertView.findViewById(R.id.green_light);
        lukou.setText(data.get(position).getId()+"");
        red_light.setText(data.get(position).getRed()+"");
        yellow_light.setText(data.get(position).getYellow()+"");
        green_light.setText(data.get(position).getGreen()+"");
        return convertView;
    }
}
