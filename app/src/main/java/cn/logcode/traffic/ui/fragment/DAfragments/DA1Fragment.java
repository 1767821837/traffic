package cn.logcode.traffic.ui.fragment.DAfragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseFragment;
import cn.logcode.traffic.bean.Sense;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.network.MyCall;
import cn.logcode.traffic.utils.network.NetworkApi;
import cn.logcode.traffic.utils.network.OkhttpApi;

public class DA1Fragment extends BaseFragment {
    LineChart linechar;
    Timer timer;
    private List<String> xvalur;
    private List<Entry> entryList;
    private Sense sense;
    private SimpleDateFormat format;
    private String date;
    private List<String> xvalurcopy;
    private LineDataSet lineDataSet;
    private LineData data1;
    private Date date2;
    private int humidity;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        linechar = view.findViewById(R.id.linechar);
        initchart();

    }

    private void initchart() {
        format = new SimpleDateFormat("mm:ss");
        XAxis xAxis = linechar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelsToSkip(0);
//        xAxis.setDrawAxisLine(false);
//设置x轴上每个点对应的线
        xAxis.setDrawGridLines(false);
        //绘制标签  指x轴上的对应数值
        xAxis.setDrawLabels(true);
        YAxis leftAxis = linechar.getAxisLeft();//取得左侧y轴
        YAxis reightAxis = linechar.getAxisRight();//取得左侧y轴
        reightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//y轴标签绘制的位置
        reightAxis.setDrawAxisLine(true);
        reightAxis.setDrawTopYLabelEntry(false);
        reightAxis.setDrawLabels(false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//y轴标签绘制的位置
        leftAxis.setDrawAxisLine(true);
        leftAxis.setAxisMinValue(15);
        leftAxis.setAxisMaxValue(25);
        leftAxis.setStartAtZero(false);//从0开始
        leftAxis.setLabelCount(5, false);
        xvalur = new ArrayList<>();
        date2 = new Date();
        for (int i = 0; i < 20; i++) {
            xvalur.add(format.format(date2));
            date2.setTime(date2.getTime() + 3000);
        }
        entryList = new ArrayList<>();
        xvalurcopy = new ArrayList<>();
        lineDataSet = new LineDataSet(entryList, "");
        lineDataSet.setDrawValues(false);
        lineDataSet.setCircleColor(Color.parseColor("#FF7D7D7E"));
        lineDataSet.setColor(Color.parseColor("#FF7D7D7E"));
        lineDataSet.setLineWidth(3f);
        lineDataSet.setCircleSize(5f);
        lineDataSet.setDrawCircleHole(false);//实心
        data1 = new LineData(xvalur, lineDataSet);
        Legend legend = linechar.getLegend();
        legend.setEnabled(false);
        linechar.setData(data1);
        linechar.setDescription("");
        initsense();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_da1;
    }

    public void initsense() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                OkhttpApi.temperature(new MyCall() {
                    @Override
                    public void success(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONObject object = jsonObject.getJSONObject("data");
                            humidity = object.getInt("temperature");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), json, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (entryList.size() >= 20) {
                            date = format.format(date2.getTime() + 3000);
                            date2.setTime(date2.getTime() + 3000);
                            xvalur.remove(0);
                            xvalur.add(date);
                            entryList.remove(0);
                        }

                        if (entryList.size() >= 19) {
                            for (int i = 0; i < entryList.size(); i++) {
                                entryList.get(i).setXIndex(i);
                            }
                        }
                        entryList.add(new Entry(humidity, entryList.size()));
                        linechar.setData(data1);
                        linechar.invalidate();//refresh
                    }

                    @Override
                    public void failed() {

                    }
                });

            }
        }, 0, 3000);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
