package cn.logcode.traffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.RGLightMangerListViewAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.Beanred;
import cn.logcode.traffic.http.NullStringToEmptyAdapterFactory;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.orderRed;

/**
 * 红绿灯管理
 */
public class RGLigthMangerActivity extends BaseActivity {

    ListView listView;
    Spinner spinner;
    Button query;
    Button batch_setup;
    int falg = 0;
    int falgs = 0;
    private Beanred beanred;
    private List<Beanred.DataBean> data;
    private RGLightMangerListViewAdapter adapter;
    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rglmanget);
        setTitle("红绿灯管理");
        setLeft_menu(R.mipmap.back, view -> onBackPressed());

        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                initdata();
            }
        }, 0, 3000);
    }

    private void initdata() {
        myOkhttp.myokhttp("https://easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/lamplist", "", new myRequestListener() {
            @Override
            public void success(String s) {
                Log.i("*************", "success: " + s);
                data.clear();
                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();

                beanred = gson.fromJson(s, Beanred.class);

                List<Beanred.DataBean> beans = orderRed.OrderList(beanred.getData(), falg);
                data.addAll(beans);
                //排序
                adapter.notifyDataSetChanged();

            }

            @Override
            public void failed(Exception e, String errorMsg) {
                Log.i("*************", "success: " + errorMsg);
            }
        });
    }

    private void initView() {
        listView = findViewById(R.id.listview);
        spinner = findViewById(R.id.spinner);
        query = findViewById(R.id.query);
        batch_setup = findViewById(R.id.batch_setup);

        data = new ArrayList<>();
        Beanred.DataBean dataBean = new Beanred.DataBean();
        dataBean.setGreen(5);
        dataBean.setId(5);
        dataBean.setRed(5);
        dataBean.setYellow(5);
        data.add(dataBean);

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                falg = falgs;
                initdata();
            }
        });

        adapter = new RGLightMangerListViewAdapter(this, data);
        listView.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                falgs = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        initdata();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
