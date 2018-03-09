package com.gamma404.expense;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.Manifest.permission.READ_CONTACTS;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACT = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        checkPermission();
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) ==
                PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{READ_CONTACTS}, REQUEST_CONTACT);
        } else {
            readContacts();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupRecycleView();
    }

    private void readContacts() {
        ContentResolver resolver = getContentResolver();
        resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts();
            }
        }
    }


    private void setupRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = getContentResolver().query(ExpenseContact.uri
                , null, null, null, null);
        ExpenseAdapter adapter = new ExpenseAdapter(cursor);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
        private Cursor cursor;

        public ExpenseAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            cursor.moveToPosition(position);
            String cdate = cursor.getString(cursor.getColumnIndex("cdate"));
            String info = cursor.getString((cursor.getColumnIndex("info")));
            holder.dateTextView.setText(cdate);
            holder.intoTextView.setText(info);
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

            ViewHolder(View itemView) {
                super(itemView);
                dateTextView = itemView.findViewById(android.R.id.text1);
                intoTextView = itemView.findViewById(android.R.id.text2);
            }
        }
    }
}
