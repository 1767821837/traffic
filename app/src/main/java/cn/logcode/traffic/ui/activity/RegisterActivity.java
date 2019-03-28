package cn.logcode.traffic.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.bean.BaseBean;
import cn.logcode.traffic.bean.NullBean;
import cn.logcode.traffic.bean.UserBean;
import cn.logcode.traffic.config.AppConfig;
import cn.logcode.traffic.http.RequestListener;
import cn.logcode.traffic.http.request.RegisterRequest;
import cn.logcode.traffic.utils.DialogUtils;
import cn.logcode.traffic.utils.LiteUtil;
import cn.logcode.traffic.utils.ToasUtils;
import cn.logcode.traffic.utils.Utils;

public class RegisterActivity extends BaseActivity {

    Button cancel;
    Button register;
    EditText ed_userName;
    EditText ed_userPass;
    EditText ed_againPass;
    EditText ed_phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }

}
