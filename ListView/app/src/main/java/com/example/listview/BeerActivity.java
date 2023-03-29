package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.adapters.BeerAdapter;
import com.example.listview.databinding.ActivityBeerBinding;
import com.example.models.Beer;

import java.util.ArrayList;
import java.util.List;

public class BeerActivity extends AppCompatActivity {


    ActivityBeerBinding binding;
   // ArrayAdapter<Beer> adapter;
    BeerAdapter adapter;
    ArrayList<Beer> beers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_beer);
        binding = ActivityBeerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
    }

    private void initAdapter() {
        //adapter = new ArrayAdapter<Beer>(BeerActivity.this, android.R.layout.simple_list_item_1);
        initData();
        adapter = new BeerAdapter(BeerActivity.this,R.layout.item_list,beers);
        binding.lvBeers.setAdapter(adapter);
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