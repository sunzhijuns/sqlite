package com.sunzhijun.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sunzhijun.bean.Person;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 根据sql语句查询获得cursor对象
     * @param db 数据库对象
     * @param sql 查询的sql 数据
     * @param selectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db, String sql, String[] selectionArgs){
        Cursor cursor = null;
        if (db!=null){
            cursor = db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

    /**
     * 将查询的cursor对象转换为list集合
     * @param cursor 游标对象
     * @return 集合对象
     */
    public static List<Person> cursorToList(Cursor cursor){
        List<Person> list = new ArrayList<>();
        if (cursor != null){
            //moveToNext() 如果返回true 表示下一条记录存在，否则表示游标中的数据读取完毕
            while (cursor.moveToNext()){
                int columnIndex = cursor.getColumnIndex(Constant._ID);
                Log.i("下标","columnIndex = "+String.valueOf(columnIndex));
                //getInt(int columnIndex) 根据参数中指定的字段下标 获取对应int类型的value
                int _id = cursor.getInt(columnIndex);
                String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
                int age=cursor.getInt(cursor.getColumnIndex(Constant.AGE));
                Person person = new Person(_id,name,age);
                list.add(person);
            }
        }
        return list;
    }

}
