package com.gamma404.expense;

import android.content.ContentValues;
import android.opengl.ETC1;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = AddActivity.class.getSimpleName();
    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        findViews();
    }

    private void findViews() {
        edDate = findViewById(R.id.add_ed_date);
        edInfo = findViewById(R.id.add_ed_info);
        edAmount = findViewById(R.id.add_ed_amount);
    }

    public void add(View view) {
        String date = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());

        boolean reminders =
                PreferenceManager.getDefaultSharedPreferences(this)
                        .getBoolean("pref_reminders", false);
        Log.d(TAG, "add: " + reminders);

        if (reminders && amount > 0) {
            Log.d(TAG, "add: " );
        }

        showDataInfo(date, info, amount+"");

//        DBHelper helper = new DBHelper(this, "expense.db", null, 1);
        ContentValues contenValues = new ContentValues();
        contenValues.put("cdate", date);
        contenValues.put("info", info);
        contenValues.put("amount", amount+"");

        AddExpenseService.insert(this, contenValues);

//        DBHelper.getInstance(this)
//                .getWritableDatabase().insert("exp",null, contenValues);

        //finish();
    }

    private void showDataInfo(String date, String info, String amount) {
        String result = "date :" + date + " ,info :" + info + " ,amount :" + amount;
        Log.d(TAG, "add: " + result);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
