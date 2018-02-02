package com.lwx.locker.feature.register;

import com.lwx.locker.base.BasePresenter;
import com.lwx.locker.data.DataManager;
import com.lwx.locker.data.local.UserInfo;
import com.lwx.locker.util.ConstantUtils;
import com.lwx.locker.util.DateUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/02/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RegisterPresenter<V extends RegisterView> extends BasePresenter<V> {
    private DataManager mDataManager;
    private int time = 60;

    RegisterPresenter() {
        super();
        mDataManager = DataManager.getInstance();
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(final int event, int result, final Object data) {
                //回调完成
                if (result == SMSSDK.RESULT_COMPLETE) {
                    Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> e) {
                            e.onNext(event);
                        }
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(mViewRef.get().bindLifecycle())
                            .subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer integer) throws Exception {
                                    if (mViewRef.get() != null) {
                                        //获取验证码成功
                                        if (integer == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                            mViewRef.get().codeSuccessful();
                                        }//提交验证码成功
                                        else if (integer == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                            Observable.create(new ObservableOnSubscribe<UserInfo>() {
                                                @Override
                                                public void subscribe(ObservableEmitter<UserInfo> e) throws Exception {
                                                    //将数据写入数据库中
                                                    if (mViewRef.get() != null) {
                                                        //将数据写入数据库中
                                                        UserInfo userInfo = new UserInfo();
                                                        userInfo.setPhone(mViewRef.get().getPhone());
                                                        userInfo.setPassword(mViewRef.get().getPassword());
                                                        userInfo.setUsertime(DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT));
                                                        mDataManager.insertOrReplace(userInfo);
                                                        e.onNext(userInfo);
                                                    }
                                                }
                                            })
                                                    .subscribeOn(Schedulers.io())
                                                    .doOnTerminate(new Action() {
                                                        @Override
                                                        public void run() throws Exception {
                                                            mViewRef.get().hideLoading();
                                                        }
                                                    })
                                                    .subscribeOn(AndroidSchedulers.mainThread())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .compose(mViewRef.get().bindLifecycle())
                                                    .subscribe(new Consumer<UserInfo>() {
                                                        @Override
                                                        public void accept(UserInfo userInfo) throws Exception {
                                                            mViewRef.get().toMainActivity(userInfo);
                                                        }
                                                    });

                                        }
                                    }
                                }
                            });
                } else {
                    Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                            if (mViewRef.get() != null) {
                                String jsonData = ((Throwable) data).getMessage();
                                try {
                                    JSONObject jsonObject = new JSONObject(jsonData);
                                    String description = jsonObject.getString("description");
                                    ///短信验证返回数据
                                    /*String detail = jsonObject.getString("detail");
                                    int httpStatus = jsonObject.getInt("httpStatus");
                                    int status = jsonObject.getInt("status");
                                    Log.v("错误码detail:", detail);
                                    Log.v("错误码description:", description);
                                    Log.v("错误码httpStatus:", httpStatus + "");
                                    Log.v("错误码status:", status + "");*/
                                    e.onNext(description);
                                } catch (JSONException ex) {
                                }
                                e.onComplete();
                            }
                        }
                    })
                            .subscribeOn(Schedulers.io())
                            .doOnTerminate(new Action() {
                                @Override
                                public void run() throws Exception {
                                    mViewRef.get().hideLoading();
                                }
                            })
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(mViewRef.get().bindLifecycle())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String err) throws Exception {
                                    mViewRef.get().showFailedError(err);
                                }
                            });
                }
            }
        };
        //注册短信回调
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 获取验证码
     */
    public void getCode() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                if (mViewRef.get() != null) {
                    //触发操作
                    SMSSDK.getVerificationCode(ConstantUtils.COUNTRY, mViewRef.get().getPhone());
                }
                while (time > 0) {
                    if (!e.isDisposed()) {
                        e.onNext(time);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        time--;
                    } else {
                        time = 0;
                    }
                }
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mViewRef.get().bindLifecycle())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (mViewRef.get() != null) {
                            if (integer > 0) {
                                mViewRef.get().showCountDownTime(integer.toString());
                            } else {
                                mViewRef.get().showCountDownTime("重新获取验证码");
                            }
                        }
                    }
                });
    }

    /**
     * 提交验证码
     */
    public void register() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                if (mViewRef.get() != null) {
                    // 触发操作
                    SMSSDK.submitVerificationCode(ConstantUtils.COUNTRY, mViewRef.get().getPhone(), mViewRef.get().getCode());
                }
                e.onComplete();
            }
        })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mViewRef.get().showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(mViewRef.get().bindLifecycle())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
    }
}
