package com.lwx.locker.feature.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lwx.locker.R;
import com.lwx.locker.base.BaseActivity;
import com.lwx.locker.custom.dialog.Mydialog;
import com.lwx.locker.data.local.UserInfo;
import com.lwx.locker.feature.MainActivity;
import com.lwx.locker.util.ConstantUtils;
import com.lwx.locker.util.RegexUtil;
import com.lwx.locker.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter<RegisterView>> implements RegisterView, View.OnClickListener {
    /**
     * 返回
     */
    private ImageView mBack;
    /**
     * 注册
     */
    private Button mRegister;
    /**
     * 同意条约
     */
    private TextView mAgree;
    /**
     * 手机号
     */
    private EditText mPhone;
    /**
     * 密码
     */
    private EditText mPassword;
    /**
     * 确认密码
     */
    private EditText mAffirmPassword;
    /**
     * 验证码
     */
    private EditText mCode;
    /**
     * 获取验证码
     */
    private TextView mGetCode;

    private Mydialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarUtil.setTranslucent(this, 50);

        init();
        setListener();
    }

    private void init() {
        mBack = findViewById(R.id.back);
        mRegister = findViewById(R.id.register);
        mPhone = findViewById(R.id.phone);
        mPassword = findViewById(R.id.password);
        mAffirmPassword = findViewById(R.id.affirm_password);
        mCode = findViewById(R.id.code);
        mGetCode = findViewById(R.id.get_code);
        mAgree = findViewById(R.id.agree);

        ToastUtils.init(true);
        mydialog = new Mydialog(this);
    }

    private void setListener() {
        mBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mAgree.setOnClickListener(this);
        mGetCode.setOnClickListener(this);
    }

    @Override
    protected RegisterPresenter<RegisterView> setPresenter() {
        return new RegisterPresenter<>();
    }

    @Override
    public void showLoading() {
        if (!mydialog.isShowing()) {
            mydialog.show();
            mydialog.setMessage("正在注册...");
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
        return this.bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    public String getPhone() {
        return mPhone.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString().trim();
    }

    @Override
    public String getCode() {
        return mCode.getText().toString().trim();
    }

    @Override
    public void toMainActivity(UserInfo userInfo) {
        ToastUtils.success(this, "注册成功");
        //跳转到MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("userInfo", userInfo);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        finish();
    }

    @Override
    public void showFailedError(String error) {
        ToastUtils.error(this, error);
    }

    @Override
    public void showCountDownTime(String countDownTime) {
        mGetCode.setText(countDownTime);
    }

    @Override
    public void codeSuccessful() {
        ToastUtils.success(this, getResources().getString(R.string.code_has_been_issued));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                finish();
                break;
            case R.id.register:
                register();
                break;
            case R.id.get_code:
                verifyPhone();
                break;
            case R.id.agree:

                break;
            default:
                break;
        }
    }

    private void verifyPhone() {
        if (TextUtils.isEmpty(mPhone.getText())) {
            ToastUtils.error(this, "手机号不能为空");
            return;
        } else if (!RegexUtil.isMobileNumber(mPhone.getText().toString())) {
            ToastUtils.error(this, "手机号不正确");
            return;
        }
        mPresenter.getCode();
    }

    private void register() {
        if (TextUtils.isEmpty(mPhone.getText())) {
            ToastUtils.error(this, "手机号不能为空");
            return;
        } else if (!RegexUtil.isMobileNumber(mPhone.getText().toString())) {
            ToastUtils.error(this, "手机号不正确");
            return;
        }
        if (TextUtils.isEmpty(mPassword.getText())) {
            ToastUtils.error(this, "密码不能为空");
            return;
        } else if (mPassword.getText().toString().trim().length() < ConstantUtils.MINPASSWORD ||
                mPassword.getText().toString().trim().length() > ConstantUtils.MAXPASSWORD) {
            ToastUtils.error(this, "请输入6到16位密码");
            return;
        }
        if (TextUtils.isEmpty(mAffirmPassword.getText())) {
            ToastUtils.error(this, "确认密码不能为空");
            return;
        } else if (!mPassword.getText().toString().trim()
                .equals(mAffirmPassword.getText().toString().trim())) {
            ToastUtils.error(this, "两次密码输入不一致");
            return;
        }
        if (TextUtils.isEmpty(mCode.getText())) {
            ToastUtils.error(this, "验证码不能为空");
            return;
        }
        mPresenter.register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    }
}
