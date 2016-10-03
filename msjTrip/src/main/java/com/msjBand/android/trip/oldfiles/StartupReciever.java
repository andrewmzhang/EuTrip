package com.msjBand.android.trip.oldfiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class StartupReciever extends BroadcastReceiver {

    private static final String TAG = "StartupReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Received broadcast intent:" + intent.getAction());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);


        boolean isOn = prefs.getBoolean(NotifServicer.PREF_IS_ALARM_ON, false);
        Log.i(TAG, "" + isOn);
        NotifServicer.setServiceAlarm(context, !isOn);
    }
}
