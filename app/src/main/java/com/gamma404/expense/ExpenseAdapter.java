package com.gamma404.expense;

import android.content.CursorLoader;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by forev on 2018/3/10.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private Cursor cursor;
    private String TAG = ExpenseAdapter.class.getSimpleName();

    OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public ExpenseAdapter() {

    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void swap(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        final Expense expense = new Expense(cursor);
        holder.setModel(expense);
        holder.itemView.setTag(expense);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + expense.getInfo());
                if (onRecyclerViewItemClickListener != null) {
                    onRecyclerViewItemClickListener.onItemClick(v,//老師這裡用holder.itemView
                            expense);
                }
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
        private final CheckBox readCheckBox;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.main_recycler_date);
            intoTextView = itemView.findViewById(R.id.main_recycler_info);
            amountTextView = itemView.findViewById(R.id.main_recycler_amount);
            readCheckBox = itemView.findViewById(R.id.main_recycler_read);
        }

        public void setModel(final Expense expense) {
            dateTextView.setText(expense.getDate());
            intoTextView.setText(expense.getInfo());
            amountTextView.setText(expense.getAmount() + "");
            readCheckBox.setChecked(expense.getRead() != 0);
            readCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    expense.setRead(isChecked ? 1 : 0);
                    itemView.getContext().getContentResolver()
                            .update(ExpenseContact.uri,
                                    expense.getContentValues()
                                    , ExpenseContact.COL_ID + " = ? "
                                    , new String[]{expense.getId() + ""});
                }
            });
        }
    }

    public interface OnRecyclerViewItemClickListener {
        public void onItemClick(View view, Expense expense);
    }
}