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

        Event e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 1, 29, 15, 30, 0);
        e.setTitle("Mandatory Meeting");
        e.setOccurred(false);
        mEvents.add(e);



    }


    public static EventsLab get(Context c) {
        if (sEventsLab == null) {

            sEventsLab = new EventsLab(c.getApplicationContext());
        }

        return sEventsLab;

    }





}
