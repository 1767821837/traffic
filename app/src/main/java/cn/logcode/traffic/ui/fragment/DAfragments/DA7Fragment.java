package cn.logcode.traffic.ui.fragment.DAfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseFragment;
import cn.logcode.traffic.bean.Sense;
import cn.logcode.traffic.utils.network.MyCall;
import cn.logcode.traffic.utils.network.OkhttpApi;

public class DA7Fragment extends BaseFragment {
    LineChart linechar;
    Timer timer;
    private List<String> xvalur;
    private List<Entry> entryList;
    private Sense sense;
    private SimpleDateFormat format;
    private String date;
    private List<String> xvalurcopy;
    private List<Entry> entryListcopy;
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
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = linechar.getAxisLeft();//取得左侧y轴
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//y轴标签绘制的位置
        leftAxis.setDrawAxisLine(false);
        YAxis reightAxis = linechar.getAxisRight();//取得左侧y轴
        reightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//y轴标签绘制的位置
        reightAxis.setDrawAxisLine(false);
        reightAxis.setDrawAxisLine(false);
        reightAxis.setDrawTopYLabelEntry(false);

        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(100);
        leftAxis.setStartAtZero(false);
        leftAxis.setLabelCount(0, true);

        xvalur = new ArrayList<>();
        date2 = new Date();
        for (int i = 0; i < 20; i++) {
            xvalur.add(format.format(date2));
            date2.setTime(date2.getTime() + 3000);
        }

        entryList = new ArrayList<>();
        xvalurcopy = new ArrayList<>();
        entryListcopy = new ArrayList<>();
        lineDataSet = new LineDataSet(entryList, "");
        lineDataSet.setColor(Color.parseColor("#000000"));
        data1 = new LineData(xvalur, lineDataSet);
        linechar.setData(data1);
        linechar.setDescription("");
        initsense();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_da2;
    }

    public void initsense() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                OkhttpApi.co2(new MyCall() {

                    @Override
                    public void success(String json) {

                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONObject object = jsonObject.getJSONObject("data");
                            humidity = object.getInt("CO2");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), json, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (entryList.size() >= 20) {
                            date = format.format(date2.getTime() + 3000);
                            date2.setTime(date2.getTime() + 3000);
                            xvalurcopy.clear();
                            xvalur.remove(0);
                            xvalurcopy.addAll(xvalur);
                            xvalur.clear();
                            xvalurcopy.add(date);
                            xvalur.addAll(xvalurcopy);
                            entryList.remove(0);

                        }
                        entryListcopy.clear();


                        if(entryList.size() >= 19){
                           for (int i = 0; i<entryList.size();i++){
                               entryListcopy.add(new Entry(entryList.get(i).getVal(),i));
                           }
                        }else{
                            entryListcopy.addAll(entryList);
                        }
                        entryListcopy.add(new Entry(humidity, entryListcopy.size()));
                        for (int j = 0; j < xvalur.size(); j++) {
                            Log.i("@@@@@@@@@@@@@@", "success: " + xvalur.get(j));

                        }
                        entryList.clear();
                        entryList.addAll(entryListcopy);
                        linechar.setData(data1);
                        linechar.invalidate();//refresh
                        linechar.refreshDrawableState();
                        linechar.notifyDataSetChanged();

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
