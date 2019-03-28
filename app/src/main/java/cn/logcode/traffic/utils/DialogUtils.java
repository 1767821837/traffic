package cn.logcode.traffic.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.BusDetailsAdapter;
import cn.logcode.traffic.config.AppConfig;

public class DialogUtils {
    static SharedPreferences sp = SPUtils.getDefault("song");
    SharedPreferences.Editor editor = sp.edit();

    //阈值设置Dialog
    public static void showSetThreshold(Context context, final SetThreshold setThreshold) {
        View view = View.inflate(context, R.layout.dialog_threshold, null);
        Button save = view.findViewById(R.id.save);
        Switch btn_switch = view.findViewById(R.id.btn_switch);
        Button cancel = view.findViewById(R.id.cancel);
        EditText wd = view.findViewById(R.id.ed_wd);
        EditText sd = view.findViewById(R.id.ed_sd);
        EditText gz = view.findViewById(R.id.ed_gz);
        EditText co = view.findViewById(R.id.ed_co2);
        EditText pm = view.findViewById(R.id.ed_pm);
        EditText road = view.findViewById(R.id.ed_road);
        wd.setText(sp.getString("温度", "20"));
        sd.setText(sp.getString("湿度", "50"));
        gz.setText(sp.getString("光照", "500"));
        co.setText(sp.getString("CO2", "200"));
        pm.setText(sp.getString("PM2.5", "300"));
        road.setText(sp.getString("道路状态", "4"));
        btn_switch.setChecked(sp.getBoolean("fazhistate", false));


        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setThreshold.save(wd.getText().toString().trim(), sd.getText().toString().trim(),
                        gz.getText().toString().trim(), co.getText().toString().trim()
                        , pm.getText().toString().trim(), road.getText().toString().trim(), btn_switch.isChecked());
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    //显示设置IPdialog
    private static void showSetIPDialog(Context context, final SetIpListener listener) {
        View view = View.inflate(context, R.layout.set_ip_dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        final EditText ip = view.findViewById(R.id.ed_ip);
        final EditText port = view.findViewById(R.id.ed_port);
        Button save = view.findViewById(R.id.save);
        Button cancel = view.findViewById(R.id.cancel);

        String address = SPUtils.getDefault("address").getString("ip", "");
        String ip_port = SPUtils.getDefault("address").getString("port", "");

        if (AppConfig.ADDRESS.equals("") || AppConfig.PORT.equals("")) {

        } else {
            ip.setText(address);
            port.setText(ip_port);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.save(ip.getText().toString().trim(), port.getText().toString().trim());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //ip设置dialog事件处理
    public static void SetNetwork(Context context) {
        showSetIPDialog(context, (String ip, String port) -> {
            SPUtils.getEditor(SPUtils.getDefault("address"))
                    .putString("ip", ip)
                    .putString("port", port)
                    .commit();
            ToasUtils.showToast(context, "设置成功");
            AppConfig.ADDRESS = SPUtils.getDefault("address").getString("ip", "");
            AppConfig.PORT = SPUtils.getDefault("address").getString("port", "");
            AppConfig.BASE_URL = "http://" + AppConfig.ADDRESS + ":" + AppConfig.PORT + "/trafficWeb/api/";
            Log.i("==baseurl==", AppConfig.BASE_URL);
        });
    }

    //显示车辆账户充值dialog
    public static void showCarAccountRechargeDialog(Context context,
                                                    List<String> carNames, CarRechargeListener listener) {
        View view = View.inflate(context, R.layout.dialog_caraccount_rec, null);

        //充值金额
        EditText car_recharge_num = view.findViewById(R.id.car_recharge_num);
        //小车列表
        TextView car_names = view.findViewById(R.id.car_names);
        //充值
        Button recharge = view.findViewById(R.id.recharge);
        //取消
        Button cancel = view.findViewById(R.id.cancel);

        String str = "";

        for (String name : carNames) {
            str = str + "," + name;
        }

        car_names.setText(str.replaceFirst("、", ""));

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();

        recharge.setOnClickListener(View -> {
            try {
                listener.rechargeNum(Integer.parseInt(car_recharge_num.getText().toString().trim()));
            } catch (NumberFormatException e) {
                listener.rechargeNum(0);
            }

            dialog.dismiss();
        });

        cancel.setOnClickListener(View -> dialog.dismiss());

        dialog.show();
    }

    //显示公交载客详情
    public static void showBusDetails(Context context) {
        View view = View.inflate(context, R.layout.dialog_bus_details, null);

        ListView listview = view.findViewById(R.id.listview);
        listview.setAdapter(new BusDetailsAdapter());
        Button back = view.findViewById(R.id.back);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();

        back.setOnClickListener(View -> dialog.dismiss());

        dialog.show();
    }

    //红绿灯周期设置
    public static void showRGLSettingDialog(Context context, RGLSetListener listener) {
        View view = View.inflate(context, R.layout.dialog_rglset, null);
        Button save = view.findViewById(R.id.save);
        Button cancel = view.findViewById(R.id.cancel);
        EditText red = view.findViewById(R.id.red_cycle);
        EditText green = view.findViewById(R.id.green_cycle);
        EditText yellow = view.findViewById(R.id.yellow_cycle);


        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();

        save.setOnClickListener(View -> {
            listener.setCycleNum(red.getText().toString().trim(),
                    green.getText().toString().trim(),
                    yellow.getText().toString().trim());
            dialog.dismiss();
        });
        cancel.setOnClickListener(View -> dialog.dismiss());
        dialog.show();
    }

    public interface SetIpListener {
        void save(String ip, String port);
    }

    public interface SetThreshold {
        void save(String temperature, String humidity, String Illumination, String CO2, String PM, String RoadCondition, boolean switchis);
    }

    public interface CarRechargeListener {
        void rechargeNum(int num);
    }

    public interface RGLSetListener {
        void setCycleNum(String redNum, String greenNum, String yellowNum);
    }

    //获取IP设置Dialogp
    public static void getIPDialog(Context context) {
        View view = View.inflate(context, R.layout.set_ip_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        dialog.show();
        EditText ed_ip = view.findViewById(R.id.ed_ip);
        EditText ed_port = view.findViewById(R.id.ed_port);
        Button cancel = view.findViewById(R.id.cancel);
        Button save = view.findViewById(R.id.save);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ed_port.addTextChangedListener(new TextWatcher() {
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
                int i = Integer.parseInt(s);
                if (i == 0) {
                    ed_port.setText("");
                } else if (i > 65535) {
                    Toast.makeText(context, "请输入0-65535", Toast.LENGTH_LONG).show();
                    ed_port.setText("");
                }

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences song = SPUtils.getDefault("song");
                SharedPreferences.Editor editor = SPUtils.getEditor(song);
                editor.putString("ipconfig", ed_ip.getText().toString());
                editor.putString("portconfig", ed_port.getText().toString());
                editor.commit();
                dialog.dismiss();
                Toast.makeText(context, "提交成功", Toast.LENGTH_LONG).show();

            }
        });

    }

    public static ProgressDialog progressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("请求中...");
        progressDialog.setMessage("数据获取中，请稍后。。。");
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        return progressDialog;
    }
}
