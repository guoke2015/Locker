package com.lwx.locker.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface IBaseView {
    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /**
     * 管理view生命周期与rxjava的订阅关系
     * @return
     */
    LifecycleTransformer bindLifecycle();
}
