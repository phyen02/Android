package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.sqlite.databinding.ActivityMainBinding;
import com.example.sqlite.models.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    SQLiteDatabase db;
    public static final String DB_NAME = "product_db.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static final String TBL_NAME = "Product";
    ArrayAdapter<Product> adapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        copyDB();
        openDB();
        loadDataFromDB();
        saveDataIntoListView();
    }

    private void copyDB(){
        try{
            File dbFile = getDatabasePath(DB_NAME);
            if(!dbFile.exists()){
                if(processCopy()){
                    Toast.makeText(MainActivity.this,"Copy database successful!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Copy database fail!", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e){
            Log.e("Error", e.toString());
        }
    }

    private void openDB(){
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
    }

    private boolean processCopy(){
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DB_NAME;
        try{
            InputStream inputStream = getAssets().open(DB_NAME);
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024]; int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush(); outputStream.close(); inputStream.close();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    private void loadDataFromDB(){
        Product p;
        // C1: Select data using rawQuery
        products = new ArrayList<Product>();
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE ProductId=? OR ProductId=?", new String[]{"2", "5"});

        Cursor cursor = db.query(TBL_NAME, null, "ProductId=? OR ProductId=?", new String[]{"1", "4"}, null, null, null);
        while(cursor.moveToNext()){
            int pId = cursor.getInt(0);
            String pName = cursor.getString(1);
            double pPrice = cursor.getDouble(2);

            // To do something ...
            p = new Product(pId, pName, pPrice);
            products.add(p);

            //Log.i("Data: ", pId + " - " + pName + " - " + pPrice);
        }
        cursor.close();
    }

    private void saveDataIntoListView(){
        adapter = new ArrayAdapter<Product>(MainActivity.this, android.R.layout.simple_list_item_1, products);
        binding.lvProduct.setAdapter(adapter);
    }
}