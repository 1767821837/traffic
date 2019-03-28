package cn.logcode.traffic.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import cn.logcode.traffic.R;

public class SnapVideoActivity extends AppCompatActivity {

    private VideoView videoview;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_video);
        initdata();
        initView();
    }

    private void initdata() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
    }

    private void initView() {
        videoview = findViewById(R.id.videoview);
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + name));
        videoview.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoview.pause();
    }
}
