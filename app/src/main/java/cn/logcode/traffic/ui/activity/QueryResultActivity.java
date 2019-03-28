package cn.logcode.traffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.CarLListAdapter;
import cn.logcode.traffic.adapter.CarRListAdapter;
import cn.logcode.traffic.base.BaseActivity;

/**
 * 查询结果页面
 */
public class QueryResultActivity extends BaseActivity {

    private ListView l_listview;
    private ListView r_listview;
    private Button btn_qr;
    private String licenseplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        init();
        Llist();
        Rlist();
    }

    /**
     * 左边listview
     */
    private void Llist() {
        List<String> ss = new ArrayList<>();
        ss.add("鲁A10001");
        ss.add("鲁A10002");
        ss.add("鲁A10003");
        ss.add("鲁A10004");
        ss.add("鲁A10005");
        CarLListAdapter carLListAdapter = new CarLListAdapter(this,ss);
        l_listview.setAdapter(carLListAdapter);

        //减号的处理是事件，点击删除item
        carLListAdapter.setOnItemDeleteClickListener(new CarLListAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i) {
                ss.remove(i);
                carLListAdapter.notifyDataSetChanged();
            }
        });

        //加号的处理事件
        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentEvent(licenseplate);
            }
        });
    }

    /**
     * 右边listview
     */
    private void Rlist() {
        List<String> ss = new ArrayList<>();
        ss.add("鲁A10001");
        ss.add("鲁A10002");
        ss.add("鲁A10003");
        ss.add("鲁A10004");
        ss.add("鲁A10005");
        CarRListAdapter carRListAdapter = new CarRListAdapter(this,ss);
        r_listview.setAdapter(carRListAdapter);
        carRListAdapter.setOnItemDeleteClickListener(new CarRListAdapter.onItemClickListener() {
            @Override
            public void onClick(String LicensePlate) {
                IntentEvent(LicensePlate);
            }
        });
    }


    private void IntentEvent(String LicensePlate){
        Intent intent = new Intent(this,SnapActivity.class);
        intent.putExtra("licenseplate",LicensePlate);
        startActivity(intent);
    }

    private void init() {
        setTitle("查询结果");
        setLeft_menu(R.mipmap.back, view -> onBackPressed());
        l_listview = findViewById(R.id.l_qr_list);
        r_listview = findViewById(R.id.r_qr_list);
        btn_qr = findViewById(R.id.btn_qr);
        licenseplate = getIntent().getStringExtra("licenseplate");
    }

}
