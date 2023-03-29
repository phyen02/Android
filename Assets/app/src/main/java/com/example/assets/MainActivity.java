package com.example.assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.assets.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<String> adapter;
    String[] fontList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFonts();
        addEvents();
    }

    private void loadFonts(){
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
        AssetManager manager = getAssets();
        try{
            fontList = manager.list("fonts");
            adapter.addAll(fontList);
        } catch(Exception e){
            throw new RuntimeException(e);
        }

        binding.lvFonts.setAdapter(adapter);
    }

    private void addEvents() {
        binding.lvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/" + adapter.getItem(i));
                binding.txtList.setTypeface(tf);
                playAssets();
            }
        });
    }
    private void playAssets(){
        try{
            AssetFileDescriptor descriptor = getAssets().openFd("music/ting.mp3");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
            descriptor.close();
            player.prepare();
            player.start();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}