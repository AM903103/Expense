package com.gamma404.expense;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Expense expense = getIntent().getParcelableExtra("EXPENSE");
        ((TextView)findViewById(R.id.detail_tv_date)).setText(expense.getDate());
        ((TextView)findViewById(R.id.detail_tv_info)).setText(expense.getInfo());
        ((TextView)findViewById(R.id.detail_tv_amount)).setText(expense.getAmount()+"");
    }
}
