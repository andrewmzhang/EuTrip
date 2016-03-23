package com.msjBand.android.trip;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

public class EventFragment extends Fragment {

    public static final String EVENT_ID =
            "com.msjBand.kraftscangradle.kraftscan";
    public static final String EVENT_FLIGHT_TYPE =
            "com.msjBand.kraftscangradle.flighttype";
    private oldEvent mOldEvent;
    private TextView mTitleText;
    private TextView mETAText;
    private TextView mTimeZoneText;
    private TextView mTimeText;
    private TextView mDateText;
    private TextView mTimeLabel;
    private TextView mDateLabel;
    private TextView mDescription;
    private Handler mHandler;
    private Runnable mUpdate;
    private String mTimeString;
    private TextView mMyFlight;

    public static EventFragment newInstance(UUID id) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT_ID, id);

        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID id = (UUID) getArguments().getSerializable(EVENT_ID);


        mOldEvent = oldEventsLab.get(getActivity()).getEvent(id);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    getActivity().onBackPressed();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_event, parent, false);


        mETAText = (TextView) v.findViewById(R.id.event_fragment_ETA);

        mHandler = new Handler();
        mUpdate = new Runnable() {
            @Override
            public void run() {
                String text = mOldEvent.getETA();
                mETAText.setText(text);
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mUpdate, 0);

        mTimeLabel = (TextView) v.findViewById(R.id.event_fragment_time_label);
        mDateLabel = (TextView) v.findViewById(R.id.event_fragment_date_label);

        mTimeZoneText = (TextView) v.findViewById(R.id.event_fragment_timezone);
        mTimeString = mOldEvent.getTimeZone();
        mTimeZoneText.setText( "(" + mTimeString + ")");

        mTimeText = (TextView) v.findViewById(R.id.time_text);
        mTimeText.setText("   " + mOldEvent.getLocalTime());

        mDateText = (TextView) v.findViewById(R.id.date_text);
        mDateText.setText(mOldEvent.getLocalDate());

        mMyFlight = (TextView) v.findViewById(R.id.event_fragment_flight);
        updateTextView();

        mDescription = (TextView) v.findViewById(R.id.fragment_event_description);
        mDescription.setText(mOldEvent.getNotes());




        return v;

    }

    private void updateTextView() {
        if (mOldEvent.getIsFlight()) {
            oldEventsLab lab = oldEventsLab.get(getActivity());

            if (lab.getFlightOne() == -1) { // flight status unknown, ask for search
                mMyFlight.setText(R.string.flight_status_unsure);
                mMyFlight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), StudentListActivity.class);
                        i.putExtra(EventFragment.EVENT_FLIGHT_TYPE, mOldEvent.getIsFlightOne());
                        startActivity(i);
                    }
                });
            }
            else if ( ((lab.getFlightOne() == 1) && (mOldEvent.getIsFlightOne())) || ((lab.getFlightOne() == 0) && (!mOldEvent.getIsFlightOne())) ) {  // if person is on flight one, and we are displaying fligh one
                mMyFlight.setText(R.string.flight_status_confirmed);
                mMyFlight.setBackgroundColor(Color.parseColor("#1EA71E"));
            }
            else if ( ((lab.getFlightOne() == 0) && (mOldEvent.getIsFlightOne())) || ((lab.getFlightOne() == 1) && (!mOldEvent.getIsFlightOne())) ) {
                mMyFlight.setText(R.string.not_your_flight);
                mMyFlight.setBackgroundColor(Color.parseColor("#ffb41f27"));
            }

        } else {
            mMyFlight.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mMyFlight.setOnClickListener(null);
        updateTextView();
    }
}
