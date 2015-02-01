package com.msjBand.kraftscangradle.kraftscan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.w3c.dom.Text;

public class EventFragment extends Fragment {

    Event mEvent;
    TextView mTitleText;

    public static final String EVENT_ID =
            "com.msjBand.kraftscangradle.kraftscan";


    public static EventFragment newInstanse(int id) {

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

        return v;

    }
}
