package cn.logcode.traffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.BIllAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.CarBean;
import cn.logcode.traffic.dao.SqliteHelp;
import cn.logcode.traffic.utils.ToasUtils;

public class BIllManagerActivity extends BaseActivity {
    Spinner sp_order;
    ListView list_bill;
    //查询按钮
    Button btn_select;
    SqliteHelp help;
    Dao<CarBean, ?> dao;
    int flag = 0;
    private List<CarBean> carBeans;
    private BIllAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_manager);
        setLeft_menu(R.mipmap.back,view ->onBackPressed());
        setTitle("账单管理");
        initview();
        initdata();
    }

    private void initdata() {
        help = new SqliteHelp(BIllManagerActivity.this);
        try {
            dao = help.getDao(CarBean.class);
            carBeans = dao.queryForAll();
            adapter = new BIllAdapter(carBeans);
            list_bill.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sp_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                flag = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carBeans.clear();
                try {
                    switch (flag) {
                        case 0:
                            carBeans.addAll(dao.queryBuilder().orderBy("date", true).query());
                            break;
                        case 1:
                            carBeans.addAll(dao.queryBuilder().orderBy("date", false).query());
                            break;
                    }
                    adapter.notifyDataSetChanged();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initview() {

        list_bill = findViewById(R.id.list_bills);
        sp_order = findViewById(R.id.sp_time_order);
        btn_select = findViewById(R.id.btn_select);
    }
}
