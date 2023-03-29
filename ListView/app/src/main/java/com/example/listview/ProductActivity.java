package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.listview.databinding.ActivityProductBinding;
import com.example.models.Product;


public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding binding;

    ArrayAdapter<Product> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initAdapter();

        addEvents();
    }

    private void addEvents() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edName.getText().toString();
                double price = Double.parseDouble(binding.edPrice.getText().toString());
                Product p = new Product(name,price);
                adapter.add(p);
            }
        });

        binding.lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product selectedProduct = adapter.getItem(i);
                Toast.makeText(ProductActivity.this,selectedProduct.getProductName() + " - " + selectedProduct.getProductPrice(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product p = adapter.getItem(i);
                adapter.remove(p);
                return false;
            }
        });
    }

    private void initAdapter() {
        adapter = new ArrayAdapter<Product>(ProductActivity.this,  android.R.layout.simple_list_item_1);
        binding.lvProduct.setAdapter(adapter);
    }
}