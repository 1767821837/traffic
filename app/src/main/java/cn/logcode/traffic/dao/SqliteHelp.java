package cn.logcode.traffic.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.logcode.traffic.bean.CarBean;
import cn.logcode.traffic.bean.DBSense;

public class SqliteHelp extends OrmLiteSqliteOpenHelper {
    public SqliteHelp(Context context) {
        super(context, "song.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, CarBean.class);
            TableUtils.createTable(connectionSource, DBSense.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
