package com.product.android.memory;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.product.android.memory.data.TopicDbHelper;
import com.product.android.memory.data.TopicContract.TopicEntry;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TopicDbHelper mTopicDbHelper;

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

        mTopicDbHelper = new TopicDbHelper(this);
        Log.d(TAG, "@@@onCreate()");
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "@@@onStart()");
        super.onStart();
        displayDatabaseInfo();
    }

/*
    private void insertMemo() {
        ContentValues values = new ContentValues();
        values.put(TopicEntry.COLUMN_MEMO_TITLE, "ダミータイトル");
        values.put(TopicEntry.COLUMN_MEMO_CONTENTS, "ダミーコンテンツ「あいうえお」");
        values.put(TopicEntry.COLUMN_MEMO_DATE, "2018.1.11");
        values.put(TopicEntry.COLUMN_MEMO_IMPORTANCE_LV, TopicEntry.MEMO_IMPORTANCE_LOW);

        SQLiteDatabase db = mTopicDbHelper.getWritableDatabase();
        long newRow = db.insert(TopicEntry.TABLE_NAME, null, values);
    }
*/


    private void displayDatabaseInfo() {
        Log.d(TAG, "@@@displayDataBaseInfo()");
        SQLiteDatabase db = mTopicDbHelper.getReadableDatabase();
        Cursor cursor = db.query(TopicEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        try{
            TextView textView = (TextView)findViewById(R.id.text_view_memos);
            textView.setText("Number of rows in topics table: "
                    + cursor.getCount() + "\n");

            int indexId = cursor.getColumnIndex(TopicEntry._ID);
            int indexTopic = cursor.getColumnIndex(TopicEntry.COLUMN_TOPIC);
            int indexSource = cursor.getColumnIndex(TopicEntry.COLUMN_TOPIC_SOURCE);
            int indexDate = cursor.getColumnIndex(TopicEntry.COLUMN_TOPIC_DATE);
            int indexImportance = cursor.getColumnIndex(TopicEntry.COLUMN_TOPIC_IMPORTANCE_LV);

            for (int i = cursor.getCount() - 1; i > -1  ; i--) {
                cursor.moveToPosition(i);
                int id = cursor.getInt(indexId);
                String topic = cursor.getString(indexTopic);
                String source = cursor.getString(indexSource);
                String date = cursor.getString(indexDate);
                int importance = cursor.getInt(indexImportance);

                String all = id + "/"
                        + topic + "/"
                        + source + "/"
                        + date + "/"
                        + importance + "\n";
                textView.append(all);
            }
        } finally {
            cursor.close();
        }
    }
}
