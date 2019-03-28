package cn.logcode.traffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.utils.ToasUtils;

/**
 * 车辆违章
 */
public class PeccancyActivity extends BaseActivity {

    private EditText ed_licenseplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peccancy);
        setTitle("车辆违章");
        setLeft_menu(R.mipmap.back,view -> onBackPressed());
        ed_licenseplate = findViewById(R.id.ed_licenseplate);
    }

    public void ViolationInquiry(View view) {
        Intent intent = new Intent(this,QueryResultActivity.class);
        String licenseplate = "鲁"+ed_licenseplate.getText().toString().trim();
        if (licenseplate.equals("鲁")||licenseplate.isEmpty()||licenseplate=="") {
            ToasUtils.showToast(this,"车牌不能为空");
        }else {
            intent.putExtra("licenseplate",licenseplate);
            startActivity(intent);
        }

    }
}
