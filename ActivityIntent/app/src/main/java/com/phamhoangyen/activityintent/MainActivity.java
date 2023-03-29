package com.phamhoangyen.activityintent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.phamhoangyen.activityintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> launcher;
    ActivityMainBinding binding;
    public static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i("MainActivity", "onCreate");

        addEvent();

    }

    private void addEvent() {
        binding.btnSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExtraActivity.class);
                startActivity(intent);
            }
        });
        binding.btnSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Extra2Activity.class);
                startActivity(intent);
            }
        });

        binding.btnSub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open sub3 and send data
                Intent intent = new Intent(MainActivity.this, Extra3Activity.class);
                //attach data using putExtra
             /*   intent.putExtra("numb",89);
                intent.putExtra("grade",8.9f);
                intent.putExtra("checked",true );
                intent.putExtra("say","hello" );*/

                //attach data using Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("numb", 68);
                bundle.putFloat("grade", 6.8f);
                bundle.putBoolean("checked", true);
                bundle.putString("say", "hello");

                intent.putExtra("myBundle", bundle);


                startActivity(intent);
            }
        });

        binding.btnSub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Extra4Activity.class);

                //Attach data
                intent.putExtra("numb",binding.edtNumber.getText().toString());
                startActivityForResult(intent, REQUEST_CODE );
                launcher.launch(intent);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            binding.txtResult.setText(String.valueOf(data.getIntExtra("returned_data",0)));
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");

    }
}