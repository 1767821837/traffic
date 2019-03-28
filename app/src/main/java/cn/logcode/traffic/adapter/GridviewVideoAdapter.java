package cn.logcode.traffic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.logcode.traffic.R;

public class GridviewVideoAdapter extends BaseAdapter {
    Context context;
    List<String> videolist;

    public GridviewVideoAdapter(Context context, List<String> videolist) {
        this.context = context;
        this.videolist = videolist;
    }

    @Override
    public int getCount() {
        return videolist.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.gridview_video_item, null);
        }
        TextView tv_video = view.findViewById(R.id.tv_video);
        ImageView img_video = view.findViewById(R.id.img_video);
        tv_video.setText(videolist.get(i));
        return view;
    }
}
