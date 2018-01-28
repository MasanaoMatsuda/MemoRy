package com.product.android.memory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.product.android.memory.data.TopicContract.TopicEntry;
import com.product.android.memory.data.TopicContract.CommentEntry;


public class TopicDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "memory.db";
    private static final int DATABASE_VERSION = 1;

    public TopicDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TOPICS_TABLE = "CREATE TABLE " + TopicEntry.TABLE_NAME + " (" +
                TopicEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TopicEntry.COLUMN_TOPIC + " TEXT NOT NULL," +
                TopicEntry.COLUMN_TOPIC_SOURCE + " TEXT," +
                TopicEntry.COLUMN_TOPIC_DATE + " TEXT NOT NULL," +
                TopicEntry.COLUMN_TOPIC_IMPORTANCE_LV + " INTEGER NOT NULL DEFAULT 1);";

        String SQL_CREATE_COMMENTS_TABLE = "CREATE TABLE " + CommentEntry.TABLE_NAME +  " (" +
                CommentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CommentEntry.COLUMN_COMMENT + " TEXT NOT NULL," +
                CommentEntry.COLUMN_COMMENT_DATE + " TEXT NOT NULL," +
                CommentEntry.COLUMN_TOPIC_ID + " INTEGER," +
                " FOREIGN KEY (" + CommentEntry.COLUMN_TOPIC_ID + ") " +
                "REFERENCES "+ TopicEntry.TABLE_NAME + " ("+ TopicEntry._ID +"));";

        db.execSQL(SQL_CREATE_TOPICS_TABLE);
        db.execSQL(SQL_CREATE_COMMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
