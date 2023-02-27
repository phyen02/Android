package com.example.appco7hon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button testButton = (Button)findViewById(R.id.btnChon);

        Bundle extras = getIntent().getExtras();
        String data = extras.getString("Value1");

//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

        testButton.setText(data);

        testButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    @Override
    public void finish(){
        Intent data = new Intent();
        data.putExtra("returnKey1", "Giá trị trả về thứ nhất");
        data.putExtra("returnKey2", "Giá trị trả về thứ hai");
        setResult(RESULT_OK, data);
        super.finish();
    }
}