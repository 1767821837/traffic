package cn.logcode.traffic.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.logcode.traffic.App;

/**
 * Created by 24327 on 2018/4/27.
 */

public class LiteUtil extends OrmLiteSqliteOpenHelper {
    private static ConnectionSource conn;
    public static LiteUtil instance;

    public LiteUtil() {
        super(App.INSTANCE, "traffic", null, 1);
        conn =  connectionSource;
    }

    public static void init() {
        instance = new LiteUtil();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    public static <T> Dao<T, Integer> getDbDao(Class<T> cls) throws SQLException {
        if(null == conn){
            throw new SQLException("conn is null");
        }
        TableUtils.createTable(conn, cls);
        return instance.getDao(cls);
    }
}
