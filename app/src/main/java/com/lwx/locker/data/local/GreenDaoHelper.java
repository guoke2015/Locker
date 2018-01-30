package com.lwx.locker.data.local;

import com.lwx.locker.LockerApplication;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;
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

public class GreenDaoHelper<T> {
    private final static String DB_NAME = "locker-db";
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    //单例
    private static volatile GreenDaoHelper mInstance;

    private GreenDaoHelper() {
        MyOpenHelper myOpenHelper = new
                MyOpenHelper(LockerApplication.getInstance().getApplicationContext(), DB_NAME, null);
        mDaoMaster = new DaoMaster(myOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoHelper getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoHelper.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 向数据库中插入或更新数据(数组)
     *
     * @param list 要插入的数据集合(对象)
     * @return 插入或更新成功数据个数
     */
    public Long insertToArray(List<T> list) {
        if (list == null || list.isEmpty() || (getDao(list.get(0).getClass()) == null)) {
            return new Long((long) -1);
        } else {
            getDao(list.get(0).getClass()).insertOrReplaceInTx(list);
            return new Long(1);
        }
    }

    /**
     * 向数据库中插入或更新数据(单条数据)
     *
     * @param t 对象
     * @return
     */
    public Long insertOrReplace(T t) {
        if (getDao(t.getClass()) == null) {
            return new Long((long) -1);
        } else {
            return getDao(t.getClass()).insertOrReplace(t);
        }
    }

    /**
     * 根据where条件查询数据
     *
     * @param t     对象
     * @param where where条件，如：UserInfoDao.Properties.Phone.eq("phone")
     * @return list列表
     */
    public List<T> queryFromWhere(T t, WhereCondition where) {
        if (getDao(t.getClass()) == null) {
            return null;
        } else {
            Query<T> query = getDao(t.getClass()).queryBuilder()
                    .where(where)
                    .build();
            return query.list();
        }
    }

    /**
     * 查询所有数据
     *
     * @param t
     * @return
     */
    public List<T> queryAllData(T t) {
        if (getDao(t.getClass()) == null) {
            return null;
        } else {
            Query<T> query = getDao(t.getClass()).queryBuilder().build();
            return query.list();
        }
    }

    /**
     * 获取Dao
     *
     * @param className 数据库表名：如UserInfo.class
     * @return dao
     */
    private AbstractDao getDao(Object className) {
        if (UserInfo.class.equals(className)) {
            return mDaoSession.getDao(UserInfo.class);
        } /*else if (GirlsUrls.class.equals(className)) {
            return mDaoSession.getDao(GirlsUrls.class);
        }*/
        return null;
    }
}
