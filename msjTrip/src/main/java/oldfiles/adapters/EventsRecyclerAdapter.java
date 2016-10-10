package oldfiles.adapters;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.msjBand.android.trip.R;
import oldfiles.pojo.Event;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "EventAdapter";
    private static final int ITEM_EVENT = 0;
    private static final int ITEM_HEADER = 1;

    private SortedList<Event> mEvents;

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitleTextView;
        private final TextView mTimeTextView;
        private final ImageView mImageView;


        public EventViewHolder(View v) {
            super(v);

            mTitleTextView = (TextView) v.findViewById(R.id.event_Title);
            mTimeTextView = (TextView) v.findViewById(R.id.event_item_time_date);
            mImageView = (ImageView) v.findViewById(R.id.event_image_view);
        }

        public TextView getTitleTextView() {
            return mTitleTextView;
        }

        public TextView getTimeTextView() {
            return mTimeTextView;
        }

        public ImageView getImageView() { return mImageView; }


    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView mDayOfWeek;


        public HeaderViewHolder(View v) {
            super(v);

            mDayOfWeek = (TextView) v.findViewById(R.id.event_header_day_of_week);
        }


        public TextView getDayOfWeek() {
            return mDayOfWeek;
        }



    }

    @Override
    public int getItemViewType(int position) {
        if (get(position).getId().equals("")) {
            return ITEM_HEADER;
        }
        else {
            return ITEM_EVENT;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {

        if (h instanceof EventViewHolder) {
            EventViewHolder holder = (EventViewHolder) h;
            holder.getTitleTextView().setText(
                    mEvents.
                            get(position)
                            .getTitle()
            );

            DateTimeZone dtz = DateTimeZone.forID(mEvents.get(position).getTimezone());
            DateTime dateTime = mEvents.get(position).getDate().toDateTime(dtz);

            DateTimeFormatter dtf = DateTimeFormat.forPattern("h:ma z");

            holder.getTimeTextView().setText(dtf.print(dateTime));
        } else {
            HeaderViewHolder holder = (HeaderViewHolder) h;
            Event e = mEvents.get(position);
            holder.getDayOfWeek().setText(e.getTitle());

        }


    }


    public EventsRecyclerAdapter() {
        mEvents = new SortedList<Event>(Event.class, new SortedList.Callback<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getDate().compareTo(o2.getDate());
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Event oldItem, Event newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areItemsTheSame(Event item1, Event item2) {
                if (!(item1.getId().equals("")) && !(item2.getId().equals("")))
                    return item1.getId().equals(item2.getId());
                else
                    return item1.getDesc().equals(item2.getDesc());
            }
        });
    }




    // region PageList Helpers
    public Event get(int position) {
        return mEvents.get(position);
    }

    public int add(Event item) {
        return mEvents.add(item);
    }

    public int indexOf(Event item) {
        return mEvents.indexOf(item);
    }

    public void updateItemAt(int index, Event item) {
        mEvents.updateItemAt(index, item);
    }

    public void set (List<Event> events) {
        if (events == null) {
            return;
        }
        ArrayList<String> dates = new ArrayList<>();

        for (int i = 0; i < events.size(); i++) {

            Event e = events.get(i);
            DateTimeZone dtz = DateTimeZone.forID(e.getTimezone());
            DateTime dateTime = e.getDate().toDateTime(dtz);

            DateTimeFormatter dtf = DateTimeFormat.forPattern("E MMM d");

            String s = dtf.print(dateTime);

            if (!dates.contains(s)) {
                dates.add(s);
                DateTime lastTime = e.getDate();

                Event n = new Event(
                        "",
                        s,
                        s,
                        "a",
                        new DateTime(
                                lastTime.getYear(),
                                lastTime.getMonthOfYear(),
                                lastTime.getDayOfMonth(),
                                0,
                                0,
                                0,
                                DateTimeZone.forID(e.getTimezone())),
                        e.getTimezone(),
                        -1
                );
                events.add(n);
            }

        }

        addAll(events);


        if (true) {
            ArrayList<String> Event_IDS = new ArrayList<>();
            for(Event e: events) {
                Event_IDS.add(e.getId());
            }

            for(int i = getItemCount() - 1; i >= 0; i--) {
                if(!(get(i).getId().equals("")) && !Event_IDS.contains(get(i).getId())) {
                    removeItemAt(i);
                }
                else if (get(i).getId().equals("")) {
                    Event f = get(i);
                    DateTimeZone dtz = DateTimeZone.forID(f.getTimezone());
                    DateTime dateTime = f.getDate().toDateTime(dtz);

                    DateTimeFormatter dtf = DateTimeFormat.forPattern("E MMM d");

                    String s = dtf.print(dateTime);
                    if (!dates.contains(s))
                        removeItemAt(i);
                }
            }
        }








    }


    public void addAll(List<Event> items) {
        mEvents.beginBatchedUpdates();
        for (Event item : items) {

            mEvents.add(item);
        }
        mEvents.endBatchedUpdates();
    }

    public void addAll(Event[] items) {
        addAll(Arrays.asList(items));
    }

    public boolean remove(Event item) {
        return mEvents.remove(item);
    }

    public Event removeItemAt(int index) {
        return mEvents.removeItemAt(index);
    }

    public void clear() {
        mEvents.beginBatchedUpdates();
        //remove items at end, to avoid unnecessary array shifting
        while (mEvents.size() > 0) {
            mEvents.removeItemAt(mEvents.size() - 1);
        }
        mEvents.endBatchedUpdates();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        if (viewType == ITEM_EVENT) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_event, viewGroup, false);
            return new EventViewHolder(v);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_header, viewGroup, false);
            return new HeaderViewHolder(v);
        }

    }

    @Override
    public int getItemCount() {
        return mEvents.size();
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