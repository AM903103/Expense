package com.gamma404.expense;

import android.content.ContentValues;
import android.opengl.ETC1;
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
        String amount = edAmount.getText().toString();
        String result ="date :" + date + " ,info :" + info + " ,amount :" + amount;
        Log.d(TAG, "add: " + result );
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        DBHelper helper = new DBHelper(this, "expense.db", null, 1);
        ContentValues contenValues = new ContentValues();
        contenValues.put("cdate",date);
        contenValues.put("info",info);
        contenValues.put("amount", amount);
        helper.getWritableDatabase().insert("exp",null, contenValues);

    }
}
