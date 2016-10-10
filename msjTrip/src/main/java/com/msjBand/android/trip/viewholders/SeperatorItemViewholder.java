package com.msjBand.android.trip.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.msjBand.android.trip.R;
import com.msjBand.android.trip.pojo.Event;

/**
 * Created by andrewmzhang on 10/7/16.
 */
public class SeperatorItemViewholder extends RecyclerView.ViewHolder {

    TextView mSeperatorDate;

    public SeperatorItemViewholder(View itemView) {

        super(itemView);
        mSeperatorDate = (TextView) itemView.findViewById(R.id.event_header_day_of_week);
    }

    public void bindSeperator(Event e) {
        mSeperatorDate.setText(e.getTitle());

    }
}
