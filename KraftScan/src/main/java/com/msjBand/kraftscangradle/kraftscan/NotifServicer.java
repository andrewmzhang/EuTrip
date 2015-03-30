package com.msjBand.kraftscangradle.kraftscan;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;


public class NotifServicer extends IntentService {

    public static final String ACTION_SHOW_NOTIFICATION =
            "com.bignerdranch.android.photogallery.SHOW_NOTIFICATION";
    private static final String TAG = "NotificationService";
    private ArrayList<Event> mEvents;
    public static final String PREF_IS_ALARM_ON = "isAlarmOn";
    private Event mEvent;

    private static final int POLL_INTERVAL = 1000 * 30;


    public NotifServicer() {
        super(TAG);
    }

    private int DrawableIDConvernter(int initialDrawableId) {
        if (initialDrawableId == R.drawable.airport)
            return R.drawable.airport_1;
        if (initialDrawableId == R.drawable.dinner_secondary)
            return R.drawable.dinner_secondary_1;
        if (initialDrawableId == R.drawable.eifell)
            return R.drawable.eifell_1;
        if (initialDrawableId == R.drawable.flight_transfer)
            return R.drawable.flight_transfer_1;
        if (initialDrawableId == R.drawable.food)
            return R.drawable.food_1;
        if (initialDrawableId == R.drawable.morn)
            return R.drawable.morn_1;
        if (initialDrawableId == R.drawable.night)
            return R.drawable.night_1;
        if (initialDrawableId == R.drawable.noon)
            return R.drawable.noon_1;
        if (initialDrawableId == R.drawable.note)
            return R.drawable.note_1;
        if (initialDrawableId == R.drawable.plane_landing)
            return R.drawable.plane_landing_1;
        if (initialDrawableId == R.drawable.plane_takeoff)
            return R.drawable.plane_takeoff_1;
        if (initialDrawableId == R.drawable.sunset)
            return R.drawable.sunset_1;

        return initialDrawableId;


    }



    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);
        mEvents = EventsLab.get(getApplicationContext()).getEvents();

        for (int i = 0; i < mEvents.size(); i++) {
            Event e = mEvents.get(i);
            if (!e.determineIsOccured()) {
                if (e.isApplicable(EventsLab.get(getApplicationContext()).getFlightOne())) {
                    mEvent = e;
                    break;
                }

            }
        }

        Intent i = new Intent(this, EventActivity.class);

        EventsLab.get(getApplicationContext()).setIntentUUID(mEvent.getId());

        Log.i(TAG, "Stored ID:  " + mEvent.getId());

        Resources r = getResources();
        PendingIntent pi = PendingIntent
                .getActivity(this, 0, i, 0);

        int flightstatus = EventsLab.get(getApplicationContext()).getFlightOne();
        boolean isFlightOne = mEvent.getIsFlightOne();
        boolean isFlight = mEvent.getIsFlight();

        int drawableId;
        int colorID;

        if ((flightstatus == 1) && (isFlightOne) && (isFlight)) { // items is both flight 1 and person is flight one
            drawableId = mEvent.getDrawableId();
            colorID = (R.color.green);
        } else if ((flightstatus == -1) && (isFlight)) { // items is a flight, but flight status unknown
            drawableId = mEvent.getDrawableId();
            colorID = R.color.cyan;
        } else if ((flightstatus == 1) && (!isFlightOne) && (isFlight)) { // items is not flight one, but person is flying one
            drawableId = R.drawable.x;
            colorID = R.color.red;
        } else if ((flightstatus == 0) && (isFlightOne) && (isFlight)) { // items is flight one, but person not flying flight one
            drawableId = R.drawable.x;
            colorID = R.color.red;
            ;
        } else if ((flightstatus == 0) && (!isFlightOne) && (isFlight)) { // items is not flight one, and person not flying flihg tone
            drawableId = mEvent.getDrawableId();
            colorID = (R.color.green);
        } else {
            drawableId = mEvent.getDrawableId();
            colorID = mEvent.getColorID();
        }

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), drawableId);

        Notification mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(DrawableIDConvernter(mEvent.getDrawableId()))
                        .setTicker(mEvent.getTitle())
                        .setContentTitle(mEvent.getTitle())
                        .setContentText(mEvent.notifTime())
                        .setContentIntent(pi)
                        .setColor(getResources().getColor(colorID))
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
            Log.d("TAG", "Notifs Stopped");
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.cancel(0);
            alarmManager.cancel(pi);

            pi.cancel();
        }


        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(NotifServicer.PREF_IS_ALARM_ON, isOn)
                .commit();

    }

}
