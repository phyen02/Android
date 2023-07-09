package com.lehuynhanhquy.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lehuynhanhquy.test.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawTextViews();
            }
        });
    }

    private void drawTextViews() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();

        EditText edtNumbOfView = findViewById(R.id.edtNumbOfView);
        String inputText = edtNumbOfView.getText().toString();

        if (!inputText.isEmpty()) {
            int numbOfViews = Integer.parseInt(inputText);
            List<Integer> numberList = new ArrayList<>();

            // Thêm các số vào danh sách
            for (int i = 0; i < numbOfViews; i++) {
                numberList.add(i);
            }

            // Khởi tạo đối tượng Random
            Random random = new Random();

            for (int i = 0; i < numbOfViews; i++) {
                    // Lấy một số ngẫu nhiên từ danh sách
                    int randomIndex = random.nextInt(numberList.size());
                    int number = numberList.get(randomIndex);

                    // Xóa số đã lấy từ danh sách để không lặp lại
                    numberList.remove(randomIndex);

                    TextView textView = new TextView(this);

                    // Thiết lập các thuộc tính layout cho TextView
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    if (i%2 == 0){
                        params.width = getResources().getDimensionPixelSize(R.dimen.textview_width2);
                    }else {
                        params.width = getResources().getDimensionPixelSize(R.dimen.textview_width);
                    }
                    if((i/2)%2 == 0 ){
                        if (i%2 == 0){
                            params.width = getResources().getDimensionPixelSize(R.dimen.textview_width2);
                        }else {
                            params.width = getResources().getDimensionPixelSize(R.dimen.textview_width);
                        }
                    }else {
                        if (i%2 == 1){
                            params.width = getResources().getDimensionPixelSize(R.dimen.textview_width2);
                        }else {
                            params.width = getResources().getDimensionPixelSize(R.dimen.textview_width);
                        }
                    }
                    params.height = getResources().getDimensionPixelSize(R.dimen.textview_height);
                    params.setMargins(
                            getResources().getDimensionPixelSize(R.dimen.textview_margin),
                            getResources().getDimensionPixelSize(R.dimen.textview_margin),
                            getResources().getDimensionPixelSize(R.dimen.textview_margin),
                            getResources().getDimensionPixelSize(R.dimen.textview_margin)
                    );
                    textView.setLayoutParams(params);

                    textView.setId(View.generateViewId());
                    textView.setText(String.valueOf(number));
                    textView.setTextSize(40);
                    textView.setGravity(Gravity.CENTER);

                    if (number % 2 == 0) {
                        textView.setBackgroundColor(Color.BLUE);
                    } else {
                        textView.setBackgroundColor(Color.GRAY);
                    }

                    gridLayout.addView(textView);
            }
        }
    }
}