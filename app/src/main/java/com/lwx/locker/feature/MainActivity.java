package com.lwx.locker.feature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.lwx.locker.R;
import com.lwx.locker.base.BaseActivity;
import com.lwx.locker.base.BasePresenter;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListener();
    }

    @Override
    protected BasePresenter setPresenter() {
        return null;
    }

    /**
     * 初始化控件
     */
    private void init() {
        radioGroup = findViewById(R.id.radio_group);
    }

    /**
     * 设置事件
     */
    private void setListener() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch (checkId) {
            case R.id.main_home:

                break;
            case R.id.main_data:

                break;
            case R.id.main_recreation:

                break;
            default:

                break;
        }
    }

    private void showFrament(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, fragment).commit();
    }
}
