package com.example.appco7hon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ExService extends Service {
    @Override
    @Nullable
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        Log.e("ABC", "Service đã được khởi tạo");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("ABC", "Service đã được huỷ");
    }
}
