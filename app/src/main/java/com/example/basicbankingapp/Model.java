package com.example.basicbankingapp;

public class Model {

    String phoneNo, name, balance, fromName, toName, date, trans_status;

    public Model() {
    }

    public Model(String phoneNo, String name, String balance) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.balance = balance;
    }

    public Model(String balance, String fromName, String toName, String date, String trans_status) {
        this.balance = balance;
        this.fromName = fromName;
        this.toName = toName;
        this.date = date;
        this.trans_status = trans_status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrans_status() {
        return trans_status;
    }

    public void setTrans_status(String trans_status) {
        this.trans_status = trans_status;
    }
}
