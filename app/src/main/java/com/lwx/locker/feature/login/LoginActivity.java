package com.lwx.locker.feature.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lwx.locker.R;
import com.lwx.locker.base.BaseActivity;
import com.lwx.locker.custom.dialog.Mydialog;
import com.lwx.locker.data.local.UserInfo;
import com.lwx.locker.feature.MainActivity;
import com.lwx.locker.feature.register.RegisterActivity;
import com.lwx.locker.util.RegexUtil;
import com.lwx.locker.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter<LoginView>> implements LoginView, View.OnClickListener {
    private TextView phone, password, register, forgetPassword;
    private Button loginBt;
    private Mydialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTranslucent(this, 50);

        init();
        setListener();
    }


    private void init() {
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        forgetPassword = findViewById(R.id.forget_password);
        loginBt = findViewById(R.id.login);
        ToastUtils.init(true);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                UserInfo userInfo = bundle.getParcelable("userInfo");
                phone.setText(userInfo.getPhone());
            }
        }

        mydialog = new Mydialog(this);
    }

    private void setListener() {
        loginBt.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
    }

    @Override
    protected LoginPresenter<LoginView> setPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    public void showLoading() {
        if (!mydialog.isShowing()) {
            mydialog.show();
            mydialog.setMessage("正在登录...");
        }
    }

    @Override
    public void hideLoading() {
        if (mydialog.isShowing()) {
            mydialog.dismiss();
        }
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
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("userInfo", userInfo);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        finish();
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
        } else if (!RegexUtil.isMobileNumber(phone.getText().toString().trim())) {
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
            case R.id.login:
                submit();
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.forget_password:

                break;
            default:
                break;
        }
    }
}

