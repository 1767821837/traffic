package cn.logcode.traffic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.logcode.traffic.R;

/**
 * Created by 24327 on 2018/4/25.
 */

public class CarRListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public CarRListAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_car_details, null);
            viewHolder.time = view.findViewById(R.id.item_car_details_tv_time);
            viewHolder.handle = view.findViewById(R.id.item_car_details_tv_handle);
            viewHolder.road= view.findViewById(R.id.item_car_details_tv_road);
            viewHolder.reason = view.findViewById(R.id.item_car_details_tv_reason);
            viewHolder.branch = view.findViewById(R.id.item_car_details_tv_branch);
            viewHolder.money = view.findViewById(R.id.item_car_details_tv_money);
            viewHolder.linearLayout = view.findViewById(R.id.item_car_details_ll);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.car_plate.setText(mList.get(i));
//        viewHolder.car_num.setText(mList.get(i));
//        viewHolder.car_money.setText(mList.get(i));
//        viewHolder.car_ms.setText(mList.get(i));
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(mList.get(i));
            }
        });
        return view;
    }



    public interface onItemClickListener {
        void onClick(String LicensePlate);
    }

    private onItemClickListener onItemClickListener;

    public void setOnItemDeleteClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder {
        TextView time;
        TextView handle;
        TextView road;
        TextView reason;
        TextView branch;
        TextView money;
        LinearLayout linearLayout;
    }

}

