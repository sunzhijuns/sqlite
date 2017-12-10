package com.sunzhijun.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sunzhijun.utils.Constant;

import java.io.File;

/**
 * Created by sunzhijun on 2017/12/11.
 */

public class CursorAdapterActivity extends AppCompatActivity {
    private ListView lv;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        lv = (ListView) findViewById(R.id.lv);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator
                + "info.db";
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from "+ Constant.TABLE_NAME,null);
        MyCursorAdapter adapter = new MyCursorAdapter(this,cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adapter);
    }
    public class MyCursorAdapter extends CursorAdapter{
        public MyCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = LayoutInflater.from(CursorAdapterActivity.this).inflate(R.layout.simplecursor_item,parent,false);

            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv_id = view.findViewById(R.id.tv_id);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_age = view.findViewById(R.id.tv_age);

            tv_id.setText(cursor.getInt(cursor.getColumnIndex(Constant._ID))+"");
            tv_name.setText(cursor.getString(cursor.getColumnIndex(Constant.NAME)));
            tv_age.setText(cursor.getInt(cursor.getColumnIndex(Constant.AGE))+"");
        }
    }
}
