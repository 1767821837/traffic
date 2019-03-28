package cn.logcode.traffic.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.j256.ormlite.stmt.query.In;

import cn.logcode.traffic.R;
import cn.logcode.traffic.config.AppConfig;
import cn.logcode.traffic.utils.SPUtils;

public class SplashActivity extends AppCompatActivity {


    Handler handler = new Handler();
    boolean isFrist ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        SharedPreferences aDefault = SPUtils.getDefault("song");
        isFrist =  aDefault.getBoolean("firstsplash",false);
        if(isFrist){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

                    SharedPreferences song = SPUtils.getDefault("song");
                    SharedPreferences.Editor editor = SPUtils.getEditor(song);
                    editor.putBoolean("firstsplash",true);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }
            },3000);
        }
    }
}
