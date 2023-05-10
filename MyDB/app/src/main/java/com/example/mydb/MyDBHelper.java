package com.example.mydb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "product_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Product";
    public static final String COL_CODE = "ProductCode";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public MyDBHelper(@Nullable Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CREATE TABLE IF NOT EXISTS Product(ProductCode INTEGER PRIMARY KEY AUTO INCREMENT, ProductName VARCHAR(50), ProductPrice REAL)
        String sql = "CREATE TABLE IF NOT EXISTS " +TBL_NAME+"("+COL_CODE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_NAME+" VARCHAR(50), "+COL_PRICE+" REAL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TBL_NAME);

    }

    public void execSql(String sql){
        //INSERT, UPDATE, DELETE,...
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor queryData(String sql){ //SELECT
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }
    public int getNummberOfRows(String sql){
        Cursor c = queryData("SELECT * FROM " + TBL_NAME);
        int numbOfRows = c.getCount();
        c.close();
        return numbOfRows;
    }

    public void createSampleData(){
        int numb = getNummberOfRows("SELECT * FROM " + TBL_NAME);
        if(numb == 0){
            execSql(" INSERT INTO " + TBL_NAME + " VALUES (null, 'Heineiken', 23000)");
            execSql(" INSERT INTO " + TBL_NAME + " VALUES (null, 'Tiger', 19000)");
            execSql(" INSERT INTO " + TBL_NAME + " VALUES (null, 'Sapporo', 21000)");
            execSql(" INSERT INTO " + TBL_NAME + " VALUES (null, 'Saigon', 18000)");
        }
    }
}

