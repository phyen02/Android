package com.example.mydb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.adapter.ProductAdapter;
import com.example.models.Product;
import com.example.mydb.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ProductAdapter adapter;
    ArrayList<Product> products;

    MyDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prePareDB();
        loadData();
    }
    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAdd){
            //Open dialog for adding product
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_add);

            EditText edtName = dialog.findViewById(R.id.edtProductName);
            EditText edtPrice = dialog.findViewById(R.id.edtProductPrice);

            Button btnSave = dialog.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.execSql("INSERT INTO " + MyDBHelper.TBL_NAME + " VALUES(null, '" + edtName.getText().toString() + "', " + edtPrice.getText().toString() + ")");
                    dialog.dismiss();
                    loadData();
                }
            });

            Button btnClose = dialog.findViewById(R.id.btnCLose);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        products = new ArrayList<>();
        //Get data from db
        Cursor c = db.queryData("SELECT * FROM " + MyDBHelper.TBL_NAME);
        int code;
        String name;
        double price;
        Product product;

        while(c.moveToNext()){
            code = c.getInt(0);
            name = c.getString(1);
            price = c.getDouble(2);
            product = new Product(code, name, price);
            products.add(product);
        }
        c.close();

        adapter = new ProductAdapter(MainActivity.this, R.layout.item_list, products);
        binding.lvProduct.setAdapter(adapter);
    }

    public void openEditDialog(Product p){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_edit);

        EditText edtName, edtPrice;
        edtName = dialog.findViewById(R.id.edtName);
        edtPrice = dialog.findViewById(R.id.edtPrice);

        edtName.setText(p.getProductName());
        edtPrice.setText(String.valueOf(p.getProductPrice()));

        Button btnSave, btnClose;
        btnSave = dialog.findViewById(R.id.btnSave);
        btnClose = dialog.findViewById(R.id.btnCLose);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSql("UPDATE " + MyDBHelper.TBL_NAME + " SET " + MyDBHelper.COL_NAME + "=" + edtName.getText().toString()
                        + "', " + MyDBHelper.COL_PRICE + "=" + edtPrice.getText().toString() + " WHERE " + MyDBHelper.COL_CODE +
                        "=" + p.getProductCode());
                dialog.dismiss();
                loadData();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void prePareDB() {
        db = new MyDBHelper(MainActivity.this);
        db.createSampleData();
    }
}