package cn.logcode.traffic.ui.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.MainGridAdapter;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.CarsBean;
import cn.logcode.traffic.bean.DBSense;
import cn.logcode.traffic.bean.Sense;
import cn.logcode.traffic.dao.SqliteHelp;
import cn.logcode.traffic.http.NullStringToEmptyAdapterFactory;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.DialogUtils;
import cn.logcode.traffic.utils.SPUtils;
import cn.logcode.traffic.utils.ToasUtils;
import cn.logcode.traffic.utils.network.MyCall;
import cn.logcode.traffic.utils.network.NetworkApi;
import cn.logcode.traffic.utils.network.OkhttpApi;

public class MainActivity extends BaseActivity {

    //侧滑我的账户
    public LinearLayout my_account;
    //红绿灯管理
    public LinearLayout rgl_manger;
    public LinearLayout news;
    //公交查询
    public LinearLayout bus_query;
    //    出行管理
    public LinearLayout chuxing;
    //账户管理
    public LinearLayout account_manger;
    //车辆违章
    private LinearLayout peccancy_manger;
    //监控抓拍
    private LinearLayout snap_manger;
    //个人中心
    private LinearLayout personal_center;
    //我的消息
    private LinearLayout dataAnalysis;
    //数据分析
    private LinearLayout my_message;
    //注销按钮
    private TextView zhuxiao;
    //账户管理
    private LinearLayout bill_manager;
    //生活助手
    private LinearLayout life_assistant;

