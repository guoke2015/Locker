package com.lwx.locker.feature.splash;

import com.lwx.locker.base.IBaseView;
import com.lwx.locker.data.local.UserInfo;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface SplashView extends IBaseView {
    void toLoginActivity(UserInfo userInfo);
    void toLoginActivity();
    void toMainActivity(UserInfo userInfo);
}
