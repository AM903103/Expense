package com.gamma404.expense;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Created by forev on 2018/3/12.
 */

public class AddExpenseService extends IntentService{

    public static final String EXPENSE_VALUES = "EXPENSE_VALUES";
    private static final String TAG = AddExpenseService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AddExpenseService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ContentValues values = intent.getParcelableExtra(EXPENSE_VALUES);
        getContentResolver().insert(ExpenseContact.uri, values);
    }

    public static void insert(Context context, ContentValues values) {
        Intent intent = new Intent(context, AddExpenseService.class);
        intent.putExtra(EXPENSE_VALUES, values);
        context.startService(intent);
    }
}
