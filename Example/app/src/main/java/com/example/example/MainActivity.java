package com.example.example;

import static com.example.example.databinding.ActivityMainBinding.inflate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;
    ImageView btnImgOk, btnImgCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        CustomDialog myCustomDialog = new CustomDialog(MainActivity.this);
        myCustomDialog.show();

//        binding.btnExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //
//                Dialog dialog = new Dialog(MainActivity.this);
//                dialog.setContentView(R.layout.activity_main);
//
//                ImageButton btnImgOk = dialog.findViewById(R.id.btnImgOk);
//                btnImgOk.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //
//                        finish();//close app
//                    }
//                });
//                ImageButton btnImgCancel = dialog.findViewById(R.id.btnImgCancel);
//                btnImgCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        });
    }
}