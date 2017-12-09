package com.sunzhijun.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 对数据库操作的工具
 * Created by sunzhijun on 2017/12/9.
 */

public class DbManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context){
        if (helper == null){
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据SQL语句在数据库中执行
     * @param db
     * @param sql
     */
    public static void execSQL(SQLiteDatabase db, String sql){
        if (db!=null){
            if (sql != null && !"".equals(sql)){
                db.execSQL(sql);
            }
        }
    }

}
