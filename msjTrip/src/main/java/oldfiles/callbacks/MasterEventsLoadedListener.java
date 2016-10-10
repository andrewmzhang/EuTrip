package oldfiles.callbacks;

import oldfiles.pojo.Event;

import java.util.ArrayList;

/**
 * Created by andrewmzhang on 4/4/2016.
 */
public interface MasterEventsLoadedListener {
    public void onMasterEventsLoaded(ArrayList<Event> listMovies);
}

