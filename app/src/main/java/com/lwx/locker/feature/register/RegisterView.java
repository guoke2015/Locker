package com.lwx.locker.feature.register;

import com.lwx.locker.base.IBaseView;
import com.lwx.locker.data.local.UserInfo;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/02/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface RegisterView extends IBaseView {
    String getPhone();

    String getPassword();

    String getCode();

    void toMainActivity(UserInfo userInfo);

    void showFailedError(String error);

    void showCountDownTime(String countDownTime);

    void codeSuccessful();
}
