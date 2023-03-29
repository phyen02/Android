package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.listview.databinding.ActivityFoodsBinding;
import com.example.listview.databinding.ActivityMainBinding;

public class FoodsActivity extends AppCompatActivity {

    ActivityFoodsBinding binding;
    String [] foods;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_foods);
        binding = ActivityFoodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();

    }

    private void loadData() {
        foods = getResources().getStringArray(R.array.foodList);
        adapter = new ArrayAdapter<String>(FoodsActivity.this, android.R.layout.simple_list_item_1,foods);
        binding.lvFoods.setAdapter(adapter);
    }
}