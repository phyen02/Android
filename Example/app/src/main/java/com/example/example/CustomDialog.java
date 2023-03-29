package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CustomDialog extends Dialog {
    ImageView btnImgOk, btnImgCancel;
    Activity context;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = (Activity) context;
        setContentView(R.layout.activity_custom_dialog);
        addViews();
        setCanceledOnTouchOutside(false);
        addEvents();
    }

    private void addEvents() {
        btnImgOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
        btnImgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    private void addViews() {
        btnImgOk = findViewById(R.id.btnImgOk);
        btnImgCancel = findViewById(R.id.btnImgCancel);
    }
}

