package com.lwx.locker.feature.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.locker.R;
import com.lwx.locker.base.BaseActivity;
import com.lwx.locker.data.local.UserInfo;
import com.lwx.locker.feature.MainActivity;
import com.lwx.locker.util.RegexUtil;
import com.lwx.locker.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter<LoginView>> implements LoginView, View.OnClickListener {
    private TextView phone, password;
    private Button loginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        setListener();
    }


    private void init() {

        ToastUtils.init(true);
    }

    private void setListener() {
        loginBt.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.autoLogin();
    }

    @Override
    protected LoginPresenter<LoginView> setPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public String getPhone() {
        return phone.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void clearPhone() {
        phone.setText("");
    }

    @Override
    public void clearPassword() {
        password.setText("");
    }

    @Override
    public void toMainActivity(UserInfo userInfo) {
        //更新UserInfo到数据库，并跳转到MainActivity
        mPresenter.update(userInfo);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showFailedError(String err) {
        ToastUtils.error(this, err);
    }

    @Override
    public void showUserInfo(UserInfo userInfo) {
        phone.setText(userInfo.getPhone());
    }

    /**
     * 验证手机号及密码信息
     */
    private void submit() {
        if (TextUtils.isEmpty(phone.getText())) {
            ToastUtils.error(this, "手机号不能为空");
            return;
        } else if (!RegexUtil.isMobileNumber(phone.getText().toString())) {
            ToastUtils.error(this, "手机号不正确");
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            ToastUtils.error(this, "密码不能为空");
            return;
        }
        mPresenter.Login();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case 1:
                submit();
                break;
        }
    }
}

