package com.lwx.locker;

import android.app.Application;

import com.lwx.locker.custom.crash.CrashHandler;
import com.lwx.locker.data.local.GreenDaoHelper;
import com.mob.MobSDK;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   : Application
 *     version: 1.0
 * </pre>
 */

public class LockerApplication extends Application {
    private static LockerApplication lockerApplication;
    private GreenDaoHelper mGreenDaoHelper;

    /**
     * 获取Application实例
     *
     * @return
     */
    public static LockerApplication getInstance() {
        return lockerApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lockerApplication = this;
        //初始化MobSDK
        MobSDK.init(this);
        mGreenDaoHelper = GreenDaoHelper.getInstance();
        CrashHandler.getInstance().init(this);
    }

    /**
     * 获取GreenDao帮助类
     *
     * @return
     */
    public GreenDaoHelper getGreenDaoHelper() {
        return mGreenDaoHelper;
    }
}
