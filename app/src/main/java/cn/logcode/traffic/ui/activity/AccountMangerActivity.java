package cn.logcode.traffic.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.AccountMangerListViewAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.CarBean;
import cn.logcode.traffic.bean.CarsBean;
import cn.logcode.traffic.dao.SqliteHelp;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.DialogUtils;
import cn.logcode.traffic.utils.ToasUtils;

public class AccountMangerActivity extends BaseActivity {
    AccountMangerListViewAdapter adapter;
    //批量充值
    LinearLayout batch_recharge;
    //充值记录
    LinearLayout recharge_record;
    String userName;
    ListView list_car_manage;
    List<CarsBean.DataBean> beans;
    private HashMap<Integer, Boolean> isSelected;
    private Dao<CarBean, ?> dao;
    private SqliteHelp dbHelper;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountmanger);
        isSelected = new HashMap<>();
        setLeft_menu(R.mipmap.back, view -> onBackPressed());

        setTitle("账户管理");
        initview();
        try {
            initdata();
        } catch (Exception e) {
            e.printStackTrace();
        }
        batch_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void initdata() throws Exception {
        progressDialog = DialogUtils.progressDialog(AccountMangerActivity.this);
        progressDialog.show();
        dbHelper = new SqliteHelp(AccountMangerActivity.this);
        dao = dbHelper.getDao(CarBean.class);
        Log.i("%%%%%", "initdata: " + dao.queryForAll().size());
        myOkhttp.myokhttp("https://easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/carlist ", "", new myRequestListener() {
            @Override
            public void success(String s) {
                CarsBean carsBean = new Gson().fromJson(s, CarsBean.class);
                beans.clear();
                beans.addAll(carsBean.getData());
                initMapdata();
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void failed(Exception e, String errorMsg) {
                Toast.makeText(AccountMangerActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void initview() {
        batch_recharge = findViewById(R.id.batch_recharge);
        recharge_record = findViewById(R.id.recharge_record);
        list_car_manage = findViewById(R.id.list_car_manage);
        batch_recharge.setVisibility(View.VISIBLE);
        recharge_record.setVisibility(View.VISIBLE);
        beans = new ArrayList<>();
        CarsBean.DataBean bean = new CarsBean.DataBean();
        bean.setNum(1);
        bean.setId(1);
        bean.setMoney(100);
        beans.add(bean);
        initMapdata();
        adapter = new AccountMangerListViewAdapter(AccountMangerActivity.this, beans, isSelected);
        list_car_manage.setAdapter(adapter);
        recharge_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountMangerActivity.this, BIllManagerActivity.class));
            }
        });
    }

    private void initMapdata() {
        int size = beans.size();
        for (int i = 0; i < size; i++) {
            isSelected.put(beans.get(i).getId(), false);
        }
    }

    public void showDialog() {
        isSelected = adapter.isSelected;
        List<CarsBean.DataBean> pibeans = new ArrayList<>();
        for (CarsBean.DataBean bean : beans
                ) {
            Boolean aBoolean = isSelected.get(bean.getId());
            if (aBoolean) {
                pibeans.add(bean);
            }
        }
        View views = View.inflate(AccountMangerActivity.this, R.layout.dialog_caraccount_rec, null);
        AlertDialog dialog = new AlertDialog.Builder(AccountMangerActivity.this)
                .setView(views)
                .create();
        dialog.show();
        TextView car_names = views.findViewById(R.id.car_names);
        EditText car_recharge_num = views.findViewById(R.id.car_recharge_num);
        Button recharge = views.findViewById(R.id.recharges);
        Button cancel = views.findViewById(R.id.cancel);

        StringBuffer sbuff = new StringBuffer();
        for (CarsBean.DataBean data : pibeans
                ) {
            sbuff.append(data.getNum() + ",");
        }
        car_names.setText(sbuff.toString().substring(0, sbuff.length() - 1));
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String money = car_recharge_num.getText().toString();
                int moneynum = Integer.parseInt(money);
                for (CarsBean.DataBean dataBean : pibeans
                        ) {
                    myOkhttp.myokhttp("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge", "{\"num\":" + Integer.parseInt(dataBean.getId() + "") + ",\"money\":" + moneynum + "}", new myRequestListener() {
                        @Override
                        public void success(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                JSONObject object = jsonObject.getJSONObject("data");
                                String msg = object.getString("msg");
                                ToasUtils.showToast(AccountMangerActivity.this, sbuff.toString().substring(0, sbuff.length() - 1) + msg);
                                int money1 = beans.get(dataBean.getId() - 1).getMoney();
                                beans.get(dataBean.getId() - 1).setMoney(money1 + moneynum);
                                CarBean carBean = new CarBean();
                                carBean.setAftermonet(money1 + moneynum);
                                carBean.setDate(new Date());
                                carBean.setMoney(moneynum);
                                carBean.setUsername("admin");
                                carBean.setCarId(dataBean.getId());
                                dao.create(carBean);
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                                progressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                            } catch (SQLException e) {
                                progressDialog.dismiss();
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void failed(Exception e, String errorMsg) {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

    }
}
