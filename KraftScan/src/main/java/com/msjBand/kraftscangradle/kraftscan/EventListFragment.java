package com.msjBand.kraftscangradle.kraftscan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EventListFragment extends ListFragment {

    private ArrayList<Event> mEvents;
    private static final String TAG = "EventListFragment";

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

        EventAdapter adapter = new EventAdapter(mEvents);
        setListAdapter(adapter);

    }


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

            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.event_Title);
            titleTextView.setPaintFlags(titleTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            titleTextView.setText(e.getTitle());

            return convertView;


        }
    }


}