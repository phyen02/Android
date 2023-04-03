package com.example.sharepreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.sharepreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static final String PREFERENCE_NAME = "my_data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_share_preferences_ex);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
    }

    private void addEvent(){
        binding.btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Save data into Shared{references
                SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                // Attach data
                editor.putInt("numb", 89);
                editor.putFloat("grades",8.9f);
                editor.putBoolean("checked", true);
                editor.putString("say", "Hello");

                editor.apply();
            }
        });
        binding.btnLoad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Save data into Shared{references
                SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                int numb = preferences.getInt("numb",0);
                float grades = preferences.getFloat("grades",0.0f);
                boolean checked = preferences.getBoolean("checked", false);
                String say = preferences.getString("say","");

                binding.txtContent.setText("");
                binding.txtContent.append("Numb" + numb + "\n");
                binding.txtContent.append("Grades" + grades + "\n");
                binding.txtContent.append("Checked" + checked + "\n");
                binding.txtContent.append("Say" + say + "\n");
            }
        });

    }
}