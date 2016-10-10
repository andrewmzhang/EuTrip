package com.msjBand.android.trip.singletons;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by andrewmzhang on 10/10/16.
 */
public class Utils {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }


}
