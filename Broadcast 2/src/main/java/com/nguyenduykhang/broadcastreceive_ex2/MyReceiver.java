package com.nguyenduykhang.broadcastreceive_ex2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyReceiver extends BroadcastReceiver {

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yy", Locale.getDefault());
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        Object[] arrMessages = (Object[]) bundle.get("pdus");
        assert arrMessages != null;
        String phone, time, content;
        long date;
        byte[] bytes;
        for(Object arrMessage:arrMessages){
            bytes = (byte[]) arrMessage;
            SmsMessage message = SmsMessage.createFromPdu(bytes);
            phone = message.getDisplayOriginatingAddress();
            date = message.getTimestampMillis();
            time = dateFormat.format(date);
            content = message.getMessageBody();

            Toast.makeText(context,"Phone: " + phone + "\nTime: " + time + "\n Content: " + content, Toast.LENGTH_LONG).show();
        }
    }
}
