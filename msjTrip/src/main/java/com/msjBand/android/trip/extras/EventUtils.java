package com.msjBand.android.trip.extras;

import com.android.volley.RequestQueue;
import com.msjBand.android.trip.database.DBEvents;
import com.msjBand.android.trip.json.Endpoints;
import com.msjBand.android.trip.json.Parser;
import com.msjBand.android.trip.json.Requester;
import com.msjBand.android.trip.pojo.Event;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Archimedes on 4/4/2016.
 */

public class EventUtils {
    public static ArrayList<Event> loadMasterEvents(RequestQueue requestQueue) {
        JSONArray response = Requester.requestMasterEventsJSON(requestQueue, Endpoints.getRequestUrlMasterEvents());
        ArrayList<Event> listEvents = Parser.parseEventsJSON(response);
        if (listEvents != null) {
            MyApplication.getWritableDatabase().insertEvents(DBEvents.EVENTS_MASTER, listEvents, true);
        }
        return listEvents;
    }


}

