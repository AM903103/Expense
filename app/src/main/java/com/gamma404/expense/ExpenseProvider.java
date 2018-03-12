package com.gamma404.expense;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by forev on 2018/3/9.
 */

public class ExpenseProvider extends ContentProvider {

    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String TAG = ExpenseProvider.class.getSimpleName();
    DBHelper helper;

    public static final int EXPENSE = 100;

    public static final int EXPENSE_WITH_ID = 200;

    static {
        sUriMatcher.addURI(ExpenseContact.authority, ExpenseContact.EXPENSE_TABLE, EXPENSE);
        sUriMatcher.addURI(ExpenseContact.authority, ExpenseContact.EXPENSE_TABLE + "/#"
                , EXPENSE_WITH_ID);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        helper = DBHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] projection,
            @Nullable String selection,
            @Nullable String[] selectionArgs,
            @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {//uri是用這的不是ExpenseContact的
            case EXPENSE:
                cursor = helper.getReadableDatabase().query(ExpenseContact.EXPENSE_TABLE
                        , null, null, null, null, null, null);
                break;
            case EXPENSE_WITH_ID:
                cursor = helper.getReadableDatabase().query(ExpenseContact.EXPENSE_TABLE
                        , null, null, null, null, null, null);
                selection = (selection == null) ? "" : selection;
                String where = ExpenseContact.COL_ID + " = ? ";
                selection = selection + where;
                long id = ContentUris.parseId(uri);//uri是用這的不是ExpenseContact的
                selectionArgs = new String[]{String.valueOf(id)};
                cursor = helper.getReadableDatabase().query(ExpenseContact.EXPENSE_TABLE
                        , projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = helper.getWritableDatabase()
                .insert(ExpenseContact.EXPENSE_TABLE
                        , null, values);

        if (id > 0) {
            return ContentUris.withAppendedId(ExpenseContact.uri, id);
        } else {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount = helper.getWritableDatabase()
                .update(ExpenseContact.EXPENSE_TABLE,
                        values,
                        selection,
                        selectionArgs);
        return rowCount;
    }
}
