package com.trinhngocminh.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trinhngocminh.adapter.BookAdapter;
import com.trinhngocminh.database.MyDBHelper;
import com.trinhngocminh.model.Book;
import com.trinhngocminh.test.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Book> books;
    MyDBHelper db;
    BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prePareDB();
        loadData();
    }

    private void loadData() {
        books = new ArrayList<>();
        //Loading data into ArrayList
        Cursor cursor = db.queryData("SELECT * FROM " + MyDBHelper.TB_NAME);
        while(cursor.moveToNext()){
            books.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                               cursor.getInt(3), cursor.getDouble(4)));
        }
        cursor.close();
        //Create adapter and sending data into adapter to view on UI
        adapter = new BookAdapter(MainActivity.this, R.layout.book_list, books);
        binding.container.setAdapter(adapter);
    }

    private void prePareDB() {
        db = new MyDBHelper(MainActivity.this);
        db.createSampleData();
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu ,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAdd){
            //Open dialog for adding product
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_add);

            EditText edtName = dialog.findViewById(R.id.edtNameBook);
            EditText edtPrice = dialog.findViewById(R.id.edtPriceBook);
            EditText edtPublisher = dialog.findViewById(R.id.edtPublisher);
            EditText edtPublished = dialog.findViewById(R.id.edtTimePublished);

            Button btnSave = dialog.findViewById(R.id.btnAdd);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        db.execSql("INSERT INTO " + MyDBHelper.TB_NAME + " VALUES(null, '" + edtName.getText().toString().trim()
                                + "', '" + edtPublisher.getText().toString().trim() + "', " + edtPublished.getText().toString().trim()
                                + ", " + edtPrice.getText().toString() + ")");
                        dialog.dismiss();
                        loadData();
                        Toast.makeText(MainActivity.this, "ADDING BOOK SUCCESS", Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Log.e("Error", ex.getMessage());
                        Toast.makeText(MainActivity.this, "ADDING BOOK FAILED", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Button btnCancle = dialog.findViewById(R.id.btnCancle);
            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void openEditDialog(Book book) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_edit);

        EditText edtName = dialog.findViewById(R.id.edtNameBook);
        EditText edtPrice = dialog.findViewById(R.id.edtPriceBook);
        EditText edtPublisher = dialog.findViewById(R.id.edtPublisher);
        EditText edtPublished = dialog.findViewById(R.id.edtTimePublished);

        edtName.setText(book.getNameBook());
        edtPublisher.setText(book.getPublisher());
        edtPublished.setText(String.valueOf(book.getTimePublished()));
        edtPrice.setText(String.valueOf(book.getPriceBook()));

        Button btnSave, btnClose;
        btnSave = dialog.findViewById(R.id.btnEdit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update data
                try{
                    db.execSql("UPDATE " + MyDBHelper.TB_NAME + " SET " + MyDBHelper.COL_NAME + "='" + edtName.getText().toString() + "', " + MyDBHelper.COL_PRICE + "=" + edtPrice.getText().toString() +  ", " + MyDBHelper.COL_PUBLISHER + " = '" + edtPublisher.getText().toString() + "', " + MyDBHelper.COL_PUBLISHED + " = " + edtPublished.getText().toString() +" WHERE " + MyDBHelper.COL_CODE + "=" + book.getCodeBook());
                    dialog.dismiss();
                    loadData();
                    Toast.makeText(MainActivity.this, "EDITING BOOK SUCCESS", Toast.LENGTH_SHORT).show();
                } catch (Exception ex){
                    Log.e("Error", ex.getMessage());
                    Toast.makeText(MainActivity.this, "EDITING BOOK FAILED", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnClose = dialog.findViewById(R.id.btnCancle);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void openDeleteDialog(Book book) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_delete);

        EditText editProductName = dialog.findViewById(R.id.edtNameBook);
        EditText edtPrice = dialog.findViewById(R.id.edtPriceBook);
        EditText edtPublisher = dialog.findViewById(R.id.edtPublisher);
        EditText edtPublished = dialog.findViewById(R.id.edtTimePublished);

        edtPublisher.setText(book.getPublisher());
        edtPublished.setText(String.valueOf(book.getTimePublished()));
        edtPrice.setText(String.valueOf(book.getPriceBook()));
        editProductName.setText(book.getNameBook());

        Button btnDelete, btnClose;
        btnDelete = dialog.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    db.execSql("DELETE FROM " + MyDBHelper.TB_NAME + " WHERE " + MyDBHelper.COL_NAME + " = '"
                            + editProductName.getText().toString() + "' AND " + MyDBHelper.COL_CODE + " = " + book.getCodeBook());
                    dialog.dismiss();
                    loadData();
                    Toast.makeText(MainActivity.this, "DELETING BOOK SUCCESS", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("Error", ex.getMessage());
                    Toast.makeText(MainActivity.this, "DELETING BOOK FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClose = dialog.findViewById(R.id.btnCancle);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}