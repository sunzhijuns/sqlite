package com.sunzhijun.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.Toast;

import com.sunzhijun.bean.Person;
import com.sunzhijun.utils.Constant;
import com.sunzhijun.utils.DbManager;
import com.sunzhijun.utils.MySqliteHelper;

import java.util.List;

/**
 * 演示sqlite数据库中的查询操作
 */
public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        helper = DbManager.getInstance(this);
//        createDb();

    }

    private void insert(int id, String name, int age){
        SQLiteDatabase db = helper.getWritableDatabase();
        /**
         * long insert(String table, String nullColumnHack, ContentValues values)
         * String table 插入表的名称
         * String nullColumnHack
         * ContentValues values 键为String的hashmap集合
         * long 表示插入数据的列数
         */
        ContentValues values = new ContentValues();
        values.put(Constant._ID,id);
        values.put(Constant.NAME,name);
        values.put(Constant.AGE,age);
        long result = db.insert(Constant.TABLE_NAME, null, values);
        if (result>0){
            Toast.makeText(MainActivity.this,"插入数据成功",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,"插入数据失败",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 点击按钮创建数据库，并且插入测试数据
     * @param view
     */
    public void createDbAndInsertTestData(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        for (int i = 4; i < 30; i++) {
            String sql = "insert into "+Constant.TABLE_NAME+" values("+i+",'zhangsan"+i+"',23)";
            DbManager.execSQL(db,sql);
        }
        db.close();
    }
    public void onQueryClick(View view){
        switch (view.getId()){
            case R.id.btn_query:
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "select * from "+ Constant.TABLE_NAME;
                Cursor cursor = DbManager.selectDataBySql(db,sql,null);
                List<Person> list = DbManager.cursorToList(cursor);
                for (Person person :
                        list) {
                    Log.i("数据：", person.toString() );
                }
                db.close();
                break;
            case R.id.btn_api_query:
                db = helper.getWritableDatabase();
                /**
                 Cursor query(String table, String[] columns, String selection,
                 String[] selectionArgs, String groupBy, String having,
                 String orderBy)
                 String table 待查询的表名
                 String[] columns 待查询的字段名 null 查询所有
                 String selection 查询条件 where子句
                 String[] selectionArgs 查询条件占位符取值 null
                 String groupBy  分组条件 group by 子句 null
                 String having 筛选条件 having 子句 null
                 String orderBy 排序条件 order by 子句 null
                 */
                cursor = db.query(Constant.TABLE_NAME, null, Constant._ID+">?",new String[]{"10"},
                       null, null, Constant._ID+" desc");
                list=DbManager.cursorToList(cursor);
                for (Person p :
                        list) {
                    Log.i("数据：", p.toString());
                }
                break;
        }
        CursorAdapter
    }
    public void createDb(View view) {
        /**
         * helper.getReadableDatabase(); helper.getWritableDatabase();创建或打开数据库
         * 如果数据库不存在，则创建数据库，否则直接打开数据库
         * 默认情况下，都表示打开或创建可读可写的数据库，
         * 但，如果磁盘已满或数据库本身权限
         * 等特殊情况下，getReadableDatabase 打开的是只读数据库。
         */
        SQLiteDatabase db = helper.getWritableDatabase();
    }
    public void click(View view){
        SQLiteDatabase db = null;
        String sql;
        switch (view.getId()){
            case R.id.btn_insert:
                db = helper.getWritableDatabase();
                sql = "insert into "+ Constant.TABLE_NAME+" values(1,'zhangsan',20)";
                DbManager.execSQL(db,sql);
                sql = "insert into "+Constant.TABLE_NAME+" values(2,'lisi',25)";
                DbManager.execSQL(db,sql);
                db.close();
                break;
            case R.id.btn_update:
                db = helper.getWritableDatabase();
                sql = "update "+Constant.TABLE_NAME+" set "+Constant.NAME+"='xiaoming' where "+Constant._ID+"=1";
                DbManager.execSQL(db,sql);
                db.close();
                break;
            case R.id.btn_delete:
                db=helper.getWritableDatabase();
                sql = "delete from "+Constant.TABLE_NAME+" where "+Constant._ID+"=2";
                DbManager.execSQL(db,sql);
                db.close();
                break;
        }
    }

    public void onClick(View view){
        SQLiteDatabase db=null;
        ContentValues values=null;
        switch (view.getId()){
            case R.id.btn_api_insert:
                db = helper.getWritableDatabase();
                /**
                 * long insert(String table, String nullColumnHack, ContentValues values)
                 * String table 插入表的名称
                 * String nullColumnHack
                 * ContentValues values 键为String的hashmap集合
                 * long 表示插入数据的列数
                 */
                values = new ContentValues();
                values.put(Constant._ID,3);
                values.put(Constant.NAME,"张三");
                values.put(Constant.AGE,23);
                long result = db.insert(Constant.TABLE_NAME, null, values);
                if (result>0){
                    Toast.makeText(MainActivity.this,"插入数据成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"插入数据失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_api_update:
                db=helper.getWritableDatabase();
                /**
                 * int update(String table, ContentValues values, String whereClause, String[] whereArgs)
                 * String table 修改的数据表的名称
                 * ContentValues values 键为string类型的hashmap
                 * String whereClause 修改条件
                 * String[] whereArgs 修改条件的占位符
                 * int 修改的条数
                 */
                values = new ContentValues();
                values.put(Constant.NAME,"修改后"); // put(需要修改的字段，修改后的值)
//                int result = db.update(Constant.TABLE_NAME, values,Constant._ID+"=3",null);
                int count = db.update(Constant.TABLE_NAME, values,Constant._ID+"=?",new String[]{"3"});
                if (count>0){
                    Toast.makeText(MainActivity.this,"修改数据成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"修改数据失败",Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.btn_api_delete:
                db=helper.getWritableDatabase();
                /**
                 * int delete(String table, String whereClause, String[] whereArgs)
                 * String table 删除的数据表的名称
                 * String whereClause 删除的条件
                 * String[] whereArgs 删除条件的占位符
                 */
                int count2=db.delete(Constant.TABLE_NAME, Constant._ID+"=?",new String[]{"1"});
                if (count2>0){
                    Toast.makeText(MainActivity.this,"删除数据成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"删除数据失败",Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;

        }
    }

}
