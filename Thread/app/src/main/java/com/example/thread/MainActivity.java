package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.thread.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Random random = new Random();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT);
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_main);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            addEvents();
    }

    int percent, randNumb;
    Handler handler = new Handler();
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            // Update UI
            binding.txtPercent.setText(percent + "%");

            if (percent == 100)
                binding.txtPercent.setText("DONE!!!");
            binding.pbPercent.setProgress(percent);

            ImageView imv = new ImageView(MainActivity.this);
            imv.setLayoutParams(params);

            if (randNumb % 2 == 0) {
                imv.setImageResource(R.drawable.baseline_balance_24);
            } else {
                imv.setImageResource(R.drawable.baseline_auto_awesome_24);
            }
            binding.container.addView(imv);
        }
    };

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw();
            }
        });
    }

    private void draw() {
        // Using post
        int numb = Integer.parseInt(binding.edtNumberOfView.getText().toString());
        binding.container.removeAllViews();
        Thread bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= numb; i++) {
                    percent = i * 100 / numb;
                    randNumb = random.nextInt(100);
                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        bgThread.start();
    }
}