package com.msjBand.android.trip;


import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

public class EventsRecyclerFragment extends Fragment {

    private static final String TAG = "EventsRecyclerFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;



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
    private ArrayList<oldEvent> mOldEvents;


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }



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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new EventsRecyclerAdapter(mOldEvents);

        mRecyclerView.setAdapter(adapter);

        return root;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getActivity().setTitle(R.string.event_title);

        setRandomColours(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday);
        mOldEvents = new ArrayList<oldEvent>();
        setRetainInstance(true);


        mOldEvents.addAll(oldEventsLab.get(getActivity()).getEvents());

    }



}
