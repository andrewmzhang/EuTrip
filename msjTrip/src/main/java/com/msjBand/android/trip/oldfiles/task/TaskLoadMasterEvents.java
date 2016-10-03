package com.msjBand.android.trip.oldfiles.task;

import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.msjBand.android.trip.oldfiles.callbacks.MasterEventsLoadedListener;
import com.msjBand.android.trip.oldfiles.extras.EventUtils;
import com.msjBand.android.trip.oldfiles.network.VolleySingleton;
import com.msjBand.android.trip.oldfiles.pojo.Event;

import java.util.ArrayList;

/**
 * Created by andrewmzhang on 4/4/2016.
 */
public class TaskLoadMasterEvents extends AsyncTask<Void, Void, ArrayList<Event>>{


    private MasterEventsLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadMasterEvents(MasterEventsLoadedListener myComponent) {

        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }



    @Override
    protected ArrayList<Event> doInBackground(Void... params) {
        Log.d("MyService", "On Run task");
        ArrayList<Event> listEvents = EventUtils.loadMasterEvents(requestQueue);
        return listEvents;
    }

    @Override
    protected void onPostExecute(ArrayList<Event> listEvents) {

        
        if (myComponent != null) {
            myComponent.onMasterEventsLoaded(listEvents);

        }
    }

}
