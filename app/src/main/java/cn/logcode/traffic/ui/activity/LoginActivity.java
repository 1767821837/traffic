package cn.logcode.traffic.ui.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseActivity;
import cn.logcode.traffic.http.request.myOkhttp;
import cn.logcode.traffic.http.request.myRequestListener;
import cn.logcode.traffic.utils.DialogUtils;
import cn.logcode.traffic.utils.SPUtils;
import cn.logcode.traffic.utils.ToasUtils;

public class LoginActivity extends BaseActivity {
    Button register;
    Button login;
    EditText ed_username;
    EditText ed_userpass;
    CheckBox save_pass;
    CheckBox auto_login;
    String username;
    String userpw;
    SharedPreferences song = SPUtils.getDefault("song");
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showDesc();
        initview();
        initdata();

        desc_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.getIPDialog(LoginActivity.this);

            }
        });

        auto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean checked = auto_login.isChecked();
                if (checked) {
                    save_pass.setChecked(checked);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = DialogUtils.progressDialog(LoginActivity.this);
                progressDialog.show();
                boolean save = save_pass.isChecked();
//                    网络请求
                myOkhttp.myokhttp("https://easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/login", "{\"username\":\"" + ed_username.getText().toString() + "\",\"password\":\"" + ed_userpass.getText().toString() + "\"}", new myRequestListener() {
                    @Override
                    public void success(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int code = jsonObject.getInt("code");

                            if (code == 1) {
                                JSONObject object = jsonObject.getJSONObject("data");
                                String name = object.getString("username");
                                if (save) {
                                    saveData();
                                } else {
                                    savenull();
                                }
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                                progressDialog.dismiss();
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToasUtils.showToast(LoginActivity.this, "账号密码错误");
                                        savenull();
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "网络异常", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failed(Exception e, String errorMsg) {
                        Toast.makeText(getApplicationContext(), "网络异常", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

            }


        });
    }

    private void initview() {
        tool_bar.setVisibility(View.VISIBLE);
        setTitle("登录");
        setYesDrawer();
        left_menu.setVisibility(View.GONE);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        ed_username = findViewById(R.id.ed_username);
        ed_userpass = findViewById(R.id.ed_userpass);
        save_pass = findViewById(R.id.save_pass);
        auto_login = findViewById(R.id.auto_login);
    }

    private void initdata() {

        boolean save = song.getBoolean("save", false);
        if (save) {
            ed_username.setText(song.getString("username", ""));
            ed_userpass.setText(song.getString("userpass", ""));
            save_pass.setChecked(save);
        }


    }

    private void saveData() {
        boolean save = save_pass.isChecked();
        boolean autologin = auto_login.isChecked();
        String name = ed_username.getText().toString();
        String pass = ed_userpass.getText().toString();
        SharedPreferences.Editor editor = SPUtils.getEditor(song);
        editor.putString("username", name);
        editor.putString("userpass", pass);
        editor.putBoolean("save", save);
        editor.putBoolean("auto", autologin);
        editor.commit();
    }

    private void savenull() {
        SharedPreferences.Editor editor = SPUtils.getEditor(song);
        editor.putBoolean("save", false);
        editor.putBoolean("auto", false);
        editor.putString("username", "");
        editor.putString("userpass", "");
        editor.commit();
    }
}

