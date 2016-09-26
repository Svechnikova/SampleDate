package com.example.tatyana.sampledate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Tatyana on 26.09.2016.
 */
public class ChangeTimeZone extends BroadcastReceiver {
    private static final String TAG = ChangeTimeZone.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive()");
        if (intent.getAction().equals(Intent.ACTION_TIMEZONE_CHANGED)) {
            Log.d(TAG, "ACTION_TIMEZONE_CHANGED received");
            showTimezone(context, intent.getStringExtra("time-zone"));
        }
    }

    private void showTimezone(Context context, String msg) {
//        Intent intent=new Intent("com.example.tatyana.sampledate.TIMEZONEACTIVITY");
        Intent intent= new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Message", msg);
        context.startActivity(intent);
    }
}
