package cn.logcode.traffic.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import cn.logcode.traffic.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private ListView mList;
    private Button mBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mList = (ListView) findViewById(R.id.list);
        mBut = (Button) findViewById(R.id.but);

        mBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but:

                break;
        }
    }
}
