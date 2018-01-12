package com.product.android.memory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import com.product.android.memory.data.MemoContract.MemoEntry;
import com.product.android.memory.data.MemoDbHelper;

import java.util.Calendar;

/**
 * Created by masanao on 2018/01/11.
 */

public class EditActivity extends AppCompatActivity {

    private MemoDbHelper mDbHelper;
    private EditText mTitleEditText;
    private EditText mContentsEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        mTitleEditText = (EditText)findViewById(R.id.memo_title);
        mContentsEditText = (EditText)findViewById(R.id.memo_contents);

        mDbHelper = new MemoDbHelper(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {
        String titleString = mTitleEditText.getText().toString();
        String contentsString = mContentsEditText.getText().toString();
        String dateString = getDate();

        ContentValues values = new ContentValues();
        values.put(MemoEntry.COLUMN_MEMO_TITLE, titleString);
        values.put(MemoEntry.COLUMN_MEMO_CONTENTS, contentsString);
        values.put(MemoEntry.COLUMN_MEMO_DATE, dateString);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long newRowId = db.insert(MemoEntry.TABLE_NAME, null, values);
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
