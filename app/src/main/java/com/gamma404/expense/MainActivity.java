package com.gamma404.expense;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import static android.Manifest.permission.READ_CONTACTS;

public class MainActivity extends AppCompatActivity implements ExpenseAdapter.OnRecyclerViewItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int REQUEST_CONTACT = 110;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ExpenseAdapter adapter;

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
        getLoaderManager().initLoader(5, null, this);
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
        getLoaderManager().restartLoader(5, null, this);
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

        //兩種case 假定第二種是在id是2
        //Uri uri = ContentUris.withAppendedId(ExpenseContact.uri, 2);

        //ExpenseContract.uri 是第一種不含參數 取全部
        //uri 是第二種 取特定筆
//        Cursor cursor = getContentResolver().query(
////                ExpenseContact.uri
//                uri
//                , null, null, null, null);
//        ExpenseAdapter adapter = new ExpenseAdapter(cursor);
        adapter = new ExpenseAdapter();
        adapter.setOnRecyclerViewItemClickListener(this);
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


    @Override
    public void onItemClick(View view, Expense expense) {
        Log.d(TAG, "onItemClick: " + expense.getInfo());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, ExpenseContact.uri
                , null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swap(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        adapter.swap(null);
    }
}
