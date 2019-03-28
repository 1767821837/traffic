package cn.logcode.traffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.stmt.query.OrderBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.BusQueryAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.BusBeans;
import cn.logcode.traffic.http.NullStringToEmptyAdapterFactory;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.DialogUtils;
import cn.logcode.traffic.utils.orderRed;

// 公交查询
public class BusqueryActivity extends BaseActivity {

    //详情
    Button bus_desc;

    ExpandableListView expand_listview;
    private BusQueryAdapter adapter;
    private BusBeans buslist;
    private List<BusBeans.DataBean> busBeans;
    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquery);
        expand_listview = findViewById(R.id.expand_listview);
        setLeft_menu(R.mipmap.back,view ->onBackPressed());
        setTitle("公交查询");
        busBeans = new ArrayList<>();
        initdata();


    timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            initdata();

        }
    }, 1000,3000);
}


    private void initdata() {
        String url = "https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/gongjiao";
        myOkhttp.myokhttp(url, "", new myRequestListener() {
            @Override
            public void success(String s) {
                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();

                BusBeans buslist = gson.fromJson(s, BusBeans.class);
                busBeans.clear();
              busBeans.addAll(orderRed.orderBus(buslist).getData());
             initdaapter();
            }
            @Override
            public void failed(Exception e, String errorMsg) {
            }
        });
    }

    private void initdaapter() {
        if(adapter ==null){
            adapter = new BusQueryAdapter(BusqueryActivity.this, busBeans);
            expand_listview.setAdapter(adapter);
        }else {

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
