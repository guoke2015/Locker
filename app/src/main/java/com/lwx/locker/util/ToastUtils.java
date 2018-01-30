package com.lwx.locker.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.lwx.locker.custom.toast.Toasty;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   : 吐司相关工具类
 *     version: 1.0
 * </pre>
 */

public class ToastUtils {
    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static boolean isJumpWhenMore;

    /**
     * 吐司初始化
     *
     * @param isJumpWhenMore 当连续弹出吐司时，是要弹出新吐司还是只修改文本内容
     *                       <p>{@code true}: 弹出新吐司<br>{@code false}: 只修改文本内容</p>
     *                       <p>如果为{@code false}的话可用来做显示任意时长的吐司</p>
     */
    public static void init(boolean isJumpWhenMore) {
        ToastUtils.isJumpWhenMore = isJumpWhenMore;
    }


    /**--------error------------*/
    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param text    错误信息
     */
    public static void error(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void error(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void error(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void error(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void errorLong(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void errorLong(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void errorLong(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 显示错误信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void errorLong(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                error(context, format, Toast.LENGTH_LONG, args);
            }
        });
    }


    /**
     * 显示错误信息Toast
     *
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    private static void error(Context context, CharSequence text, int duration) {
        if (isJumpWhenMore) {
            cancelToast();
        }
        if (sToast == null) {
            sToast = Toasty.error(context.getApplicationContext(), text, duration, true);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

    /**
     * 显示错误信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void error(Context context, int resId, int duration) {
        error(context, context.getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示错误信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void error(Context context, int resId, int duration, Object... args) {
        error(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * 显示错误信息Toast
     *
     * @param context  上下文
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void error(Context context, String format, int duration, Object... args) {
        error(context, String.format(format, args), duration);
    }


    /**--------success------------*/
    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param text    错误信息
     */
    public static void success(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void success(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void success(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void success(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void successLong(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void successLong(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void successLong(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 显示成功信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void successLong(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                success(context, format, Toast.LENGTH_LONG, args);
            }
        });
    }


    /**
     * 显示成功信息Toast
     *
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    private static void success(Context context, CharSequence text, int duration) {
        if (isJumpWhenMore) {
            cancelToast();
        }
        if (sToast == null) {
            sToast = Toasty.success(context.getApplicationContext(), text, duration, true);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

    /**
     * 显示成功信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void success(Context context, int resId, int duration) {
        success(context, context.getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示成功信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void success(Context context, int resId, int duration, Object... args) {
        success(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * 显示成功信息Toast
     *
     * @param context  上下文
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void success(Context context, String format, int duration, Object... args) {
        success(context, String.format(format, args), duration);
    }


    /**--------info------------*/
    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param text    错误信息
     */
    public static void info(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void info(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void info(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void info(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void infoLong(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void infoLong(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void infoLong(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 显示详细信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void infoLong(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                info(context, format, Toast.LENGTH_LONG, args);
            }
        });
    }


    /**
     * 显示详细信息Toast
     *
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    private static void info(Context context, CharSequence text, int duration) {
        if (isJumpWhenMore) {
            cancelToast();
        }
        if (sToast == null) {
            sToast = Toasty.info(context.getApplicationContext(), text, duration, true);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

    /**
     * 显示详细信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void info(Context context, int resId, int duration) {
        info(context, context.getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示详细信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void info(Context context, int resId, int duration, Object... args) {
        info(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * 显示详细信息Toast
     *
     * @param context  上下文
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void info(Context context, String format, int duration, Object... args) {
        info(context, String.format(format, args), duration);
    }


    /**--------warning------------*/
    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param text    错误信息
     */
    public static void warning(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void warning(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void warning(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void warning(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void warningLong(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void warningLong(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void warningLong(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 显示等待信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void warningLong(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                warning(context, format, Toast.LENGTH_LONG, args);
            }
        });
    }


    /**
     * 显示等待信息Toast
     *
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    private static void warning(Context context, CharSequence text, int duration) {
        if (isJumpWhenMore) {
            cancelToast();
        }
        if (sToast == null) {
            sToast = Toasty.warning(context.getApplicationContext(), text, duration, true);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

    /**
     * 显示等待信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void warning(Context context, int resId, int duration) {
        warning(context, context.getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示等待信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void warning(Context context, int resId, int duration, Object... args) {
        warning(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * 显示等待信息Toast
     *
     * @param context  上下文
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void warning(Context context, String format, int duration, Object... args) {
        warning(context, String.format(format, args), duration);
    }


    /**--------normal------------*/
    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param text    错误信息
     */
    public static void normal(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void normal(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void normal(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void normal(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void normalLong(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void normalLong(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void normalLong(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * 显示正常信息Toast
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void normalLong(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                normal(context, format, Toast.LENGTH_LONG, args);
            }
        });
    }


    /**
     * 显示正常信息Toast
     *
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    private static void normal(Context context, CharSequence text, int duration) {
        if (isJumpWhenMore) {
            cancelToast();
        }
        if (sToast == null) {
            sToast = Toasty.normal(context.getApplicationContext(), text, duration);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

    /**
     * 显示正常信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void normal(Context context, int resId, int duration) {
        normal(context, context.getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示正常信息Toast
     *
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void normal(Context context, int resId, int duration, Object... args) {
        normal(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * 显示正常信息Toast
     *
     * @param context  上下文
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void normal(Context context, String format, int duration, Object... args) {
        normal(context, String.format(format, args), duration);
    }


    /**
     * 取消吐司显示
     */
    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }
}
