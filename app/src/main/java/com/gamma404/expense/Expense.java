package com.gamma404.expense;

/**
 * Created by forev on 2018/3/10.
 */

public class Expense {
    int id;
    String date;
    String info;
    int amount;

    public Expense() {
    }

    public Expense(int id, String date, String info, int amount) {
        this.id = id;
        this.date = date;
        this.info = info;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
