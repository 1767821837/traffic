package cn.logcode.traffic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.BusBeans;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.ui.activity.BusqueryActivity;
import cn.logcode.traffic.utils.orderRed;

public class BusQueryAdapter extends BaseExpandableListAdapter {
    Context context;
    List<BusBeans.DataBean> busBeans;

    public BusQueryAdapter(Context context, List<BusBeans.DataBean> busBeans) {
        this.context = context;
        this.busBeans = busBeans;
    }

    //父item数量
    @Override
    public int getGroupCount() {
        return busBeans.size();
    }

    //子item数量
    @Override
    public int getChildrenCount(int groupPosition) {
        return busBeans.get(groupPosition).getBus().size();
    }

    //得到父item
    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    //得到一个父item的一个子item
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_busquery_group, null);
        }
        TextView station = convertView.findViewById(R.id.station);
        station.setText(busBeans.get(groupPosition).getZhantai());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Log.i("!!!!!!!!!!!", "getChildView: " + busBeans);
        if (convertView == null)
            convertView = View.inflate(context, R.layout.item_busquery_child, null);
        TextView bus_id = convertView.findViewById(R.id.bus_id);
        TextView arrival_time = convertView.findViewById(R.id.arrival_time);
        bus_id.setText(busBeans.get(groupPosition).getBus().get(childPosition).getName());
        arrival_time.setText(busBeans.get(groupPosition).getBus().get(childPosition).getDistance() + "");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    public void ok() {
        String url = "https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/gongjiao";
        String url1 = "https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/weather";


        myOkhttp.myokhttp(url, "", new myRequestListener() {
            @Override
            public void success(String s) {

                BusBeans buslist = new Gson().fromJson(s, BusBeans.class);
                BusBeans busBeanss = orderRed.orderBus(buslist);
                busBeans = busBeanss.getData();
            }

            @Override
            public void failed(Exception e, String errorMsg) {
            }
        });

    }
}
