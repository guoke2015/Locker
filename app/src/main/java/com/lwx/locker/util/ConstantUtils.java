package com.lwx.locker.util;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ConstantUtils {
    /**
     * 自动登录时间（d）
     */
    public static final int AUTO_LOGIN_DAY = 10;

    /**
     * 国家代码，中国为86
     */
    public static final String COUNTRY = "86";

    /**
     * 最小密码长度
     */
    public static final int MINPASSWORD = 6;

    /**
     * 最大密码长度
     */
    public static final int MAXPASSWORD = 16;

    /******************** 存储相关常量 ********************/
    /**
     * Byte与Byte的倍数
     */
    public static final int BYTE = 1;
    /**
     * KB与Byte的倍数
     */
    public static final int KB = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final int MB = 1048576;
    /**
     * GB与Byte的倍数
     */
    public static final int GB = 1073741824;

    public enum MemoryUnit {
        BYTE,
        KB,
        MB,
        GB
    }
}
