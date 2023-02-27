package com.example.appco7hon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appco7hon.databinding.ActivityMainBinding;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button testButton = (Button)findViewById(R.id.btnChon);
        testButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("Value1", "This value one for Activity Second");
                i.putExtra("Value2", "This value one for Activity Second");

                int REQUEST_CODE = 9;
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == 9){
            if (data.hasExtra("returnKey1"));
            Toast.makeText(this, data.getExtras().getString("returnKey1"), Toast.LENGTH_SHORT).show();
        }
    }
}