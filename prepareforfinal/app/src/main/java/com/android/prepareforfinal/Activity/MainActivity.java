package com.android.prepareforfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.prepareforfinal.Adapter.BookAdapter;
import com.android.prepareforfinal.Db.DbHelper;
import com.android.prepareforfinal.Model.Book;
import com.android.prepareforfinal.R;
import com.android.prepareforfinal.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DbHelper db;
    BookAdapter adapter;
    ArrayList<Book> books;
    EditText edtName, edtPub, edtPrice;
    Button btnSave, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareDb();
        loadData();
    }

    private void loadData() {
        books = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + DbHelper.TBL_NAME);
        while (cursor.moveToNext()){
            books.add(new Book(cursor.getInt(0), cursor.getString(1)
                        , cursor.getString(2), cursor.getDouble(3)));
        }
        cursor.close();

        adapter = new BookAdapter(MainActivity.this, books, R.layout.book_list);
        binding.lvBook.setAdapter(adapter);
    }

    private void prepareDb() {
        db = new DbHelper(MainActivity.this);
        db.createSampleData();
    }

    public void openDialogAdd(Book book){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_add);

        edtName = dialog.findViewById(R.id.edtName);
        edtPub = dialog.findViewById(R.id.edtPub);
        edtPrice = dialog.findViewById(R.id.edtPrice);
        btnSave = dialog.findViewById(R.id.btnSave);
        btnClose = dialog.findViewById(R.id.btnCancle);

        btnSave = dialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    db.execSql("INSERT INTO " + DbHelper.TBL_NAME + " VALUES(null, '" + edtName.getText().toString().trim()
                            + "', '" + edtPub.getText().toString().trim() + "', " + edtPrice.getText().toString() + ")");
                    dialog.dismiss();
                    loadData();
                    Toast.makeText(MainActivity.this, "ADDING BOOK SUCCESS", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("Error", ex.getMessage());
                    Toast.makeText(MainActivity.this, "ADDING BOOK FAILED", Toast.LENGTH_SHORT).show();
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