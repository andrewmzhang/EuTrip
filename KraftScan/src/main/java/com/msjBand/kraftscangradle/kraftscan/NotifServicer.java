package com.msjBand.kraftscangradle.kraftscan;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class NotifServicer extends IntentService {

    public static final String ACTION_SHOW_NOTIFICATION =
            "com.bignerdranch.android.photogallery.SHOW_NOTIFICATION";
    private static final String TAG = "NotificationService";
    private ArrayList<Event> mEvents;
    public static final String PREF_IS_ALARM_ON = "isAlarmOn";
    private Event mEvent;

    private static final int POLL_INTERVAL = 1000 * 60;


    public NotifServicer() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);
        mEvents = EventsLab.get(getApplicationContext()).getEvents();

        for (int i = 0; i < mEvents.size(); i++) {
            Event e = mEvents.get(i);
            if (!e.determineIsOccured()) {
                if (e.isApplicable())
                    mEvent = e;
                break;
            }
        }

        Resources r = getResources();
        PendingIntent pi = PendingIntent
                .getActivity(this, 0, new Intent(this, EventListActivity.class), 0);

        Notification mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setTicker(mEvent.getTitle())
                        .setContentTitle(mEvent.getTitle())
                        .setSmallIcon(R.drawable.eifell)
                        .setContentText(mEvent.notifTime())
                        .setContentIntent(pi)
                        .build();
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder);

        sendBroadcast(new Intent(ACTION_SHOW_NOTIFICATION));


    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent i = new Intent(context, NotifServicer.class);
        PendingIntent pi = PendingIntent.getService(
                context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    public static void setServiceAlarm(Context context, boolean isOn) {

        Intent i = new Intent(context, NotifServicer.class);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) {
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), POLL_INTERVAL, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(NotifServicer.PREF_IS_ALARM_ON, isOn)
                .commit();

    }

}
