package com.gamma404.expense;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by forev on 2018/3/10.
 */

public class Expense implements Parcelable {
    int id;
    String date;
    String info;
    int amount;

    protected Expense(Parcel in) {
        id = in.readInt();
        date = in.readString();
        info = in.readString();
        amount = in.readInt();
        read = in.readInt();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(date);
        dest.writeString(info);
        dest.writeInt(amount);
        dest.writeInt(read);
    }
}
