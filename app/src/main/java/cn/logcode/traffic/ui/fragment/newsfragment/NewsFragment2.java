package cn.logcode.traffic.ui.fragment.newsfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.newslistviewAdapter;
import cn.logcode.traffic.bean.NewsBean;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;

public class NewsFragment2 extends Fragment {
    private ListView listviewnew;
    private NewsBean newsBean;
    private List<NewsBean.ResultBean.DataBean> data;
    private newslistviewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfragment, container, false);
        newsBean = new NewsBean();
        data = new ArrayList<>();
        NewsBean.ResultBean.DataBean dataBean = new NewsBean.ResultBean.DataBean();
        dataBean.title = "我是新闻";
        data.add(dataBean);
        initView(view);
        initdata();
        return view;
    }

    private void initView(View view) {
        listviewnew = (ListView) view.findViewById(R.id.listviewnew);
        adapter = new newslistviewAdapter(getContext(), data);
        listviewnew.setAdapter(adapter);

    }

    private void initdata() {
        myOkhttp.myokhttp("http://v.juhe.cn/toutiao/index?type=keji&key=4e84971db9da2d90c5392072797c8482", "", new myRequestListener() {
            @Override
            public void success(String s) {
                try {
                    newsBean = new Gson().fromJson(s, NewsBean.class);
                    data.clear();
                    data.addAll(newsBean.result.data);
                    adapter.notifyDataSetChanged();

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e, String errorMsg) {

            }
        });
    }
}
