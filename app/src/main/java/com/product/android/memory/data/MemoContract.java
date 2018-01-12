package com.product.android.memory.data;

import android.provider.BaseColumns;

/**
 * Created by masanao on 2018/01/11.
 */

public class MemoContract {

    public static class MemoEntry implements BaseColumns{
        public static final String TABLE_NAME = "memo";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MEMO_TITLE = "title";
        public static final String COLUMN_MEMO_CONTENTS = "contents";
        public static final String COLUMN_MEMO_DATE = "date";
        public static final String COLUMN_MEMO_IMPORTANCE_LV = "importance_level";

        public static final int MEMO_IMPORTANCE_HIGH = 1;
        public static final int MEMO_IMPORTANCE_MIDDLE = 2;
        public static final int MEMO_IMPORTANCE_LOW = 3
                ;
    }
}
