package oldfiles.task;

import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.RequestQueue;
import oldfiles.callbacks.MasterEventsLoadedListener;
import oldfiles.extras.EventUtils;
import oldfiles.network.VolleySingleton;
import oldfiles.pojo.Event;

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
