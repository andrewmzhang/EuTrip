package com.msjBand.android.trip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "Alarm.Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Recurring Alarm, onReceived");
    }
}