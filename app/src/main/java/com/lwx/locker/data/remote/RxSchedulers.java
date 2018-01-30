package com.lwx.locker.data.remote;

import com.lwx.locker.base.IBaseView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RxSchedulers {
    /**
     * 基本请求
     *
     * @param baseView
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> flow_io_main(final IBaseView baseView) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                baseView.showLoading();
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                baseView.hideLoading();
                            }
                        })
                        .compose(baseView.bindLifecycle())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 基本请求
     *
     * @param baseView
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> observable_io_main(final IBaseView baseView) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                baseView.showLoading();
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                baseView.hideLoading();
                            }
                        })
                        .compose(baseView.bindLifecycle())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * Observable基本使用
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Observable<T> observable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                e.onNext(t);
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
