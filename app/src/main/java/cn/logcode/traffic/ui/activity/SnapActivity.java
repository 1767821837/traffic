package cn.logcode.traffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import cn.logcode.traffic.R;
import cn.logcode.traffic.adapter.GridviewVideoAdapter;
import cn.logcode.traffic.base.BaseActivity;

public class SnapActivity extends BaseActivity {
    GridView gridview_video;
    private GridviewVideoAdapter adapter;
    List<String> videolist = new ArrayList<>();

    //这里Fragment必须给初始值，否则下面做返回判断时会报空
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap);
        setTitle("监控抓拍");
        setLeft_menu(R.mipmap.back, view -> onBackPressed());
        initdata();
        initview();
    }

    private void initdata() {
        videolist.add("video1");
        videolist.add("video2");
        videolist.add("video3");
        videolist.add("video4");
        videolist.add("video5");
        videolist.add("video6");
        videolist.add("video7");
        videolist.add("video8");
        videolist.add("video9");
        videolist.add("video10");
    }

    private void initview() {
        gridview_video = findViewById(R.id.gridview_video);
        adapter = new GridviewVideoAdapter(SnapActivity.this, videolist);
        gridview_video.setAdapter(adapter);
        gridview_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SnapActivity.this, SnapVideoActivity.class);
                intent.putExtra("name", videolist.get(i));
                startActivity(intent);

            }
        });
    }


}
