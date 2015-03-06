package com.msjBand.kraftscangradle.kraftscan;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EventFragment extends Fragment {

    public static final String EVENT_ID =
            "com.msjBand.kraftscangradle.kraftscan";
    private Event mEvent;
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

        int id = getArguments().getInt(EVENT_ID);

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
                String text = mEvent.getETA();
                mETAText.setText(text);
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mUpdate, 0);

        mTimeLabel = (TextView) v.findViewById(R.id.event_fragment_time_label);
        mDateLabel = (TextView) v.findViewById(R.id.event_fragment_date_label);

        mTimeZoneText = (TextView) v.findViewById(R.id.event_fragment_timezone);
        mTimeString = mEvent.getTimeZone();
        mTimeZoneText.setText( "(" + mTimeString + ")");

        mTimeText = (TextView) v.findViewById(R.id.time_text);
        mTimeText.setText("   " + mEvent.getLocalTime());

        mDateText = (TextView) v.findViewById(R.id.date_text);
        mDateText.setText(mEvent.getLocalDate());

        mMyFlight = (TextView) v.findViewById(R.id.event_fragment_flight);
        if (mEvent.getIsFlight()) {
            EventsLab lab = EventsLab.get(getActivity());

            if (lab.getFlightOne() == -1) { // flight status unknown, ask for search
                mMyFlight.setText(R.string.flight_status_unsure);
                mMyFlight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity().getBaseContext(), "Hello",
                                Toast.LENGTH_SHORT).show();


                    }
                });
            }
            else if ( ((lab.getFlightOne() == 1) && (mEvent.getIsFlightOne())) || ((lab.getFlightOne() == 0) && (!mEvent.getIsFlightOne())) ) {  // if person is on flight one, and we are displaying fligh one
                mMyFlight.setText(R.string.flight_status_confirmed);
                mMyFlight.setBackgroundColor(Color.parseColor("#FF00FF06"));
            }
            else if ( ((lab.getFlightOne() == 0) && (mEvent.getIsFlightOne())) || ((lab.getFlightOne() == 1) && (!mEvent.getIsFlightOne())) ) {
                mMyFlight.setText(R.string.not_your_flight);
            }

        } else {
            mMyFlight.setVisibility(View.GONE);
        }

        mMyFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });


        return v;

    }
}
