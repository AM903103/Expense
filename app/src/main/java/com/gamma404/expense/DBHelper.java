package com.gamma404.expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.ext.DeclHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by forev on 2018/3/7.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String EXPENSE_DBNAME = "expense.db";
    private static DBHelper instance;
    private Context context;
    private final String TAG = DBHelper.class.getSimpleName();

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context, EXPENSE_DBNAME, null, DB_VERSION);
        }
        return instance;
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ExpenseContact.CREATE_SQL);

        readExpensesFromResources(db);
    }

    private void readExpensesFromResources(SQLiteDatabase db) {
        InputStream is = context.getResources().openRawResource(R.raw.expense);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        Log.d(TAG, "onCreate: 3");
        try {
            String line= in.readLine();
            while (line != null) {
                sb.append(line);
                line = in.readLine();
            }
            String json = sb.toString();
            JSONObject object = new JSONObject(json);
            JSONArray expense = object.getJSONArray("expenses");

            for (int i = 0; i < expense.length(); i++) {
                JSONObject exp = expense.getJSONObject(i);
                String date = exp.getString("cdate");
                String info = exp.getString("info");
                int amount = exp.getInt("amount");
                ContentValues values = new ContentValues();
                values.put("cdate", date);
                values.put("info", info);
                values.put("amount", amount);
                //會db recursive call
//                context.getContentResolver().insert(ExpenseContact.uri, values);
                //OK
                //long id = db.insert(ExpenseContact.EXPENSE_TABLE, null, values);
                AddExpenseService.insert(context,values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
