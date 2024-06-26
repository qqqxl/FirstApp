package com.example.firstapp.db;
//记录一条数据的相关内容类
public class AcountBean {
    int id;
    String typename;
    int yesId;
    String beizhu;
    float money;
    String time;
    int year;
    int month;
    int day;
    int kind;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getYesId() {
        return yesId;
    }

    public void setYesId(int yesId) {
        this.yesId = yesId;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public AcountBean() {
    }

    public AcountBean(int id, String typename, int yesId, String beizhu, float money, String time, int year, int month, int day, int kind) {
        this.id = id;
        this.typename = typename;
        this.yesId = yesId;
        this.beizhu = beizhu;
        this.money = money;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.kind = kind;
    }
}
