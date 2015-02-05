package com.msjBand.kraftscangradle.kraftscan;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.logging.LogRecord;

public class EventFragment extends Fragment {

    private Event mEvent;
    private TextView mTitleText;
    private TextView mETAText;
    private TextView mTimeZoneText;
    private TextView mTimeText;
    private TextView mDateText;


    private Handler mHandler;
    private Runnable mUpdate;
    private String mTimeString;

    public static final String EVENT_ID =
            "com.msjBand.kraftscangradle.kraftscan";


    public static EventFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(EVENT_ID, id);

        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = (int) getArguments().getInt(EVENT_ID);

        mEvent = EventsLab.get(getActivity()).getEvent(id);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_event, parent, false);

        mTitleText = (TextView) v.findViewById(R.id.event_fragment_title);
        mTitleText.setText(mEvent.getTitle());


        mETAText = (TextView) v.findViewById(R.id.event_fragment_ETA);

        mHandler = new Handler();
        mUpdate = new Runnable() {
            @Override
            public void run() {
                mETAText.setText(mEvent.getETA());
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mUpdate, 0);

        mTimeZoneText = (TextView) v.findViewById(R.id.event_fragment_timezone);
        mTimeString = mEvent.getTimeZone();
        mTimeZoneText.setText( "(" + mTimeString + ")");

        mTimeText = (TextView) v.findViewById(R.id.time_text);
        mTimeText.setText(mEvent.getLocal());



        return v;

    }
}
