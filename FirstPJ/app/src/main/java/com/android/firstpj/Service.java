package com.android.firstpj;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Service extends android.app.Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Intent - Manifest", "Service đã được khởi tạo");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Intent - Manifest", "Service đã bị huỷ");
    }
}
