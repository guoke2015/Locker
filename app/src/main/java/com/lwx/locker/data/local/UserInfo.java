package com.lwx.locker.data.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 最近使用时间
     */
    private String usertime;
    @Generated(hash = 181183650)
    public UserInfo(Long id, String phone, String password, String usertime) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.usertime = usertime;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsertime() {
        return this.usertime;
    }
    public void setUsertime(String usertime) {
        this.usertime = usertime;
    }
}
