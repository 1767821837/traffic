package cn.logcode.traffic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.logcode.traffic.App;
import cn.logcode.traffic.R;
import cn.logcode.traffic.utils.DialogUtils;

/**
 * Created by 24327 on 2018/4/25.
 */

public class CarLListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public CarLListAdapter(Context context, List<String> list) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.itme_query_result, null);
            viewHolder.car_plate = view.findViewById(R.id.tv_item_car_plate);
            viewHolder.car_num = view.findViewById(R.id.tv_item_car_num);
            viewHolder.car_money= view.findViewById(R.id.tv_item_car_money);
            viewHolder.car_ms = view.findViewById(R.id.car_ms);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.car_plate.setText(mList.get(i));
//        viewHolder.car_num.setText(mList.get(i));
//        viewHolder.car_money.setText(mList.get(i));
//        viewHolder.car_ms.setText(mList.get(i));
        viewHolder.car_ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onDeleteClick(i);
            }
        });
        return view;
    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

    class ViewHolder {
        TextView car_plate;
        TextView car_num;
        TextView car_money;
        Button car_ms;
    }

}

