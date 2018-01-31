package com.lwx.locker.feature.splash;

import com.lwx.locker.base.BasePresenter;
import com.lwx.locker.data.DataManager;
import com.lwx.locker.data.local.UserInfo;
import com.lwx.locker.data.local.UserInfoDao;
import com.lwx.locker.data.remote.RxSchedulers;
import com.lwx.locker.util.ConstantUtils;
import com.lwx.locker.util.DateUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SplashPresenter<V extends SplashView> extends BasePresenter<V> {
    private DataManager mDataManager;

    SplashPresenter() {
        super();
        mDataManager = DataManager.getInstance();
    }

    /**
     * 自动登录
     */
    public void autoLogin() {
        Observable.create(new ObservableOnSubscribe<List<UserInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserInfo>> e) throws Exception {
                e.onNext(mDataManager.queryAllData(new UserInfo(), UserInfoDao.Properties.Usertime));
            }
        })
                .compose(RxSchedulers.<List<UserInfo>>observable_io_main(mViewRef.get()))
                .subscribe(new Consumer<List<UserInfo>>() {
                    @Override
                    public void accept(List<UserInfo> userInfos) throws Exception {
                        if (mViewRef.get() != null) {
                            if (userInfos != null && userInfos.size() > 0) {
                                //10天之内自动登录，否则需重新登录
                                if (DateUtil.dayDiffCurr(userInfos.get(0).getUsertime()) <= ConstantUtils.AUTO_LOGIN_DAY) {
                                    mViewRef.get().toMainActivity(userInfos.get(0));
                                } else {
                                    mViewRef.get().toLoginActivity(userInfos.get(0));
                                }
                            } else {
                                mViewRef.get().toLoginActivity();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mViewRef.get() != null) {
                            mViewRef.get().toLoginActivity();
                        }
                    }
                });
    }

    /**
     * 更新UserInfo数据
     *
     * @param userInfo
     */
    public void update(final UserInfo userInfo) {
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> e) throws Exception {
                userInfo.setUsertime(DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT));
                e.onNext(mDataManager.insertOrReplace(userInfo));
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .compose(mViewRef.get().bindLifecycle());
    }
}
