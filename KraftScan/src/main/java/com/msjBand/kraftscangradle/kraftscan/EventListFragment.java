package com.msjBand.kraftscangradle.kraftscan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EventListFragment extends ListFragment {

    private ArrayList<Event> mEvents;
    private static final String TAG = "EventListFragment";
    private EventAdapter adapter;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


        Log.d(TAG, "send in " + position);
        Intent i = new Intent(getActivity(), EventPagerActivity.class);
        i.putExtra(EventFragment.EVENT_ID, position);
        startActivity(i);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(R.string.event_title);

        mEvents = EventsLab.get(getActivity()).getEvents();
        //getListView().setDivider(null);


        adapter = new EventAdapter(mEvents);
        setListAdapter(adapter);
        getListView().smoothScrollToPosition(10);

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Deprecated
    @SuppressWarnings("16")
    private class EventAdapter extends ArrayAdapter<Event> {

        public EventAdapter(List<Event> Events) {
            super(getActivity(), 0, Events);
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
            Boolean isFlightOne = event.getIsFlightOne();
            Boolean isFlight = event.getIsFlight();
            Boolean isDepart = event.isDepart();

            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);

            Drawable Na = getResources().getDrawable(R.drawable.x);
            ShapeDrawable shape = new ShapeDrawable(new OvalShape());

            if ((flightstatus == 1) && (isFlightOne) && (isFlight)){ // items is both flight 1 and person is flight one
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(Color.GREEN);
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == -1) && (isFlight) && (isFlight)) { // items is a flight, but flight status unknown
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(Color.CYAN);
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == 1) && (!isFlightOne) && (isFlight)) { // items is not flight one, but person is flying one
                imageView.setImageDrawable(Na);
                shape.getPaint().setColor(Color.RED);
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == 0) && (isFlightOne) && (isFlight)) { // items is flight one, but person not flying flight one
                imageView.setImageDrawable(Na);
                shape.getPaint().setColor(Color.RED);
                imageView.setImageDrawable(shape);
            } else if ((flightstatus == 0) && (!isFlightOne) && (isFlight)) { // items is not flight one, and person not flying flihg tone
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(Color.GREEN);
                imageView.setBackgroundDrawable(shape);
            } else {
                Drawable image = getResources().getDrawable(event.getDrawableId());
                imageView.setImageDrawable(image);
                shape.getPaint().setColor(Color.BLUE);
                imageView.setBackgroundDrawable(shape);
            }



            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.event_Title);
            titleTextView.setText(e.getTitle());

            TextView timeDate =
                    (TextView) convertView.findViewById(R.id.item_time_date);
            timeDate.setText( e.getListDate()+ "\n" + e.getLocalTime() );

            return convertView;


        }
    }


}