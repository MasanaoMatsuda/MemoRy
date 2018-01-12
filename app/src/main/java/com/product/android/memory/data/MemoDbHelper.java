package com.product.android.memory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.product.android.memory.data.MemoContract.MemoEntry;


/**
 * Created by masanao on 2018/01/11.
 */

public class MemoDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "memos.db";
    private static final int DATABASE_VERSION = 1;

    public MemoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_MEMOS_TABLE = "CREATE TABLE " + MemoEntry.TABLE_NAME + " (" +
                MemoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MemoEntry.COLUMN_MEMO_TITLE + " TEXT NOT NULL," +
                MemoEntry.COLUMN_MEMO_CONTENTS + " TEXT," +
                MemoEntry.COLUMN_MEMO_DATE + " TEXT NOT NULL," +
                MemoEntry.COLUMN_MEMO_IMPORTANCE_LV + " INTEGER NOT NULL DEFAULT 1);";
        db.execSQL(SQL_CREATE_MEMOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
