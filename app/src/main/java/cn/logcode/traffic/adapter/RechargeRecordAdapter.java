package cn.logcode.traffic.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cn.logcode.traffic.App;
import cn.logcode.traffic.R;

/**
 * Created by Planet on 2018/4/26.
 */

public class RechargeRecordAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 3;
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

        return View.inflate(App.INSTANCE.getApplicationContext(), R.layout.item_record, null);
    }
}
