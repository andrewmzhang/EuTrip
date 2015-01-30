package com.msjBand.kraftscangradle.kraftscan;


import android.content.Context;
import android.provider.CalendarContract;

import java.util.ArrayList;
import java.util.TimeZone;


public class EventsLab {

    private static EventsLab sEventsLab;
    private Context mAppContext;

    private ArrayList<Event> mEvents;

    private EventsLab(Context appContext) {

        mAppContext = appContext;
        mEvents = new ArrayList<Event>();

        // Mandatory Meeting
        Event e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 1, 29, 15, 30, 0);
        e.setTitle("Mandatory Meeting");
        e.setOccurred(false);
        mEvents.add(e);

        // Arrival @ 5am
        e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 5, 0, 0);
        e.setTitle("Airport SFO");
        e.setNotes("Arrive at SFO American Airlines Domestic Departures Terminal 2");
        e.setOccurred(false);
        mEvents.add(e);

        // Departure of Flight 2283
        e =  new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 8, 35, 0);
        e.setTitle("Flight 2283 Departure");





    }


    public static EventsLab get(Context c) {
        if (sEventsLab == null) {

            sEventsLab = new EventsLab(c.getApplicationContext());
        }

        return sEventsLab;

    }





}
