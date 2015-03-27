package com.msjBand.kraftscangradle.kraftscan;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.amulyakhare.textdrawable.TextDrawable;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class EventListFragment extends ListFragment {

    private ArrayList<Event> mEvents;
    public static final String TAG = "EventListFragment";
    private EventAdapter adapter;
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Event e = ((EventAdapter) getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), EventPagerActivity.class);
        i.putExtra(EventFragment.EVENT_ID, e.getId());
        startActivity(i);

    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(R.string.event_title);

        setRandomColours(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday);
        mEvents = new ArrayList<Event>();


        mEvents.addAll(EventsLab.get(getActivity()).getEvents());
        //getListView().setDivider(null);

        previousState = EventsLab.get(getActivity()).isRemoveIrrelevant();



        adapter = new EventAdapter(mEvents);

        setListAdapter(adapter);

/*

        mHandler = new Handler();
        mUpdate = new Runnable() {
            @Override
            public void run() {

                sendFilters();
                mHandler.postDelayed(this, 2000);
            }
        };
        mHandler.postDelayed(mUpdate, 0); */

    }

    public void sendFilters() {
        if ((previousState != EventsLab.get(getActivity()).isRemoveIrrelevant()) || (previousName !=  EventsLab.get(getActivity()).getName())) {
            if (EventsLab.get(getActivity()).isRemoveIrrelevant() == true) {
                System.out.println("Hellop");
                adapter.getFilter().filter("t");
            }
            else {
                adapter.getFilter().filter(null);
            }
            previousState = EventsLab.get(getActivity()).isRemoveIrrelevant();

            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        sendFilters();
        adapter.notifyDataSetChanged();
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



    @Deprecated
    @SuppressWarnings("16")
    private class EventAdapter extends ArrayAdapter<Event> {

        private Filter filter;
        private final Object mLock = new Object();
        public ArrayList<Event> mItems;
        private int count;

        public EventAdapter(ArrayList<Event> Events) {
            super(getActivity(), 0, Events);
            mItems = Events;
            count = mItems.size();

        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        public void setCount(ArrayList<Event> Events) {
            count  = Events.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_event, null);

            }

            Event e = getItem(position);

            EventsLab eventsLab = EventsLab.get(getActivity());

            int flightstatus = eventsLab.getFlightOne();
            Event event = mEvents.get(position);
            Boolean isFlightOne = e.getIsFlightOne();
            Boolean isFlight = e.getIsFlight();
            Boolean isDepart = e.isDepart();

            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);

            Drawable Na = getResources().getDrawable(R.drawable.x);
            ShapeDrawable shape = new ShapeDrawable(new OvalShape());

            if ((flightstatus == 1) && (isFlightOne) && (isFlight)){ // items is both flight 1 and person is flight one
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(getResources().getColor(R.color.green));
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == -1) && (isFlight)) { // items is a flight, but flight status unknown
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(getResources().getColor(R.color.cyan));
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == 1) && (!isFlightOne) && (isFlight)) { // items is not flight one, but person is flying one
                imageView.setImageDrawable(Na);
                shape.getPaint().setColor(getResources().getColor(R.color.red));
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == 0) && (isFlightOne) && (isFlight)) { // items is flight one, but person not flying flight one
                imageView.setImageDrawable(Na);
                shape.getPaint().setColor(getResources().getColor(R.color.red));
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == 0) && (!isFlightOne) && (isFlight)) { // items is not flight one, and person not flying flihg tone
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(getResources().getColor(R.color.green));
                imageView.setBackgroundDrawable(shape);
            } else {
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(getResources().getColor(R.color.blue));
                imageView.setBackgroundDrawable(shape);
            }

            ImageView weekDay = (ImageView) convertView.findViewById(R.id.day_of_week);

            TextDrawable dayOfWeek = TextDrawable.builder().buildRound(event.getDayOfWeek(), retriveColor(event.getFullWeekDay()));
            weekDay.setImageDrawable(dayOfWeek);

            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.event_Title);
            titleTextView.setText(e.getTitle());

            TextView timeDate =
                    (TextView) convertView.findViewById(R.id.item_time_date);
            timeDate.setText( e.getListDate()+ "\n" + e.getLocalTime() );

            return convertView;


        }


        private class EventsFilter extends Filter {
            private boolean constraintState;

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();


                if (constraint == null) {
                    results.values = EventsLab.get(getActivity()).getMasterEvents();
                    results.count = EventsLab.get(getActivity()).getMasterEvents().size();

                } else {


                    ArrayList<Event> events = new ArrayList<Event>();

                    for (Event e : mEvents) {
                        if (e.isApplicable()) {
                            events.add(e);
                        }
                    }

                    results.values = events;
                    results.count = events.size();

                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mEvents.clear();
                mEvents.addAll((ArrayList)results.values);
                notifyDataSetChanged();

            }

        }

        @Override
        public Filter getFilter() {

            if (filter == null);
                filter = new EventsFilter();

            return filter;

        }
    }

}


