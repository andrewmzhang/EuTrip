package com.msjBand.android.trip;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ViewHolder> {

    private static final String TAG = "EventAdapter";

    private ArrayList<oldEvent> mOldEvents;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;

        public ViewHolder(View v) {
            super(v);

            mTextView = (TextView) v.findViewById(R.id.event_Title);
        }

        public TextView getTextView() {return mTextView;}
    }


    public EventsRecyclerAdapter(ArrayList<oldEvent> oldEvents) {
        mOldEvents = oldEvents;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_event, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mOldEvents.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(mOldEvents.get(position).getTitle());
    }

    /*
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
        Boolean isFlightOne = e.getIsFlightOne();
        Boolean isFlight = e.getIsFlight();
        Boolean isDepart = e.isDepart();

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);

        Drawable Na = getResources().getDrawable(R.drawable.x);
        ShapeDrawable shape = new ShapeDrawable(new OvalShape());

        int drawableId = e.getDrawableId();
        int colorID;
        colorID = getResources().getColor(R.color.blue);

        if (drawableId == R.drawable.airport)
            colorID = getResources().getColor(R.color.indigo);

        if (drawableId == R.drawable.note)
            colorID = getResources().getColor(R.color.green);

        if (drawableId == R.drawable.food)
            colorID = getResources().getColor(R.color.amber);

        if ((flightstatus == 1) && (isFlightOne) && (isFlight)) { // items is both flight 1 and person is flight one
            Drawable image = getResources().getDrawable(event.getDrawableId());
            imageView.setImageDrawable(image);
            shape.getPaint().setColor(getResources().getColor(R.color.green));
            imageView.setBackgroundDrawable(shape);
        } else if ((flightstatus == -1) && (isFlight)) { // items is a flight, but flight status unknown
            Drawable image = getResources().getDrawable(event.getDrawableId());
            imageView.setImageDrawable(image);
            shape.getPaint().setColor(getResources().getColor(R.color.cyan));
            imageView.setBackgroundDrawable(shape);
        } else if ((flightstatus == 1) && (!isFlightOne) && (isFlight)) { // items is not flight one, but person is flying one
            imageView.setImageDrawable(Na);
            shape.getPaint().setColor(getResources().getColor(R.color.red));
            imageView.setBackgroundDrawable(shape);
        } else if ((flightstatus == 0) && (isFlightOne) && (isFlight)) { // items is flight one, but person not flying flight one
            imageView.setImageDrawable(Na);
            shape.getPaint().setColor(getResources().getColor(R.color.red));
            imageView.setBackgroundDrawable(shape);
        } else if ((flightstatus == 0) && (!isFlightOne) && (isFlight)) { // items is not flight one, and person not flying flihg tone
            Drawable image = getResources().getDrawable(event.getDrawableId());
            imageView.setImageDrawable(image);
            shape.getPaint().setColor(getResources().getColor(R.color.green));
            imageView.setBackgroundDrawable(shape);
        } else {
            Drawable image = getResources().getDrawable(event.getDrawableId());
            imageView.setImageDrawable(image);
            shape.getPaint().setColor(colorID);
            imageView.setBackgroundDrawable(shape);
        }

        ImageView weekDay = (ImageView) convertView.findViewById(R.id.day_of_week);

        TextDrawable dayOfWeek = TextDrawable.builder().buildRound(event.getDayOfWeek(), retriveColor(event.getFullWeekDay()));
        weekDay.setImageDrawable(dayOfWeek);

        TextView titleTextView =
                (TextView) convertView.findViewById(R.id.event_Title);
        titleTextView.setText(e.getTitle());

        TextView timeDate =
                (TextView) convertView.findViewById(R.id.item_time_date);
        timeDate.setText(e.getListDate() + "\n" + e.getLocalTime());

        return convertView;


    }
    */
}