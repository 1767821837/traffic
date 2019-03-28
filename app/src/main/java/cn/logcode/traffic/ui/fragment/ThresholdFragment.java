package cn.logcode.traffic.ui.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.logcode.traffic.R;
import cn.logcode.traffic.base.BaseFragment;
import cn.logcode.traffic.ui.activity.PersonalActivity;
import cn.logcode.traffic.utils.SPUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThresholdFragment extends BaseFragment {
    private SharedPreferences song;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        song = SPUtils.getDefault("song");
        editor = SPUtils.getEditor(song);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        TextView tv_warning;
        TextView et_setting_warning;
        Button btn_setting;

        tv_warning = view.findViewById(R.id.tv_warning);
        et_setting_warning = view.findViewById(R.id.et_setting_warning);
        btn_setting = view.findViewById(R.id.btn_setting);
        int fazhi = song.getInt("fazhi", 50);
        tv_warning.setText(fazhi + "");
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = et_setting_warning.getText().toString();
                editor.putInt("fazhi", Integer.parseInt(string));
                editor.commit();
                tv_warning.setText(string);

            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_threshold;
    }

}
