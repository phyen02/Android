package com.nguyenduykhang.broadcastreceiver_ex;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.nguyenduykhang.broadcastreceiver_ex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    BroadcastReceiver receiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {


            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()){
                if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                    binding.imvState.setImageResource(R.drawable.baseline_wifi_24);
                    binding.txtState.setText("Connected with WIFI");
                }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                    binding.imvState.setImageResource(R.drawable.baseline_signal_cellular_alt_24);
                    binding.txtState.setText("Connected with Mobile Data");
                }
            }else{
                binding.imvState.setImageResource(R.drawable.baseline_do_disturb_24);
                binding.txtState.setText("No internet connection");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}