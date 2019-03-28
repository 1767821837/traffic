package cn.logcode.traffic.ui.activity;

import android.app.DatePickerDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseActivity;

public class ChuxingActivity extends BaseActivity {
    TextView tv_time;
    TextView tv_depiction;
    private Calendar calendar;
    TextView tv_switchtext1;
    TextView tv_switchtext2;
    TextView tv_switchtext3;
    Switch switch1;
    Switch switch2;
    Switch switch3;
    ImageView img_red;
    ImageView img_gree;
    ImageView img_yell;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        // 获取当前的年
        year = calendar.get(calendar.YEAR);
        // 获取当前的月
        month = calendar.get(calendar.MONTH);
        // 获取当前月的第几天
        day = calendar.get(calendar.DAY_OF_MONTH);
        super.onCreate(savedInstanceState);
        setTitle("出行管理");
        setLeft_menu(R.mipmap.back, view -> onBackPressed());
        setContentView(R.layout.activity_chuxing);
        initview();
        initListen();
        initdata(year, month, day);
    }


    private void initListen() {


        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(ChuxingActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(i + "年");
                        sb.append(i1 + "月");
                        sb.append(i2 + "日");
                        tv_time.setText(sb.toString());
                        year = i;
                        month = i1;
                        day = i2;


                        initdata(i, i1, i2);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
    }

    private void initview() {
        tv_time = findViewById(R.id.tv_time);
        tv_depiction = findViewById(R.id.tv_depiction);
        tv_switchtext1 = findViewById(R.id.tv_switchtext1);
        tv_switchtext2 = findViewById(R.id.tv_switchtext2);
        tv_switchtext3 = findViewById(R.id.tv_switchtext3);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        img_red = findViewById(R.id.img_red);
        img_gree = findViewById(R.id.img_gree);
        img_yell = findViewById(R.id.img_yellow);
        StringBuffer sb = new StringBuffer();
        sb.append(year + "年");
        sb.append(month + "月");
        sb.append(day + "日");
        tv_time.setText(sb.toString());
    }

    private void initdata(int years, int months, int days) {

        if (days % 2 == 0) {
            tv_switchtext1.setText("关");
            tv_switchtext2.setText("开");
            tv_switchtext3.setText("关");

            switch1.setChecked(false);
            switch1.setEnabled(false);
            switch2.setChecked(true);
            switch3.setEnabled(false);
            switch2.setEnabled(true);
            switch3.setChecked(false);
            tv_depiction.setText("双号出行车辆：2");
        } else {
            tv_switchtext1.setText("开");
            tv_switchtext2.setText("关");
            tv_switchtext3.setText("开");
            switch1.setChecked(true);
            switch1.setEnabled(true);
            switch2.setEnabled(false);
            switch3.setEnabled(true);
            switch2.setChecked(false);
            switch3.setChecked(true);
            tv_depiction.setText("单号出行车辆：1、3");
        }

//加载动画
        AnimationDrawable drawablered = (AnimationDrawable) getResources().getDrawable(R.drawable.animationred);
        AnimationDrawable drawablegree = (AnimationDrawable) getResources().getDrawable(R.drawable.animationgree);
        AnimationDrawable drawableyellow = (AnimationDrawable) getResources().getDrawable(R.drawable.animationyellow);


        img_red.setBackground(drawablered);
        img_gree.setBackground(drawablegree);
        img_yell.setBackground(drawableyellow);
        drawablered.start();
        drawablegree.start();
        drawableyellow.start();
    }
}
