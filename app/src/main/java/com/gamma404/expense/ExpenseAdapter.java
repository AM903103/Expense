package com.gamma404.expense;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by forev on 2018/3/10.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private Cursor cursor;
    private String TAG = ExpenseAdapter.class.getSimpleName();

    public ExpenseAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String cdate = cursor.getString(cursor.getColumnIndex("cdate"));
        final String info = cursor.getString((cursor.getColumnIndex("info")));
        String amount = cursor.getString((cursor.getColumnIndex("amount")));
        holder.dateTextView.setText(cdate);
        holder.intoTextView.setText(info);
        holder.amountTextView.setText(amount);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + info);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        } else {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView intoTextView;
        private final TextView dateTextView;
        private final TextView amountTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.main_recycler_date);
            intoTextView = itemView.findViewById(R.id.main_recycler_info);
            amountTextView = itemView.findViewById(R.id.main_recycler_amount);
        }
    }
}