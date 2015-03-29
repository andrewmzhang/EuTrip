package com.msjBand.kraftscangradle.kraftscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "Alarm.Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Recurring Alarm, onReceived");
    }
}
