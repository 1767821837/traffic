package cn.logcode.traffic.adapter;


import android.content.Context;

import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.CarBean;
import cn.logcode.traffic.bean.CarsBean;
import cn.logcode.traffic.dao.SqliteHelp;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.ui.activity.AccountMangerActivity;
import cn.logcode.traffic.utils.ToasUtils;


public class AccountMangerListViewAdapter extends BaseAdapter {
    List<CarsBean.DataBean> beans;
    Context context;
    public  HashMap<Integer, Boolean> isSelected;
    private Dao<CarBean, ?> dao;
    private SqliteHelp dbHelper;
    public AccountMangerListViewAdapter(Context context, List<CarsBean.DataBean> beans,HashMap<Integer, Boolean> isSelected) {
        this.context = context;
        this.beans = beans;
        this.isSelected = isSelected;
        dbHelper = new SqliteHelp(context);
        try {
            dao = dbHelper.getDao(CarBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    public int getCount() {
        return beans.size();
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

        TextView tv_car_num;
        TextView tv_car_money;
        CheckBox check_car;
        Button but_recharge;
        view = LayoutInflater.from(context).inflate(R.layout.item_accountmanger, viewGroup, false);
        tv_car_num = view.findViewById(R.id.tv_car_num);
        tv_car_money = view.findViewById(R.id.tv_car_money);
        check_car = view.findViewById(R.id.check_car);
        but_recharge = view.findViewById(R.id.but_recharge);
        tv_car_num.setText(beans.get(i).getNum() + "");
        tv_car_money.setText(beans.get(i).getMoney() + "");
        check_car.setChecked(isSelected.get(beans.get(i).getId()));
        check_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected.put(beans.get(i).getId(), check_car.isChecked());
            }
        });


        but_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View views = View.inflate(context, R.layout.dialog_caraccount_rec, null);
                android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(context)
                        .setView(views)
                        .create();
                dialog.show();
                TextView car_names = views.findViewById(R.id.car_names);
                EditText car_recharge_num = views.findViewById(R.id.car_recharge_num);
                Button recharge = views.findViewById(R.id.recharges);
                Button cancel = views.findViewById(R.id.cancel);
                car_recharge_num.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String s = editable.toString();
                        if ("".equals(s)) {
                            return;
                        }
                        if (Integer.parseInt(s) == 0) {
                            car_recharge_num.setText("");
                        }

                    }
                });
                car_names.setText(beans.get(i).getNum() + "");
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                recharge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      for (int i=0;i<beans.size();i++){
                          Log.i("*************", "onClick: "+isSelected.get(beans.get(i).getId()));
                      }
                        String money = car_recharge_num.getText().toString();
                        if (!"".equals(money)) {
                            int moneynum = Integer.parseInt(money);
                            if (moneynum > 0 && moneynum <= 999) {

                                myOkhttp.myokhttp("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge", "{\"num\":" + Integer.parseInt(beans.get(i).getId() + "") + ",\"money\":" + moneynum + "}", new myRequestListener() {
                                    @Override
                                    public void success(String s) {
                                        Log.i("*****", "success: " + s);
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);
                                            JSONObject object = jsonObject.getJSONObject("data");
                                            String msg = object.getString("msg");
                                            ToasUtils.showToast(context, msg);
                                            int money1 = beans.get(i).getMoney();
                                            beans.get(i).setMoney(money1 + moneynum);
                                            AccountMangerListViewAdapter.this.notifyDataSetChanged();

                                            CarBean carBean = new CarBean();
                                            carBean.setAftermonet(money1 + moneynum);
                                            carBean.setDate(new Date());
                                            carBean.setMoney(moneynum);
                                            carBean.setUsername("admin");
                                            carBean.setCarId(beans.get(i).getId());
                                            dao.create(carBean);
                                            dialog.dismiss();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void failed(Exception e, String errorMsg) {
                                        Log.i("*****", "success: " + errorMsg);
                                        ToasUtils.showToast(context, "请求发送错误");
                                        dialog.dismiss();
                                    }
                                });
                            } else {
                                ToasUtils.showToast(context, "请输入0~999");
                            }
                        }
                    }
                });
            }
        });



        return view;
    }
}
