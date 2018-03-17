package com.gamma404.expense;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private Expense expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        expense = getIntent().getParcelableExtra("EXPENSE");
        ((TextView) findViewById(R.id.detail_tv_date)).setText(expense.getDate());
        ((TextView) findViewById(R.id.detail_tv_info)).setText(expense.getInfo());
        ((TextView) findViewById(R.id.detail_tv_amount)).setText(expense.getAmount() + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return true :menu to be displayed;
        //return false: it will not be shown.
        getMenuInflater().inflate(R.menu.menu_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            Log.d(TAG, "onOptionsItemSelected: ");
            getContentResolver().delete(ExpenseContact.uri, " = ? ",
                    new String[]{expense.getId() + ""});
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
