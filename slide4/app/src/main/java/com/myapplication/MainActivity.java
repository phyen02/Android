package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.myapplication.adapter.BeerAdapter;
import com.myapplication.databinding.ActivityMainBinding;
import com.myapplication.models.Beer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding binding;
    BeerAdapter adapter;
    ArrayList<Beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initAdapter();
    }

    private void initAdapter() {
        initData();
        adapter = new BeerAdapter(MainActivity.this,R.layout.item,beers);
        binding.gvBeers.setAdapter(adapter);

        binding.gvBeers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),GriditemActivity.class);
                intent.putExtra("name",beers.get(i).getBeerName());
                intent.putExtra("image",beers.get(i).getBeerThumb());
                startActivity(intent);
            }
        });
    }

    private void initData() {
        beers = new ArrayList<>();
        beers.add(new Beer(R.drawable.heineken,"Heineken",19000));
        beers.add(new Beer(R.drawable.tiger,"Tiger",15000));
        beers.add(new Beer(R.drawable.beer333,"333",17000));
        beers.add(new Beer(R.drawable.sapporo,"Saporo",21000));
        beers.add(new Beer(R.drawable.larue,"Larue",14000));
        beers.add(new Beer(R.drawable.hanoi,"Hà Nội",20000));
        beers.add(new Beer(R.drawable.saigon,"Saigon",18000));
    }

}