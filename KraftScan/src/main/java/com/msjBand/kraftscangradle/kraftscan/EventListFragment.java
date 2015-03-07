package com.msjBand.kraftscangradle.kraftscan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.List;


public class EventListFragment extends ListFragment {

    private ArrayList<Event> mEvents;
    private static final String TAG = "EventListFragment";
    private EventAdapter adapter;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Event e = ((EventAdapter) getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), EventActivity.class);
        i.putExtra(EventFragment.EVENT_ID, position);
        startActivity(i);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(R.string.event_title);

        mEvents = EventsLab.get(getActivity()).getEvents();
        getListView().setDivider(null);

        adapter = new EventAdapter(mEvents);
        setListAdapter(adapter);

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

            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);

            if ((flightstatus == 1) && (isFlightOne) && (isFlight)){ // items is both flight 1 and person is flight one
                Drawable icon = getResources().getDrawable(R.drawable.plane_landing);
                imageView.setImageDrawable(icon);
                ShapeDrawable shape = new ShapeDrawable(new OvalShape());
                shape.getPaint().setColor(Color.GREEN);
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == -1) && (isFlight) && (isFlight)) { // items is a flight, but flight status unknown
                TextDrawable circleThing = TextDrawable.builder().buildRound("?", Color.CYAN);
                imageView.setImageDrawable(circleThing);
            } else if ((flightstatus == 1) && (!isFlightOne) && (isFlight)) { // items is not flight one, but person is flying one
                Drawable icon = getResources().getDrawable(R.drawable.clear_plane);
                imageView.setImageDrawable(icon);
                ShapeDrawable shape = new ShapeDrawable(new OvalShape());
                shape.getPaint().setColor(Color.GREEN);
                imageView.setBackgroundDrawable(shape);
            } else if ((flightstatus == 0) && (isFlightOne) && (isFlight)) { // items is flight one, but person not flying flight one
                TextDrawable circleThing = TextDrawable.builder().buildRound("Na", Color.RED);
                imageView.setImageDrawable(circleThing);
            } else if ((flightstatus == 0) && (!isFlightOne) && (isFlight)) { // items is not flight one, and person not flying flihg tone
                ShapeDrawable shape = new ShapeDrawable(new OvalShape());
                shape.getPaint().setColor(Color.CYAN);
                imageView.setBackgroundDrawable(shape);
            }



            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.event_Title);
            titleTextView.setText(e.getTitle());

            return convertView;


        }
    }


}