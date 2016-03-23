package com.msjBand.android.trip;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;


public class EventActivity extends SingleFragmentActivity {

    public static final String EVENT_NAME = "Event.Name";

    @Override
    protected Fragment createFragment() {
        UUID id = oldEventsLab.get(getApplicationContext()).getIntentUUID();
        Log.d("TAG", "UUID recieved by EventActivity " + id);
        getSupportActionBar().setTitle(oldEventsLab.get(getApplicationContext()).getEvent(id).getTitle());

        return EventFragment.newInstance(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        UUID id = oldEventsLab.get(getApplicationContext()).getIntentUUID();

        getSupportActionBar().setTitle(oldEventsLab.get(getApplicationContext()).getEvent(id).getTitle());

    }
}
