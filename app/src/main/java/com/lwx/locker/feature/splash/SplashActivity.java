package com.lwx.locker.feature.splash;

import android.content.Intent;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.lwx.locker.R;
import com.lwx.locker.base.BaseActivity;
import com.lwx.locker.data.local.UserInfo;
import com.lwx.locker.feature.MainActivity;
import com.lwx.locker.feature.login.LoginActivity;
import com.trello.rxlifecycle2.LifecycleTransformer;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter<SplashView>> implements SplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTranslucent(this, 50);


    }

    @Override
    protected SplashPresenter<SplashView> setPresenter() {
        return new SplashPresenter<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.autoLogin();
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
    public void toLoginActivity(UserInfo userInfo) {
        Intent intent = new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("userInfo", userInfo);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        finish();
    }

    @Override
    public void toLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        finish();
    }

    @Override
    public void toMainActivity(UserInfo userInfo) {
        mPresenter.update(userInfo);
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("userInfo", userInfo);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        finish();
    }
}
