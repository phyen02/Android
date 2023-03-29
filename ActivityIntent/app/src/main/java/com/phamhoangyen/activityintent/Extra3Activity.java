package com.phamhoangyen.activityintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.phamhoangyen.activityintent.databinding.ActivityExtra4Binding;

public class Extra3Activity extends AppCompatActivity {
    ActivityExtra4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_extra3);

        binding = ActivityExtra4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    private void getData() {
        //get data from mainactivity
        Intent intent = getIntent();
     /*   Intent intent = new Intent();
        int numb = intent.getIntExtra("numb",0);
        float grade = intent.getFloatExtra("grage", 0.0f);
        boolean checked = intent.getBooleanExtra("checked",false);
        String say = intent.getStringExtra("say");*/

        //Get data via Bundle
        Bundle bundle =  intent.getBundleExtra("myBundle");
        int numb = bundle.getInt("numb", 0);
        float grade = bundle.getFloat("grade", 0.0f);
        boolean checked = bundle.getBoolean("checked", false);
        String say = bundle.getString("say");



        //show data
        //  binding.txtContent.setText("Numb " + numb +"\n" + "Grade" + grade + "\n" + "Checked" + checked + "\n" + "Say" + say + "\n"  );
        binding.txtContent.setText("");
        binding.txtContent.append("Numb" + numb + "\n");
        binding.txtContent.append("Grade" + grade + "\n");
        binding.txtContent.append("Checked" + checked + "\n");
        binding.txtContent.append("Say" + say + "\n");


    }
}