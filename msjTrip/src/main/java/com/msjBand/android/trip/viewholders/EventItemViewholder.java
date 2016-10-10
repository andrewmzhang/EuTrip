package com.msjBand.android.trip.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.msjBand.android.trip.R;
import com.msjBand.android.trip.pojo.Event;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by porter on 10/7/16.
 */
public class EventItemViewholder extends RecyclerView.ViewHolder {


    View view;
    CircleImageView image;
    TextView mTitle;
    TextView mDate;


    public EventItemViewholder(View itemView) {
        super(itemView);
        view = itemView;

        mTitle = (TextView) view.findViewById(R.id.event_Title);
        mDate = (TextView) view.findViewById(R.id.event_item_time_date);
        image = (CircleImageView) view.findViewById(R.id.event_image_view);

    }

    public void bindEvent(Event event) {

        mTitle.setText(event.getTitle());
        mDate.setText(event.getDesc());


    }


}
