package cn.logcode.traffic.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cn.logcode.traffic.App;
import cn.logcode.traffic.R;

//公交车载客详情
public class BusDetailsAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 5;
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
        return View.inflate(App.INSTANCE.getApplicationContext(), R.layout.item_busdetails,null);
    }
}
