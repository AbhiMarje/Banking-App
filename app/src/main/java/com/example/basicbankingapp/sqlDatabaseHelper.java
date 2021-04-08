package com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlDatabaseHelper extends SQLiteOpenHelper {

    private static final String dbName = "dbmanager";

    public sqlDatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table tbl_user (PHONENUMBER INTEGER PRIMARY KEY,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)";
        String transQry = "create table tbl_trans (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)";
        db.execSQL(qry);
        db.execSQL(transQry);
        db.execSQL("insert into tbl_user values(9000000001,'Abhi',10000.00,'abhi@gmail.com','XXXXXXXXXXXX1234','ABC00000001')");
        db.execSQL("insert into tbl_user values(9000000002,'Venkatesh',9050.00,'Venkatesh@gmail.com','XXXXXXXXXXXX5678','ABC00000002')");
        db.execSQL("insert into tbl_user values(9000000003,'Pramod',8000.00,'Pramod@gmail.com','XXXXXXXXXXXX9101','ABC00000003')");
        db.execSQL("insert into tbl_user values(9000000004,'Mayur',7050.00,'Mayur@gmail.com','XXXXXXXXXXXX1213','ABC00000004')");
        db.execSQL("insert into tbl_user values(9000000005,'Aditya',6000.00,'Aditya@gmail.com','XXXXXXXXXXXX1415','ABC00000005')");
        db.execSQL("insert into tbl_user values(9000000006,'Tushar',5050.00,'Tushar@gmail.com','XXXXXXXXXXXX1617','ABC00000006')");
        db.execSQL("insert into tbl_user values(9000000007,'Harsh',4000.00,'Harsh@gmail.com','XXXXXXXXXXXX1819','ABC00000007')");
        db.execSQL("insert into tbl_user values(9000000008,'Sammed',3050.00,'Sammed@gmail.com','XXXXXXXXXXXX2021','ABC00000008')");
        db.execSQL("insert into tbl_user values(9000000009,'Rahul',2000.00,'Rahul@gmail.com','XXXXXXXXXXXX2022','ABC00000009')");
        db.execSQL("insert into tbl_user values(90000000010,'Rohit',1050.00,'Rohit@gmail.com','XXXXXXXXXXXX2023','ABC00000010')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_user");
        db.execSQL("DROP TABLE IF EXISTS tbl_trans");
        onCreate(db);
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tbl_user", null);
        return cursor;
    }

    public String transferData(String date, String fromname, String toname, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DATE", date);
        cv.put("FROMNAME", fromname);
        cv.put("TONAME", toname);
        cv.put("AMOUNT", amount);
        cv.put("STATUS", status);
        long result = db.insert("tbl_trans", null, cv);
        if (result == -1){
            return "Failed";
        }else {
            return "Success";
        }
    }

    public Cursor readTransferData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tbl_trans", null);
        return cursor;
    }

    public Cursor readparticulardata(String phoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tbl_user where PHONENUMBER = " +phoneNumber, null);
        return cursor;
    }

    public Cursor updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update tbl_user set balance = " + amount + " where phonenumber = " +phonenumber);
        return null;
    }

}
