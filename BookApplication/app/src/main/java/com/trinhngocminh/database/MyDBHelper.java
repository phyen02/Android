package com.trinhngocminh.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static  final String DB_NAME = "book_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TB_NAME = "Book";

    public static final String COL_CODE = "BookCode";
    public static final String COL_NAME = "BookName";
    public static final String COL_PUBLISHER = "BookPublisher";
    public static final String COL_PUBLISHED = "BookPublished";
    public static final String COL_PRICE = "BookPrice";

    public MyDBHelper(@Nullable Context context) {
        super(context, TB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_NAME + "("+ COL_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                     + COL_NAME + " VARCHAR(150), "+ COL_PUBLISHER + " VARCHAR(150), "+ COL_PUBLISHED + " INTEGER , "
                + COL_PRICE + " REAL" +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
    }

    public void execSql(String sql){
        //Insert, Update, Delete...
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor queryData(String sql){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }

    public int getNumberOfRows(String sql){
        Cursor cursor = queryData(sql);
        int numbOfRows = cursor.getCount();
        cursor.close();
        return numbOfRows;
    }

    public void  createSampleData(){
        int numb = getNumberOfRows("SELECT * FROM " + TB_NAME);
        if (numb == 0){
            execSql("INSERT INTO " + TB_NAME + " VALUES(null, 'Dac Nhan Tam', 'Alpha', 12, 119000)");
            execSql("INSERT INTO " + TB_NAME + " VALUES(null, 'Con Cho Nho', 'Kim Dong', 5, 59000)");
            execSql("INSERT INTO " + TB_NAME + " VALUES(null, 'Co Gai Den Tu Hom Qua', 'Kim Dong', 4 ,59000)");
            execSql("INSERT INTO " + TB_NAME + " VALUES(null, '7 Buoc Den Mua He', 'Kim Dong', 6 ,69000)");
        }
    }
}
