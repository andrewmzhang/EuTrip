package com.msjBand.android.trip.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.msjBand.android.trip.pojo.Event;
import com.msjBand.android.trip.viewholders.EventItemViewholder;
import com.msjBand.android.trip.viewholders.SeperatorItemViewholder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by andrewmzhang on 10/4/16.
 */
public class EventsAdapter extends FirebaseRecyclerAdapter<Event, RecyclerView.ViewHolder> {

    private int seperatorLayout;


    public EventsAdapter(Class<Event> modelClass, int modelLayout, int seperatorLayout, Class<RecyclerView.ViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.seperatorLayout = seperatorLayout;
    }


    @Override
    public int getItemViewType(int position) {
        Event event = getItem(position);
        if (event.isSeperator())
            return seperatorLayout;
        else
            return mModelLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        try {
            if (viewType == mModelLayout) {
                Constructor<EventItemViewholder> constructor = (EventItemViewholder.class).getConstructor(View.class);
                return constructor.newInstance(view);
            } else {
                Constructor<SeperatorItemViewholder> constructor = (SeperatorItemViewholder.class).getConstructor(View.class);
                return constructor.newInstance(view);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, Event model, int position) {


        if (!model.isSeperator()) {
            EventItemViewholder viewholder = (EventItemViewholder) viewHolder;
            viewholder.bindEvent(model);
        } else {

            SeperatorItemViewholder viewholder = (SeperatorItemViewholder) viewHolder;
            viewholder.bindSeperator(model);


        }


    }
}
