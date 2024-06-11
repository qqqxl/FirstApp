package com.example.firstapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.firstapp.R;

public class DBOpen extends SQLiteOpenHelper {
    public DBOpen(@Nullable Context context) {
        super(context, "tally.db",null,1);
    }
    //创建数据库方法，只有项目第一次运行时会被调用；

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表示类型的表
        String sql="create table typetb(id integer primary key autoincrement,typename varchar(10),notId integer,yesId integer,kind integer)";
        db.execSQL(sql);
        insertType(db);
        //创建记账表
        sql="create table acounttb(id integer primary key autoincrement,typename varchar(10),yesId integer,beizhu varchar(80),money float,"
                +"time varchar(60),year integer,month integer,day integer,kind integer)";
        db.execSQL(sql);
    }

    private void insertType(SQLiteDatabase db) {
        //向tb中插入
        String sql="insert into typetb(typename,notId,yesId,kind) values(?,?,?,?)";
        db.execSQL(sql,new Object[]{"其他", R.drawable.baseline_list_hui,R.drawable.baseline_view_list_24,0});
        db.execSQL(sql,new Object[]{"餐饮",R.drawable.dinner_hui, R.drawable.dinner,0});//支出为0
        db.execSQL(sql,new Object[]{"购物", R.drawable.shopping_hui,R.drawable.shopping,0});
        db.execSQL(sql,new Object[]{"水果",R.drawable.fruit_hui, R.drawable.frurit,0});
        db.execSQL(sql,new Object[]{"零食", R.drawable.sugar_hui,R.drawable.sugar,0});
        db.execSQL(sql,new Object[]{"出行",R.drawable.air_hui, R.drawable.air,0});
        db.execSQL(sql,new Object[]{"衣服",R.drawable.cloth_hui, R.drawable.cloth,0});
        db.execSQL(sql,new Object[]{"学费",R.drawable.study_hui, R.drawable.study,0});
        db.execSQL(sql,new Object[]{"烟酒",R.drawable.baseline_local_bar_hui, R.drawable.baseline_local_bar_24,0});
        db.execSQL(sql,new Object[]{"住房",R.drawable.house_hui, R.drawable.house,0});
        db.execSQL(sql,new Object[]{"通讯",R.drawable.massage_hui, R.drawable.massage,0});
        db.execSQL(sql,new Object[]{"宠物", R.drawable.chongwu_hui,R.drawable.other,0});

        db.execSQL(sql,new Object[]{"其他", R.drawable.baseline_list_hui,R.drawable.baseline_view_list_24,0});
        db.execSQL(sql,new Object[]{"工资",R.drawable.saraly_hui, R.drawable.saraly,1});//收入为1
        db.execSQL(sql,new Object[]{"兼职",R.drawable.jianzhi_hui, R.drawable.jianzhi,1});
        db.execSQL(sql,new Object[]{"奖学金",R.drawable.jiangxuejin_hui, R.drawable.jiangxuejin,1});
        db.execSQL(sql,new Object[]{"生活费",R.drawable.shenghuifei_hui, R.drawable.shenghuofei,1});
        db.execSQL(sql,new Object[]{"补贴",R.drawable.butie_hui, R.drawable.butie,1});
        db.execSQL(sql,new Object[]{"意外",R.drawable.baseline_outlet_hui, R.drawable.expert,1});


    }

    //数据库版本在更新时发生改变会调用此方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
