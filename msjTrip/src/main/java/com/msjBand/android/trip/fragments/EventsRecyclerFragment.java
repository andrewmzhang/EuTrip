package com.msjBand.android.trip.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.msjBand.android.trip.R;
import com.msjBand.android.trip.adapters.EventsAdapter;
import com.msjBand.android.trip.pojo.Event;

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


    private DatabaseReference firebaseEvents = FirebaseDatabase.getInstance().getReference();


    // Creates a new instance of Events Recycler Fragment, with the databaseRefString being the database path
    public static EventsRecyclerFragment newInstance(String databaseRefString) {

        EventsRecyclerFragment fragment = new EventsRecyclerFragment();
        Bundle args = new Bundle();
        args.putString(DATAREF_KEY, databaseRefString);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseEvents = firebaseEvents.child(getArguments().getString(DATAREF_KEY));


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events_recycler, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new EventsAdapter(Event.class, R.layout.recycler_item_event, R.layout.recycler_item_header, RecyclerView.ViewHolder.class, firebaseEvents);

        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }
}