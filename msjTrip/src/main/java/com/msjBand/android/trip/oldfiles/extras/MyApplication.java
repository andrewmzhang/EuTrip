package com.msjBand.android.trip.oldfiles.extras;

import android.app.Application;
import android.content.Context;
import com.msjBand.android.trip.oldfiles.database.DBEvents;

public class MyApplication extends Application {

    private static MyApplication sInstance;
    public static final String MasterEventsURL = "http://msj-band-trip.herokuapp.com/exportEvents.json";
    private static DBEvents mDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public synchronized static DBEvents getWritableDatabase() {
        if (mDatabase == null) {
            mDatabase = new DBEvents(getAppContext());
        }
        return mDatabase;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
