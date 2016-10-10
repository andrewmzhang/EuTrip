package com.msjBand.android.trip.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.database.*;
import com.msjBand.android.trip.R;
import com.msjBand.android.trip.adapters.EventsAdapter;
import com.msjBand.android.trip.pojo.Event;
import com.msjBand.android.trip.singletons.Utils;

public class EventsRecyclerFragment extends Fragment {


    /*


        Description: Contains a recyclerview that displays a list of master events from Firebase

        TODO:
            1. Implement





     */


    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    public static final String DATAREF_KEY = "DATAREFKEY";
    public static final String MASTEREVENTS_REF = "masterEvents";


    protected RecyclerView mRecyclerView;
    protected EventsAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected SwipeRefreshLayout mSwipeRefreshLayout;


    private DatabaseReference firebaseEvents = Utils.getDatabase().getReference();


    // Creates a new instance of Events Recycler Fragment, with the databaseRefString being the database path
    public static EventsRecyclerFragment newInstance(String databaseRefString) {

        EventsRecyclerFragment fragment = new EventsRecyclerFragment();
        Bundle args = new Bundle();
        args.putString(DATAREF_KEY, databaseRefString);
        fragment.setArguments(args);
        return fragment;

    }


    protected void checkConnectionState() {
        DatabaseReference connectedRef = Utils.getDatabase().getReference(".info/connected");
        connectedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = dataSnapshot.getValue(Boolean.class);
                if (connected) {
                    Log.d(TAG, "Connected");
                    Snackbar snack = Snackbar.make(
                            getActivity().findViewById(android.R.id.content),
                            "Connected",
                            Snackbar.LENGTH_SHORT);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.GREEN);
                    snack.show();
                } else {
                    Snackbar snack = Snackbar.make(
                            getActivity().findViewById(android.R.id.content),
                            "No Internet",
                            Snackbar.LENGTH_SHORT);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snack.show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseEvents = Utils.getDatabase().getReference().child(getArguments().getString(DATAREF_KEY));
        firebaseEvents.keepSynced(true);




    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        checkConnectionState();




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_events_recycler, container, false);


        rootView.setTag(TAG);


        // Remove the Swipe Refresh, we don't need it anymore. Not with Firebase Realtime.
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeEventsLists);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkConnectionState();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        DatabaseReference connectedRef = Utils.getDatabase().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = dataSnapshot.getValue(Boolean.class);
                if (connected) {
                    Log.d(TAG, "Auto Connected");
                    Snackbar snack = Snackbar.make(
                            getActivity().findViewById(android.R.id.content),
                            "Connected",
                            Snackbar.LENGTH_SHORT);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.GREEN);
                    snack.show();
                } else {
                    Log.d(TAG, "Auto Disconnect");
                    Snackbar snack = Snackbar.make(
                            getActivity().findViewById(android.R.id.content),
                            "No Internet",
                            Snackbar.LENGTH_SHORT);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snack.show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new EventsAdapter(Event.class, R.layout.recycler_item_event, R.layout.recycler_item_header, RecyclerView.ViewHolder.class, firebaseEvents);

        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }
}