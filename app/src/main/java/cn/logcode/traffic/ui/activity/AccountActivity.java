package cn.logcode.traffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.CarsBean;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;

public class AccountActivity extends BaseActivity {
    Spinner car_num;
    TextView balance;
    Button query;
    Button recharge;
    EditText ed_money;
    //小车集合数据
    List<CarsBean.DataBean> data;
    CarsBean.DataBean currentCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        query = findViewById(R.id.query);
        setTitle("我的账户");
        setLeft_menu(R.mipmap.back,view ->onBackPressed());

        ed_money = findViewById(R.id.ed_money);
        balance = findViewById(R.id.balance);
        recharge = findViewById(R.id.recharge);
        car_num = findViewById(R.id.car_num);
        car_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    balance.setText(data.get(i).getMoney() + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initdata();

    }

    private void initdata() {
        myOkhttp.myokhttp("https://easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/carlist ", "", new myRequestListener() {
            @Override
            public void success(String s) {
                CarsBean carsBean = new Gson().fromJson(s, CarsBean.class);
                data = carsBean.getData();
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    datas.add(data.get(i).getNum() + "");
                }
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_spinner1, datas);
                car_num.setAdapter(adapter);
            }

            @Override
            public void failed(Exception e, String errorMsg) {

            }
        });

    }
}
