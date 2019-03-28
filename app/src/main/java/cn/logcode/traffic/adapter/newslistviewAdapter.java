package cn.logcode.traffic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.NewsBean;

public class newslistviewAdapter extends BaseAdapter {
    Context context;
    List<NewsBean.ResultBean.DataBean> newsBean;

    public newslistviewAdapter(Context context, List<NewsBean.ResultBean.DataBean> newsBean) {
        this.context = context;
        this.newsBean = newsBean;
    }

    @Override
    public int getCount() {
        return newsBean.size();
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
            view = View.inflate(context, R.layout.newitem, null);
        }

        TextView tv_new = view.findViewById(R.id.tv_news);
        if (newsBean.get(i).title != null)
            tv_new.setText(newsBean.get(i).title);
        return view;
    }
}
