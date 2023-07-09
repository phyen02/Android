package com.android.prepareforfinal.Db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "book.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Book";
    public static final String COL_CODE = "BookCode";
    public static final String COL_NAME = "BookName";
    public static final String COL_PUB = "BookPub";
    public static final String COL_PRICE = "BookPrice";

    public DbHelper(@Nullable Context context) {
        super(context, TBL_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " VARCHAR(150), " + COL_PUB + " VARCHAR(150), " + COL_PRICE + " REAL" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
    }

    public void execSql(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor queryData(String sql){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }

    public int getNumbOfRows(String sql){
        Cursor cursor = queryData(sql);
        int numb = cursor.getCount();
        cursor.close();
        return numb;
    }

    public void createSampleData(){
        int numb = getNumbOfRows("SELECT * FROM " + TBL_NAME);
        if (numb == 0){
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Dac Nhan Tam', 'Alpha', 80000)");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Van Hao Luu Lac', 'Kim Dong', 25000)");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Sach 1', 'Tre', 55000)");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Sach 2', 'Alpha', 34000)");
        }
    }
}
