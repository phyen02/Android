package com.example.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thread.databinding.ActivityMainBinding;

import java.util.Random;

public class ThreadMethod2 extends AppCompatActivity {

    ActivityMainBinding binding;
    Random random = new Random();

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT);

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.arg1 ==100){
                binding.txtPercent.setText("DONE!");
            }else{
                ImageView imv = new ImageView(ThreadMethod2.this);
                if((int) msg.obj %2 == 00){

                    imv.setImageResource(R.drawable.baseline_auto_awesome_24 );
                }else{

                    imv.setImageResource(R.drawable.baseline_balance_24 );
                }
                imv.setLayoutParams(params);
                binding.container.addView(imv);
                binding.txtPercent.setText(msg.arg1 + " %");

            }
            binding.pbPercent.setProgress(msg.arg1);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
    }

    private void addEvent() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawUI();
            }
        });
    }

    private void drawUI() {
        // Using message
        int numb = Integer.parseInt(binding.edtNumberOfView.getText().toString());
        binding.container.removeAllViews();
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=numb; i++){
                    Message message = handler.obtainMessage();
                    message.arg1 = i*100/numb; //Percent
                    message.obj = random.nextInt(100);
                    handler.sendMessage(message);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }
}
