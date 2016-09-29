package com.example.tatyana.sampledate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by Tatyana on 26.09.2016.
 */
public abstract class TimeZoneChangeListener extends BroadcastReceiver {
    private static final String TAG = TimeZoneChangeListener.class.getName();
    private Context mContext;

    public TimeZoneChangeListener(Context mContext) {
        this.mContext = mContext;
    }

    public void register(){
        Log.d(TAG, "registerReceiver");
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIMEZONE_CHANGED);
        mContext.registerReceiver(this, filter);
    }

    public void unregister(){
        Log.d(TAG, "unregisterReceiver");
        mContext.unregisterReceiver(this);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive()");
        if (intent.getAction().equals(Intent.ACTION_TIMEZONE_CHANGED)) {
            Log.d(TAG, "ACTION_TIMEZONE_CHANGED received");
            onTimeZoneChanged(intent.getStringExtra("time-zone"));
        }
    }

    abstract void onTimeZoneChanged(String timezone);
}
