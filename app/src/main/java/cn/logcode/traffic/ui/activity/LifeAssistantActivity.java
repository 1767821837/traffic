package cn.logcode.traffic.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.LiftviewpageAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.LifeBean;
import cn.logcode.traffic.bean.WeatherBean;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.DialogUtils;
import cn.logcode.traffic.utils.network.MyCall;
import cn.logcode.traffic.utils.network.NetworkApi;
import cn.logcode.traffic.utils.network.OkhttpApi;

public class LifeAssistantActivity extends BaseActivity {
    TextView tv_now;
    TextView tv_teday;
    TextView tv_day1;
    TextView tv_day2;
    TextView tv_day3;
    TextView tv_uitravioletrays_tips;
    TextView tv_uitravioletrays_body;
    TextView tv_cold_tips;
    TextView tv_cold_body;
    TextView tv_clothes_tips;
    TextView tv_clothes_body;
    TextView tv_sport_tips;
    TextView tv_sport_body;
    TextView tv_atmosphere_tips;
    TextView tv_atmosphere_body;
    TextView tv_bad;
    ViewPager life_viewpager;
    ImageView img_rush;
    LineChart lineChart;
    private WeatherBean weatherBean;
    List<Entry> entries1;
    List<String> xvalue;
    List<Entry> entries2;
    private Date date;
    private SimpleDateFormat dateFm;
    private LineDataSet linedataset1;
    private LineDataSet linedataset2;
    private LineData lineData1;
    private LifeBean lifeBean;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant);
        setTitle("生活助手");
        setLeft_menu(R.mipmap.back,view ->onBackPressed());
        initview();
        initdata();
    }

    private void initdata() {
        progressDialog = DialogUtils.progressDialog(LifeAssistantActivity.this);
        progressDialog.show();
        date = new Date();
        dateFm = new SimpleDateFormat("EEEE");
        entries1 = new ArrayList<>();
        xvalue = new ArrayList<>();
        entries2 = new ArrayList<>();
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setAxisLineColor(Color.TRANSPARENT);
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        YAxis leftyAxis = lineChart.getAxisLeft();
        leftyAxis.setAxisMinValue(0);
        leftyAxis.setAxisMaxValue(25);
        leftyAxis.setLabelCount(5, true);
        leftyAxis.setDrawLabels(false);
        leftyAxis.setDrawAxisLine(false);
//        leftyAxis.setDrawGridLines(false);
        YAxis rightyAxis = lineChart.getAxisRight();
        rightyAxis.setEnabled(false);
        getweather();
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        lineChart.setDescription("");
        tv_day1.setText(dateFm.format(date.getTime() + 2 * 60 * 60 * 24 * 1000));
        tv_day2.setText(dateFm.format(date.getTime() + 3 * 60 * 60 * 24 * 1000));
        tv_day3.setText(dateFm.format(date.getTime() + 4 * 60 * 60 * 24 * 1000));
        img_rush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initdata();
            }
        });


    }

    private void initview() {
        tv_now = findViewById(R.id.tv_now);
        tv_teday = findViewById(R.id.tv_teday);
        lineChart = findViewById(R.id.linechart);
        tv_day1 = findViewById(R.id.tv_day1);
        tv_day2 = findViewById(R.id.tv_day2);
        tv_day3 = findViewById(R.id.tv_day3);
        img_rush = findViewById(R.id.img_rush);
        tv_uitravioletrays_tips = findViewById(R.id.tv_uitravioletrays_tips);
        tv_uitravioletrays_body = findViewById(R.id.tv_uitravioletrays_body);
        tv_cold_tips = findViewById(R.id.tv_cold_tips);
        tv_cold_body = findViewById(R.id.tv_cold_body);
        tv_clothes_tips = findViewById(R.id.tv_clothes_tips);
        tv_clothes_body = findViewById(R.id.tv_clothes_body);
        tv_sport_tips = findViewById(R.id.tv_sport_tips);
        tv_sport_body = findViewById(R.id.tv_sport_body);
        tv_atmosphere_tips = findViewById(R.id.tv_atmosphere_tips);
        tv_atmosphere_body = findViewById(R.id.tv_atmosphere_body);
        tv_bad = findViewById(R.id.tv_bad);
        life_viewpager = findViewById(R.id.life_viewpager);
        LiftviewpageAdapter adapter = new LiftviewpageAdapter(getSupportFragmentManager());
        life_viewpager.setAdapter(adapter);
    }

    public void getweather() {

        OkhttpApi.weather(new MyCall() {
            @Override
            public void success(String json) {
                try {
                    entries1.clear();
                    entries2.clear();
                    xvalue.clear();
                    weatherBean = NetworkApi.gson.fromJson(json, WeatherBean.class);
                    tv_now.setText(weatherBean.data.get(0).now + "°");
                    tv_teday.setText("今天：" + weatherBean.data.get(0).day + "℃");
                    for (int i = 0; i < weatherBean.data.size(); i++) {
                        String str = weatherBean.data.get(i).day;
                        String[] split = str.split("--");
                        entries1.add(new Entry(Integer.parseInt(split[0]), i));
                        entries2.add(new Entry(Integer.parseInt(split[1]), i));
                        xvalue.add("" + i);
                    }
                    linedataset1 = new LineDataSet(entries1, "");
                    linedataset1.setColor(Color.parseColor("#3F51B5"));
                    linedataset1.setDrawCircleHole(false);
                    linedataset1.setCircleColor(Color.parseColor("#3F51B5"));
                    linedataset2 = new LineDataSet(entries2, "");
                    linedataset2.setColor(Color.parseColor("#FF0000"));
                    linedataset2.setDrawCircleHole(false);
                    linedataset2.setCircleColor(Color.parseColor("#FF0000"));
                    lineData1 = new LineData(xvalue, linedataset1);
                    lineData1.addDataSet(linedataset2);
                    lineData1.setValueFormatter(new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                            return (int) entry.getVal() + "";
                        }
                    });
                    lineChart.setData(lineData1);
                    lineChart.invalidate();
                    getlife();
                } catch (Exception e) {
                    return;
                }
            }

            @Override
            public void failed() {

            }
        });
    }

    private void getlife() {
        lifeBean = new LifeBean();
        myOkhttp.myokhttp("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/life", "", new myRequestListener() {
            @Override
            public void success(String s) {

                try {
                    lifeBean = new Gson().fromJson(s, LifeBean.class);
                    if (lifeBean.data.uitravioletrays >= 1000 && lifeBean.data.uitravioletrays <= 3000) {
                        tv_uitravioletrays_tips.setText("中等(" + lifeBean.data.uitravioletrays + ")");
                        tv_uitravioletrays_body.setText("涂擦SPF大于15、PA+防晒护肤品");
                    } else if (lifeBean.data.uitravioletrays > 0 && lifeBean.data.uitravioletrays < 1000) {
                        tv_uitravioletrays_tips.setText("弱(" + lifeBean.data.uitravioletrays + ")");
                        tv_uitravioletrays_body.setText("辐射较弱，涂擦SPF12~15、PA+护肤品辐射较弱，涂擦SPF12~15、PA+护肤品");
                    } else {
                        tv_uitravioletrays_tips.setText("强(" + lifeBean.data.uitravioletrays + ")");
                        tv_uitravioletrays_body.setText("尽量减少外出，需要涂抹高倍数防晒霜");
                    }
                    if (weatherBean.data.get(0).now > 0 && weatherBean.data.get(0).now < 8) {
                        tv_cold_tips.setText("较易发(" + weatherBean.data.get(0).now + ")");
                        tv_cold_body.setText("温度低，风较大，较易发生感冒，注意防护");
                    } else {
                        tv_cold_tips.setText("少发(" + weatherBean.data.get(0).now + ")");
                        tv_cold_body.setText("无明显降温，感冒机率较低");
                    }
                    if (weatherBean.data.get(0).now > 0 && weatherBean.data.get(0).now < 12) {
                        tv_clothes_tips.setText("冷(" + weatherBean.data.get(0).now + ")");
                        tv_clothes_body.setText("建议穿长袖衬衫、单裤等服装");
                    } else if (weatherBean.data.get(0).now >= 12 && weatherBean.data.get(0).now <= 21) {
                        tv_clothes_tips.setText("舒适(" + weatherBean.data.get(0).now + ")");
                        tv_clothes_body.setText("建议穿短袖衬衫、单裤等服装");
                    } else {
                        tv_clothes_tips.setText("热(" + weatherBean.data.get(0).now + ")");
                        tv_clothes_body.setText("适合穿T恤、短薄外套等夏季服装");
                    }


                    if (lifeBean.data.sport > 0 && lifeBean.data.sport < 3000) {
                        tv_sport_tips.setText("适宜(" + lifeBean.data.sport + ")");
                        tv_sport_body.setText("气候适宜，推荐您进行户外运动");
                    } else if (lifeBean.data.sport >= 3000 && lifeBean.data.sport <= 6000) {
                        tv_sport_tips.setText("中(" + lifeBean.data.sport + ")");
                        tv_sport_body.setText("易感人群应适当减少室外活动");
                    } else {
                        tv_sport_tips.setText("较不宜(" + lifeBean.data.sport + ")");
                        tv_sport_body.setText("空气氧气含量低，请在室内进行休闲运动");
                    }
                    if (lifeBean.data.atmosphere > 0 && lifeBean.data.atmosphere < 30) {
                        tv_atmosphere_tips.setText("优(" + lifeBean.data.atmosphere + ")");
                        tv_atmosphere_body.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                    } else if (lifeBean.data.sport >= 30 && lifeBean.data.atmosphere <= 100) {
                        tv_atmosphere_tips.setText("良(" + lifeBean.data.atmosphere + ")");
                        tv_atmosphere_body.setText("易感人群应适当减少室外活动");
                    } else {
                        tv_atmosphere_tips.setText("污染(" + lifeBean.data.atmosphere + ")");
                        tv_atmosphere_body.setText("空气质量差，不适合户外活动");
                    }

                    progressDialog.dismiss();
                } catch (Exception e) {
                    progressDialog.dismiss(); 
                    return;
                }
            }

            @Override
            public void failed(Exception e, String errorMsg) {

            }
        });


    }
}
