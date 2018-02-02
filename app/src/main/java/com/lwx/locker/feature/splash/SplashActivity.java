package com.lwx.locker.feature.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.lwx.locker.R;
import com.lwx.locker.base.BaseActivity;
import com.lwx.locker.data.local.UserInfo;
import com.lwx.locker.feature.MainActivity;
import com.lwx.locker.feature.login.LoginActivity;
import com.lwx.locker.util.ToastUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter<SplashView>> implements SplashView {
    private static final String TAG = "SplashActivity";
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTranslucent(this, 50);

        ToastUtils.init(true);
        rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission.name)) {
                                ToastUtils.success(SplashActivity.this, "您拒绝了应用程序写入外部存储的权限，会导致部分功能无法正常使用");
                            }
                        } else {
                            if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission.name)) {
                                ToastUtils.success(SplashActivity.this, "您永久拒绝了应用程序写入外部存储的权限，会导致部分功能无法正常使用，请在设置中打开该权限");
                            }
                        }
                    }
                });
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
