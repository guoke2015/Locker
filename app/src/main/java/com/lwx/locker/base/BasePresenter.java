package com.lwx.locker.base;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   : Presenter基类(表示层)
 *     version: 1.0
 * </pre>
 */

public class BasePresenter<V> {
    //View层引用
    protected WeakReference<V> mViewRef;

    /**
     * 绑定
     * @param view
     */
    public void attachView(V view){
        mViewRef=new WeakReference<V>(view);
    }

    /**
     * 解绑
     */
    public void detachView(){
        mViewRef.clear();
    }
}
