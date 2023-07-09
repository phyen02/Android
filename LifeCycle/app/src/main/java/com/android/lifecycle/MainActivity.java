package com.android.lifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String STATE = "Trạng thái";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(STATE,"onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(STATE,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(STATE,"onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(STATE,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(STATE,"onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.e(STATE,"onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.e(STATE,"onSaveInstanceState");
    }
}