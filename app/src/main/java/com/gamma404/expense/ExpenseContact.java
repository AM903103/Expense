package com.gamma404.expense;

import android.net.Uri;

/**
 * Created by forev on 2018/3/9.
 */

public class ExpenseContact {

    public static final String EXPENSE_TABLE = "exp";
    public static final String COL_ID = "_id";
    public static final String COL_DATE = "cdate";
    public static final String COL_INFO = "info";
    public static final String COL_AMOUNT = "amount";
    public static final String CREATE_SQL =
            "create table " + EXPENSE_TABLE + " ( " +
                    COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_DATE + " TEXT, " +
                    COL_INFO + " TEXT, " +
                    COL_AMOUNT + " INTEGER )";
    public static final String authority = "com.gamma404.expense";
    public static final Uri uri = new Uri.Builder()
            .scheme("content")
            .authority(authority)
            .path(EXPENSE_TABLE)
            .build();
}
