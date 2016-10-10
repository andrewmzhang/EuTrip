package oldfiles.fragments;


import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.*;
import com.msjBand.android.trip.R;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;
import oldfiles.adapters.EventsRecyclerAdapter;
import oldfiles.callbacks.MasterEventsLoadedListener;
import oldfiles.database.DBEvents;
import oldfiles.extras.MyApplication;
import oldfiles.logging.L;
import oldfiles.network.VolleySingleton;
import oldfiles.pojo.Event;
import oldfiles.task.TaskLoadMasterEvents;

import java.util.ArrayList;

public class EventsRecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MasterEventsLoadedListener {

    private static final String TAG = "EventsRecyclerFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final String MASTER_EVENTS = "master_events";

    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private EventsMasterObserver mEventsMasterObserver;

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    private SwipeRefreshLayout refreshLayout;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private ArrayList<String> ids;

    private EventsRecyclerAdapter adapter;
    private String previousName = "";

    private Handler mHandler;
    private Runnable mUpdate;
    private boolean previousState;

    private int Monday;
    private int Tuesday;
    private int Wednesday;
    private int Thursday;
    private int Friday;
    private int Saturday;
    private int Sunday;

    private int index;
    private int top;
    private ArrayList<Event> mEvents = new ArrayList<>();

    private class EventsMasterObserver extends ContentObserver {
        public EventsMasterObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mEvents.clear();
                    mEvents = MyApplication.getWritableDatabase().readEvents(DBEvents.EVENTS_MASTER);
                    adapter.set(mEvents);
                }
            });

        }
    }


    @Override
    public void onRefresh() {

        new TaskLoadMasterEvents(this).execute();
    }


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    public void onMasterEventsLoaded(ArrayList<Event> listEvents) {
        if (refreshLayout.isRefreshing()) {
            if (listEvents == null) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        L.t(getActivity(), "Cannot Refresh Feed");
                    }
                }, 1500);
            } else {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        L.t(getActivity(), "Done");
                    }
                }, 1500);
            }
        }

        Log.d("MyService", "Updating View");
        if (listEvents != null) {
            adapter.set(listEvents);
        }
    }


    /*
        private void setRandomColours(int mon, int tues, int wed, int thurs, int fri, int sat, int sun) {
            ArrayList<Integer> colors = new ArrayList<Integer>();
            Resources res = getResources();
            colors.add(res.getColor(R.color.deepOrange));
            colors.add(res.getColor(R.color.orange));
            colors.add(res.getColor(R.color.lightGreen));
            colors.add(res.getColor(R.color.pink));
            colors.add(res.getColor(R.color.teal));
            colors.add(res.getColor(R.color.blueGrey));
            colors.add(res.getColor(R.color.deepPurple));
            Collections.shuffle(colors);
            Monday = colors.get(0);
            Tuesday = colors.get(1);
            Wednesday = colors.get(2);
            Thursday = colors.get(3);
            Friday = colors.get(4);
            Saturday = colors.get(5);
            Sunday = colors.get(6);

        }
    */
    private int retriveColor(String name) {
        if(name.equals("Monday"))
            return Monday;
        else if (name.equals("Tuesday"))
            return Tuesday;
        else if (name.equals("Wednesday"))
            return Wednesday;
        else if (name.equals("Thursday"))
            return Thursday;
        else if (name.equals("Friday"))
            return Friday;
        else if (name.equals("Saturday"))
            return Saturday;
        else if (name.equals("Sunday"))
            return Sunday;
        else
            return Color.TRANSPARENT;
    }







    /*
            //getListView().setDivider(null);

        previousState = EventsLab.get(getActivity()).isRemoveIrrelevant();



        adapter = new EventAdapter(mEvents);
     */


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events_recycler, container, false);
        root.setTag(TAG);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeEventsLists);
        refreshLayout.setColorSchemeResources(R.color.accent, R.color.primary, R.color.primary_text);
        refreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        RecyclerView.ItemAnimator animator = new OvershootInRightAnimator();
        animator.setRemoveDuration(250);
        animator.setAddDuration(1000);

        mRecyclerView.setItemAnimator(animator);



        adapter = new EventsRecyclerAdapter();

        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setAdapter(adapter);

        if (savedInstanceState != null) {
            mEvents = savedInstanceState.getParcelableArrayList(MASTER_EVENTS);

        } else {
            mEvents = MyApplication.getWritableDatabase().readEvents(DBEvents.EVENTS_MASTER);
            if (mEvents.isEmpty()) {
                new TaskLoadMasterEvents(this).execute();
            }


        }

        adapter.set(mEvents);

        return root;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MASTER_EVENTS, mEvents);
    }

    private void handleVolleyError(VolleyError error) {
        //if any error occurs in the network operations, show the TextView that contains the error message
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            Toast.makeText(MyApplication.getAppContext(), "Bad connection. Try again!!", Toast.LENGTH_LONG).show();

        } else if (error instanceof AuthFailureError) {
            Toast.makeText(MyApplication.getAppContext(), "Can't authenticate!!", Toast.LENGTH_LONG).show();
            //TODO
        } else if (error instanceof ServerError) {
            Toast.makeText(MyApplication.getAppContext(), "Server is down!!", Toast.LENGTH_LONG).show();
            //TODO
        } else if (error instanceof NetworkError) {
            Toast.makeText(MyApplication.getAppContext(), "Bad network. Try again!!", Toast.LENGTH_LONG).show();
            //TODO
        } else if (error instanceof ParseError) {
            Toast.makeText(MyApplication.getAppContext(), "Can't read data. Try again!!", Toast.LENGTH_LONG).show();
            //TODO
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //getActivity().setTitle(R.string.event_title);


        mEventsMasterObserver = new EventsMasterObserver();
        MyApplication.getAppContext().getContentResolver().registerContentObserver(DBEvents.EVENTS_URI, false, mEventsMasterObserver);

        setRetainInstance(true);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mEventsMasterObserver != null) {
            MyApplication.getAppContext().getContentResolver().unregisterContentObserver(mEventsMasterObserver);
            mEventsMasterObserver = null;
        }
    }
}