    public List<CarsBean.DataBean> carsBeans;
    public int fazhi = 50;
    private Timer timer;
    private SharedPreferences song;
    private GridView gv_main;
    private MainGridAdapter adapter;
    private Timer timer1;
    private List<Integer> datas = new ArrayList<>();
    Sense.DataBean.GetsenseBean data;
    private Sense sense;
    SqliteHelp sqliteHelp;
    Dao<DBSense, ?> dao;
    private List<DBSense> dbSenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setYesDrawer();
        initView();
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initListener();


    }

    private void initView() {
        my_account = findViewById(R.id.my_account);
        rgl_manger = findViewById(R.id.rgl_manger);
        bus_query = findViewById(R.id.bus_query);
        account_manger = findViewById(R.id.account_manger);
        peccancy_manger = findViewById(R.id.peccancy_manger);
        snap_manger = findViewById(R.id.snap_manger);
        personal_center = findViewById(R.id.personal_center);
        my_message = findViewById(R.id.my_message);
        dataAnalysis = findViewById(R.id.dataAnalysis);
        zhuxiao = findViewById(R.id.tv_zhuxiao);
        gv_main = findViewById(R.id.gv_main);
        bill_manager = findViewById(R.id.bill_manager);
        chuxing = findViewById(R.id.chuxing);
        life_assistant = findViewById(R.id.life_assistant);
        news = findViewById(R.id.news);
    }

    private void init() throws Exception {
        song = SPUtils.getDefault("song");
        setTitle("首页-环境指标", null);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        datas.add(1);
        zhuxiao.setVisibility(View.VISIBLE);
        adapter = new MainGridAdapter(this, datas);
        gv_main.setAdapter(adapter);
        carsBeans = new ArrayList<>();
        sqliteHelp = new SqliteHelp(MainActivity.this);
        dao = sqliteHelp.getDao(DBSense.class);


        myOkhttp.myokhttp("https://easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/carlist ", "", new myRequestListener() {
            @Override
            public void success(String s) {
                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                CarsBean carsBean = gson.fromJson(s, CarsBean.class);
                carsBeans.clear();
                carsBeans.addAll(carsBean.getData());
                adapter.notifyDataSetChanged();
                if (carsBeans != null) {
                    startfazhi();
                    initsense();
                }
            }

            @Override
            public void failed(Exception e, String errorMsg) {
            }
        });
    }

    public void initListener() {

        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToasUtils.showToast(MainActivity.this, "点击了" + position);
                if (position == 0) {
                    DialogUtils.showSetThreshold(MainActivity.this, new DialogUtils.SetThreshold() {
                        @Override
                        public void save(String temperature, String humidity, String Illumination, String CO2, String PM, String RoadCondition, boolean switchis) {
                            SharedPreferences.Editor editor = SPUtils.getEditor(song);
                            editor.putString("温度", temperature);
                            editor.putString("湿度", humidity);
                            editor.putString("光照", Illumination);
                            editor.putString("CO2", CO2);
                            editor.putString("PM2.5", PM);
                            editor.putString("道路状态", RoadCondition);
                            editor.putBoolean("fazhistate", switchis);
                            editor.commit();

                        }
                    });
                }
            }
        });


        //我的账户
        my_account.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AccountActivity.class));
            drawerLayout.closeDrawers();
        });
        //红绿灯管理
        rgl_manger.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RGLigthMangerActivity.class));
            drawerLayout.closeDrawers();
        });
        //公交查询
        bus_query.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, BusqueryActivity.class));
            drawerLayout.closeDrawers();
        });
        //账户管理
        account_manger.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AccountMangerActivity.class));
            drawerLayout.closeDrawers();
        });
        //车辆违章
        peccancy_manger.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, PeccancyActivity.class));
            drawerLayout.closeDrawers();
        });

        //监控抓拍
        snap_manger.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SnapActivity.class));
            drawerLayout.closeDrawers();
        });
        //个人中心
        personal_center.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, PersonalActivity.class));
            drawerLayout.closeDrawers();
        });
        //我的消息
        my_message.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, MyMessageActivity.class));
            drawerLayout.closeDrawers();
        });
        //数据分析
        dataAnalysis.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, EnvironmentalActivity.class));
            drawerLayout.closeDrawers();
        });
        //注销登录
        zhuxiao.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("autologin", false);
            startActivity(intent);
            finish();
        });
        //账单管理
        bill_manager.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, BIllManagerActivity.class));
            drawerLayout.closeDrawers();
        });
        chuxing.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ChuxingActivity.class));
            drawerLayout.closeDrawers();
        });

        life_assistant.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LifeAssistantActivity.class));
            drawerLayout.closeDrawers();
        });
        news.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, NewsActivity.class));
            drawerLayout.closeDrawers();
        });
    }

    /**
     * 双击退出
     */
    private long isExitTime = 0;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawers();
        } else {
            if (System.currentTimeMillis() - isExitTime > 2000) {
                ToasUtils.showToast(getApplicationContext(), "再次点击退出");
                isExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }

    }

    public void startfazhi() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fazhi = song.getInt("fazhi", 50);
                if (carsBeans != null) {
                    for (CarsBean.DataBean bean : carsBeans
                            ) {
                        if (fazhi > bean.getMoney()) {
                            Log.i("-----------", "run: " + bean + "***************");
                            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            NotificationCompat.Builder compat = new NotificationCompat.Builder(MainActivity.this)
                                    .setSmallIcon(R.mipmap.setting_icon)
                                    .setContentText("车辆编号：" + bean.getNum() + "余额：" + bean.getMoney() + "阀值:" + fazhi)
                                    .setContentTitle("余额不足警告");
                            manager.notify(bean.getId(), compat.build());
                            timer.cancel();

                        }
                    }
                }

            }
        }, 0, 3000);
    }


    public void initsense() {
        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                OkhttpApi.senseapi(new MyCall() {
                    @Override
                    public void success(String json) {
                        Log.i("***************", "success: " + json);

                        try {
                            sense = NetworkApi.gson.fromJson(json, Sense.class);

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        datas.clear();
                        data = sense.getData().getGetsense();
                        for (int i = 0; i < 6; i++) {
                            datas.add(data.getTemperature());
                            datas.add(data.getHumidity());
                            datas.add(data.getLightIntensity());
                            datas.add(data.getCo2());
                            datas.add(data.getPm2_5());
                            datas.add(data.getAddress());
                        }

//                存记录到数据库
                        try {
                            dbSenses = dao.queryForAll();

                            if (dbSenses.size() >=20) {
                                dao.delete(dbSenses.get(0));
                            }
                            DBSense dbSense = new DBSense();
                            dao.create(dbSense);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "数据库操作失败", Toast.LENGTH_SHORT).show();
                        }

                        Log.i("*******+++++", "success: " + dbSenses.size());
                        adapter.notifyDataSetChanged();


                    }


                    @Override
                    public void failed() {

                    }
                });
            }
        }, 0, 3000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( timer==null&& timer1==null) {
            initsense();
            startfazhi();
        }
    }

    @Override
    protected void onStop() {
        timer.cancel();
        timer1.cancel();
        super.onStop();
    }
}
