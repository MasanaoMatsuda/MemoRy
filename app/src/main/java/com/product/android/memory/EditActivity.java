package com.product.android.memory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.product.android.memory.data.TopicContract.TopicEntry;
import com.product.android.memory.data.TopicDbHelper;

import java.util.Calendar;


public class EditActivity extends AppCompatActivity {

    private static final String TAG = EditActivity.class.getSimpleName();

    private TopicDbHelper mTopicDbHelper;
    private EditText mTopicEditText;
    private EditText mSourceEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        mTopicEditText = (EditText)findViewById(R.id.edit_topic);
        mSourceEditText = (EditText)findViewById(R.id.edit_topic_source);

        mTopicDbHelper = new TopicDbHelper(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            saveData();
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void saveData() {
        String topic = mTopicEditText.getText().toString();
        String source = mSourceEditText.getText().toString();
        String date = getDate();

        // トピックが空の場合はsaveしない
        if (topic.isEmpty()) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(TopicEntry.COLUMN_TOPIC, topic);
        values.put(TopicEntry.COLUMN_TOPIC_SOURCE, source);
        values.put(TopicEntry.COLUMN_TOPIC_DATE, date);

        SQLiteDatabase db = mTopicDbHelper.getWritableDatabase();
        long newRowId = db.insert(TopicEntry.TABLE_NAME, null, values);
        if (newRowId != -1) {
            Toast.makeText(this,
                    "New topic is saved." + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return year + "/" + (month + 1) + "/" + day + "/" + " " +
                        hour + ":" + minute;
    }
}
