package com.example.slide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnP1, btnP2, btnP3;
    TextView txtReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        practice1();
        practice2();
        practice3();
    }

    public void practice1(){
        btnP1 = (Button)findViewById(R.id.btnP1);
        btnP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Practice1.class);
                startActivity(i);
            }
        });
    }

    public void practice2(){
        btnP2 = (Button)findViewById(R.id.btnP2);
        btnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Practice2.class);
                startActivity(i);
            }
        });
    }

    public void practice3(){
        btnP3 = (Button)findViewById(R.id.btnP3);
        btnP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Practice3.class);
                startActivity(i);
            }
        });
    }
}