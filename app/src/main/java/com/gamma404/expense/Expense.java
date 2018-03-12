package com.gamma404.expense;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by forev on 2018/3/10.
 */

public class Expense {
    int id;
    String date;
    String info;
    int amount;

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    int read;

    public Expense() {
    }

    public Expense(int id, String date, String info, int amount) {
        this.id = id;
        this.date = date;
        this.info = info;
        this.amount = amount;
    }

    public Expense(int id, String date, String info, int amount, int read) {
        this.id = id;
        this.date = date;
        this.info = info;
        this.amount = amount;
        this.read = read;
    }

    public Expense(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(ExpenseContact.COL_ID));
        date = cursor.getString(cursor.getColumnIndex(ExpenseContact.COL_DATE));
        info = cursor.getString((cursor.getColumnIndex(ExpenseContact.COL_INFO)));
        amount = cursor.getInt((cursor.getColumnIndex(ExpenseContact.COL_AMOUNT)));
        read = cursor.getInt((cursor.getColumnIndex(ExpenseContact.COL_READ)));

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

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpenseContact.COL_ID, id);
        contentValues.put(ExpenseContact.COL_DATE, date);
        contentValues.put(ExpenseContact.COL_INFO, info);
        contentValues.put(ExpenseContact.COL_AMOUNT, amount);
        contentValues.put(ExpenseContact.COL_READ, read);
        return contentValues;
    }
}
