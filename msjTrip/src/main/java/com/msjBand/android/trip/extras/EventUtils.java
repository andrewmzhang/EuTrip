package com.msjBand.android.trip.extras;

import android.util.Log;
import com.android.volley.RequestQueue;
import com.google.android.gms.gcm.PeriodicTask;
import com.msjBand.android.trip.database.DBEvents;
import com.msjBand.android.trip.json.Endpoints;
import com.msjBand.android.trip.json.Parser;
import com.msjBand.android.trip.json.Requester;
import com.msjBand.android.trip.pojo.Event;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Archimedes on 4/4/2016.
 */

public class EventUtils {
    public static ArrayList<Event> loadMasterEvents(RequestQueue requestQueue) {
        JSONArray response = Requester.requestMasterEventsJSON(requestQueue, Endpoints.getRequestUrlMasterEvents());
        ArrayList<Event> listEvents = Parser.parseEventsJSON(response);
        ArrayList<Event> oldEvents = MyApplication.getWritableDatabase().readEvents(DBEvents.EVENTS_MASTER);


        if (listEvents != null && (!listsAreEqual(listEvents, oldEvents))) {
            MyApplication.getWritableDatabase().insertEvents(DBEvents.EVENTS_MASTER, listEvents, true);
            MyApplication.getAppContext().getContentResolver().notifyChange(DBEvents.EVENTS_URI, null);
            Log.d("MyService", "Lists are not equal and not null");
        }


        if (listsAreEqual(listEvents,oldEvents)) {
            Log.d("MyService", "Lists are equal");
            return listEvents;
        }





        return listEvents;
    }

    public static boolean listsAreEqual(List<Event> one, List<Event> two){

        if (one == null || two == null)
            return false;

        if (one.size() != two.size())
            return false;

        for (Event e: one) {
            boolean isElementIn = false;
            for (Event f: two) {

                if (e.getId().equals(f.getId())) {
                    isElementIn = true;
                }
            }

            if (!isElementIn) {
                return false;
            }
        }


        return true;
    }


}

