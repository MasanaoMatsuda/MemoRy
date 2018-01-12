package com.product.android.memory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.product.android.memory.data.MemoContract;
import com.product.android.memory.data.MemoDbHelper;
import com.product.android.memory.data.MemoContract.MemoEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MemoDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });

        mDbHelper = new MemoDbHelper(this);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void insertMemo() {
        ContentValues values = new ContentValues();
        values.put(MemoEntry.COLUMN_MEMO_TITLE, "ダミータイトル");
        values.put(MemoEntry.COLUMN_MEMO_CONTENTS, "ダミーコンテンツ「あいうえお」");
        values.put(MemoEntry.COLUMN_MEMO_DATE, "2018.1.11");
        values.put(MemoEntry.COLUMN_MEMO_IMPORTANCE_LV, MemoEntry.MEMO_IMPORTANCE_LOW);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long newRow = db.insert(MemoEntry.TABLE_NAME, null, values);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert_dummy_data:
                Toast.makeText(this, "Insert", Toast.LENGTH_SHORT).show();
                insertMemo();
                displayDatabaseInfo();
            case R.id.delete_all_data:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(MemoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        try{
            TextView textView = (TextView)findViewById(R.id.text_view_memos);
            textView.setText("Number of rows in memo database table: "
                    + cursor.getCount() + "\n");

            int indexId = cursor.getColumnIndex(MemoEntry._ID);
            int indexTitle = cursor.getColumnIndex(MemoEntry.COLUMN_MEMO_TITLE);
            int indexContents = cursor.getColumnIndex(MemoEntry.COLUMN_MEMO_CONTENTS);
            int indexDate = cursor.getColumnIndex(MemoEntry.COLUMN_MEMO_DATE);
            int indexImportance = cursor.getColumnIndex(MemoEntry.COLUMN_MEMO_IMPORTANCE_LV);

            for (int i = cursor.getCount() - 1; i > -1  ; i--) {
                cursor.moveToPosition(i);
                int id = cursor.getInt(indexId);
                String title = cursor.getString(indexTitle);
                String contents = cursor.getString(indexContents);
                String date = cursor.getString(indexDate);
                int importance = cursor.getInt(indexImportance);

                String all = id + "/"
                        + title + "/"
                        + contents + "/"
                        + date + "/"
                        + importance + "\n";
                textView.append(all);
            }
        } finally {
            cursor.close();
        }
    }
}
