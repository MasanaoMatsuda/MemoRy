package com.product.android.memory.data;

import android.provider.BaseColumns;


public class TopicContract {

    public static class TopicEntry implements BaseColumns{
        public static final String TABLE_NAME = "topics";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TOPIC = "topic";
        public static final String COLUMN_TOPIC_SOURCE = "source";
        public static final String COLUMN_TOPIC_DATE = "date";
        public static final String COLUMN_TOPIC_IMPORTANCE_LV = "importance_level";

        public static final int TOPIC_IMPORTANCE_HIGH = 1;
        public static final int TOPIC_IMPORTANCE_MIDDLE = 2;
        public static final int TOPIC_IMPORTANCE_LOW = 3;
    }

    public static class CommentEntry implements BaseColumns {
        public static final String TABLE_NAME = "comments";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_COMMENT_DATE = "date";
        public static final String COLUMN_TOPIC_ID = "topic_id";
    }
}
