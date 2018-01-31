package com.lwx.locker.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.lwx.locker.R;
import com.lwx.locker.util.AppManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends RxAppCompatActivity {

    //表示层的引用
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mPresenter = setPresenter();
        mPresenter.attachView((V) this);
    }

    /**
     * 创建表示层
     *
     * @return presenter
     */
    protected abstract P setPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        }
        return false;
    }
}
