package com.msjBand.android.trip;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EventDescriptionFragment extends Fragment {

    public static final String EVENT_ID =
            "com.msjBand.kraftscangradle.kraftscan.EventDescriptionFragment.EventID";
    private oldEvent mOldEvent;
    private TextView mDescription;


    public static EventDescriptionFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(EVENT_ID, id);

        EventDescriptionFragment fragment = new EventDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = getArguments().getInt(EVENT_ID);
        mOldEvent = oldEventsLab.get(getActivity()).getEvent(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_description, container, false);

        mDescription = (TextView) v.findViewById(R.id.fragment_event_description);
        mDescription.setText(mOldEvent.getNotes());

        return v;


    }


}
