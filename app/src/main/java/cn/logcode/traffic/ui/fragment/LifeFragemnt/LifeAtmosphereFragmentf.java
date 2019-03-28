package cn.logcode.traffic.ui.fragment.LifeFragemnt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.bean.LifeBean;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;

public class LifeAtmosphereFragmentf extends Fragment {

    private BarChart barChart;
    private Timer timer;
    private LifeBean lifeBean;
    private TextView tv_bad;
    private List<String> xvalue;
    private List<BarEntry> entryList;
    private Date date;
    private SimpleDateFormat format;
    private BarDataSet bardataset;
    private BarData bardata;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.life1fragment, container, false);
        tv_bad = view.findViewById(R.id.tv_bad);
        barChart = view.findViewById(R.id.barchart);
        initwidget();
        initdate();
        return view;
    }

    private void initwidget() {
        date = new Date();
        format = new SimpleDateFormat("ss");
        xvalue = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            xvalue.add(format.format(date));
            date.setTime(date.getTime() + 3000);
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelsToSkip(0);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawAxisLine(false);

        entryList = new ArrayList<>();
        YAxis leftyaxis = barChart.getAxisLeft();
        YAxis reightyaxis = barChart.getAxisRight();
        reightyaxis.setEnabled(false);
//        leftyaxis.setDrawGridLines(false);
        leftyaxis.setDrawAxisLine(false);
        leftyaxis.setAxisMinValue(0);
        leftyaxis.setAxisMaxValue(198);
        leftyaxis.setLabelCount(12, true);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
        barChart.setDescription("");
    }

    private void initdate() {
        lifeBean = new LifeBean();
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                myOkhttp.myokhttp("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/life", "", new myRequestListener() {
                    @Override
                    public void success(String s) {
                        try {
                            lifeBean = new Gson().fromJson(s, LifeBean.class);


                            if (entryList.size() >= 20) {
                                entryList.remove(0);
                                xvalue.remove(0);
                                xvalue.add(format.format(date.getTime() + 3000));
                                date.setTime(date.getTime() + 3000);
                            }
                            entryList.add(new BarEntry(lifeBean.data.atmosphere, entryList.size()));
                            if (entryList.size() >= 20) {

                                for (int i = 0; i < entryList.size(); i++) {
                                    entryList.get(i).setXIndex(i);
                                }
                            }
                            int maxnum = 0;
                            for (int i = 0; i < entryList.size(); i++) {
                                if (maxnum < (int) entryList.get(i).getVal()) {
                                    maxnum = (int) entryList.get(i).getVal();
                                }

                            }
                            tv_bad.setText("过去一分钟空气最差值：" + maxnum);
                            Log.i("*****", "success: " + "entryList" + entryList.size() + "xvalue" + xvalue.size());
                            bardataset = new BarDataSet(entryList, "");
                            bardataset.setBarSpacePercent(10f);
                            bardataset.setDrawValues(false);
                            bardataset.setColor(Color.parseColor("#FFABABAB"));
                            bardata = new BarData(xvalue, bardataset);

                            barChart.setData(bardata);
                            barChart.invalidate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Exception e, String errorMsg) {

                    }
                });
            }
        }, 0, 1000);


    }

    @Override
    public void onStart() {
        super.onStart();
        if (timer == null) {
            initdate();
        }
    }

    @Override
    public void onStop() {
        timer.cancel();
        super.onStop();
    }
}
