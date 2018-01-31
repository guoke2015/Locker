package com.lwx.locker.data;

import com.lwx.locker.LockerApplication;
import com.lwx.locker.data.local.GreenDaoHelper;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DataManager<T> {
    private static volatile DataManager mDataManager;
    private GreenDaoHelper mGreenDaoHelper;

    /**
     * 获取DataManager单例
     *
     * @return
     */
    public static DataManager getInstance() {
        if (mDataManager == null) {
            synchronized (DataManager.class) {
                if (mDataManager == null) {
                    mDataManager = new DataManager();
                }
            }
        }
        return mDataManager;
    }

    private DataManager(){
        mGreenDaoHelper= LockerApplication.getInstance().getGreenDaoHelper();
    }

    /**
     * 向数据库中插入或更新数据(数组)
     *
     * @param list 要插入的数据集合(对象)
     * @return 插入或更新成功数据个数
     */
    public Long insertToArray(final List<T> list) {
        return mGreenDaoHelper.insertToArray(list);
    }

    /**
     * 向数据库中插入或更新数据(单条数据)
     *
     * @param t 对象
     * @return
     */
    public long insertOrReplace(T t) {
        return mGreenDaoHelper.insertOrReplace(t);
    }

    /**
     * 根据where条件查询数据
     *
     * @param t     对象
     * @param where where条件，如：UserInfoDao.Properties.Phone.eq("phone")
     * @return list列表
     */
    public List<T> queryFromWhere(T t, WhereCondition where) {
        return mGreenDaoHelper.queryFromWhere(t, where);
    }

    /**
     * 查询所有数据
     * @param t
     * @param property 按指定字段降序，如UserInfoDao.Properties.Usertime
     * @return
     */
    public List<T> queryAllData(T t, Property property) {
        return mGreenDaoHelper.queryAllData(t,property);
    }
}
