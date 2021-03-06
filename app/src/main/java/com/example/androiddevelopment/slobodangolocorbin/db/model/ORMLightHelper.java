package com.example.androiddevelopment.slobodangolocorbin.db.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class ORMLightHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "test.db";
    private static final int    DATABASE_VERSION = 1;

    private Dao<Stavka, Integer> mProductDao = null;
    private Dao<Prijava, Integer> mPrijavaDao = null;

    public ORMLightHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Stavka.class);
            TableUtils.createTable(connectionSource, Prijava.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Stavka.class, true);
            TableUtils.dropTable(connectionSource, Prijava.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Stavka, Integer> getStavkaDao() throws SQLException {
        if (mProductDao == null) {
            mProductDao = getDao(Stavka.class);
        }

        return mProductDao;
    }

    public Dao<Prijava, Integer> getPrijavaDao() throws SQLException {
        if (mPrijavaDao == null) {
            mPrijavaDao = getDao(Prijava.class);
        }

        return mPrijavaDao;
    }
}
