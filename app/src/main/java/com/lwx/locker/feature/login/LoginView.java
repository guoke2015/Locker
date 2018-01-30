package com.lwx.locker.feature.login;

import com.lwx.locker.base.IBaseView;
import com.lwx.locker.data.local.UserInfo;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface LoginView extends IBaseView {
    String getPhone();

    String getPassword();

    void clearPhone();

    void clearPassword();

    void toMainActivity(UserInfo userInfo);

    void showFailedError(String err);

    void showUserInfo(UserInfo userInfo);
}
