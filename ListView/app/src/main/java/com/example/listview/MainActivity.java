package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.listview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter adapter;

    String [] drinks = {"Coca-Cola", "Pepsi", "Olong Tea", "Sting", "RedBull",
            "Coffee", "Milk", "Lemon Tea", "Milk Tea", "C2", "Soda"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        loadData();
    }

    private void loadData() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,drinks);
        binding.lvDrinks.setAdapter(adapter);
    }
}