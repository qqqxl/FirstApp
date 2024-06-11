package com.example.firstapp.db;
//表示收入支出具体类型的类
public class TypeBean {
    int id;
    String typename;//类型名称
    int notId;//未被选中的；
    int yesId;//被选中的
    int kind;//收入—1 支出-0

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

    public int getNotId() {
        return notId;
    }

    public void setNotId(int notId) {
        this.notId = notId;
    }

    public int getYesId() {
        return yesId;
    }

    public void setYesId(int yesId) {
        this.yesId = yesId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public TypeBean() {
    }

    public TypeBean(int id, String typename, int notId, int yesId, int kind) {
        this.id = id;
        this.typename = typename;
        this.notId = notId;
        this.yesId = yesId;
        this.kind = kind;
    }
}
