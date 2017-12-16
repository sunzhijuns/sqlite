package com.sunzhijun.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sunzhijun.bean.Person;
import com.sunzhijun.utils.Constant;
import com.sunzhijun.utils.DbManager;
import com.sunzhijun.utils.MySqliteHelper;

import java.util.List;

/**
 * Created by sunzhijun on 2017/12/11.
 */

public class TransationActivity extends AppCompatActivity {
    private MySqliteHelper helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transation);
        helper=DbManager.getInstance(this);
    }
    public void insertData(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        //1.数据库显示的开启事务
        db.beginTransaction();
        for (int i = 10; i <= 20; i++) {
            String sql ="insert into "+Constant.TABLE_NAME+" values("+i+",'zhangsan"+i+"',23)";
            DbManager.execSQL(db,sql);
        }
        //2.提交事务
        db.setTransactionSuccessful();
        //3.关闭事务
        db.endTransaction();


        String sql = "select * from "+ Constant.TABLE_NAME;
        Cursor cursor = DbManager.selectDataBySql(db,sql,null);
        List<Person> list = DbManager.cursorToList(cursor);
        for (Person person :
                list) {
            Log.i("数据：", person.toString() );
        }

        db.close();
    }
}
