package com.example.firstapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//负责管理数据库的类，主要对表中的内容进行增添改查
public class DBManager  {
    private static SQLiteDatabase db;//初始化


    public static void initDB(Context context) {
        DBOpen helper = new DBOpen(context);//得到帮助类对象
        db = helper.getWritableDatabase();//得到数据库对象
    }

    public static List<TypeBean> getTypeList(int kind) {
        List<TypeBean> list = new ArrayList<>();
        //读取表数据
        String sql = "select * from typetb where kind = " + kind;
        Cursor cursor = db.rawQuery(sql, null);
        //循环读取游标内容，存储到对象中
        while (cursor.moveToNext()) {
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            int notId = cursor.getInt(cursor.getColumnIndexOrThrow("notId"));
            int yesId = cursor.getInt(cursor.getColumnIndexOrThrow("yesId"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            int kind1 = cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
            TypeBean typeBean = new TypeBean(id, typename, notId, yesId, kind);
            list.add(typeBean);

        }
        return list;
    }

    //向记账表中插入数据
    public static void insertToAcounttb(AcountBean bean) {
        ContentValues values = new ContentValues();
        values.put("typename", bean.getTypename());
        values.put("yesId", bean.getYesId());
        values.put("beizhu", bean.getBeizhu());
        values.put("money", bean.getMoney());
        values.put("year", bean.getYear());
        values.put("month", bean.getMonth());
        values.put("day", bean.getDay());
        values.put("kind", bean.getKind());

        db.insert("acounttb", null, values);

    }
    //查找记账表的某天支出收入
    public static List<AcountBean>getOneDayAccount(int year, int month, int day) {
          List<AcountBean>list=new ArrayList<>();
          String sql="select * from acounttb where year=? and month=? and day=? order by id desc";
          Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + ""});
        //遍历数据
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            String beizhu = cursor.getString(cursor.getColumnIndexOrThrow("beizhu"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("money"));
            int yesId=cursor.getInt(cursor.getColumnIndexOrThrow("yesId"));
            int kind=cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
            AcountBean bean = new AcountBean(id, typename, yesId, beizhu, money,time, year, month, day, kind);
            list.add(bean);
        }
        return list;
    }
    public static List<AcountBean>getOneMonthAccounnt(int year, int month) {
        List<AcountBean>list=new ArrayList<>();
        String sql="select * from acounttb where year=? and month=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", });
        //遍历数据
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String typename = cursor.getString(cursor.getColumnIndexOrThrow("typename"));
            String beizhu = cursor.getString(cursor.getColumnIndexOrThrow("beizhu"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("money"));
            int yesId=cursor.getInt(cursor.getColumnIndexOrThrow("yesId"));
            int kind=cursor.getInt(cursor.getColumnIndexOrThrow("kind"));
            int day = cursor.getInt(cursor.getColumnIndexOrThrow("day"));
            AcountBean bean = new AcountBean(id, typename, yesId, beizhu, money,time, year, month, day, kind);
            list.add(bean);
        }
        return list;
    }
    public static float getDayData(int year,int month,int day, int kind){
        float total=0.0f;
        String sql;
        sql = "select sum(money) from acounttb where year=? and month=? and day=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "",month+"",day+"", kind + ""});
        if (cursor.moveToFirst()){
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("sum(money)"));
            total=money;
        }
        return total;
    }

    //获取月总支出收入
    public static float getSumMonthData(int year,int month,int kind){
        float total=0.0f;
        String sql;
        sql = "select sum(money) from acounttb where year=? and month=? and kind=?";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "",month+"", kind + ""});
        if (cursor.moveToFirst()){
            float money = cursor.getFloat(cursor.getColumnIndexOrThrow("sum(money)"));
            total=money;
        }
        return total;
    }

}