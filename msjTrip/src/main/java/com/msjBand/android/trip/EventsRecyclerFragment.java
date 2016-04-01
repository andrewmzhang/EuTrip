package com.msjBand.android.trip;


import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.gcm.PeriodicTask;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class EventsRecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "EventsRecyclerFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    private SwipeRefreshLayout refreshLayout;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private ArrayList<String> ids;

    private EventsRecyclerAdapter adapter;
    private String previousName = "";

    private Handler mHandler;
    private Runnable mUpdate;
    private boolean previousState;

    private int Monday;
    private int Tuesday;
    private int Wednesday;
    private int Thursday;
    private int Friday;
    private int Saturday;
    private int Sunday;

    private int index;
    private int top;
    private ArrayList<Event> mEvents;

    @Override
    public void onRefresh() {
        updateEvents();
    }


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }



    private void setRandomColours(int mon, int tues, int wed, int thurs, int fri, int sat, int sun) {
        ArrayList<Integer> colors = new ArrayList<Integer>();
        Resources res = getResources();
        colors.add(res.getColor(R.color.deepOrange));
        colors.add(res.getColor(R.color.orange));
        colors.add(res.getColor(R.color.lightGreen));
        colors.add(res.getColor(R.color.pink));
        colors.add(res.getColor(R.color.teal));
        colors.add(res.getColor(R.color.blueGrey));
        colors.add(res.getColor(R.color.deepPurple));
        Collections.shuffle(colors);
        Monday = colors.get(0);
        Tuesday = colors.get(1);
        Wednesday = colors.get(2);
        Thursday = colors.get(3);
        Friday = colors.get(4);
        Saturday = colors.get(5);
        Sunday = colors.get(6);

    }

    private int retriveColor(String name) {
        if(name.equals("Monday"))
            return Monday;
        else if (name.equals("Tuesday"))
            return Tuesday;
        else if (name.equals("Wednesday"))
            return Wednesday;
        else if (name.equals("Thursday"))
            return Thursday;
        else if (name.equals("Friday"))
            return Friday;
        else if (name.equals("Saturday"))
            return Saturday;
        else if (name.equals("Sunday"))
            return Sunday;
        else
            return Color.TRANSPARENT;
    }







    /*
            //getListView().setDivider(null);

        previousState = EventsLab.get(getActivity()).isRemoveIrrelevant();



        adapter = new EventAdapter(mEvents);
     */


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events_recycler, container, false);
        root.setTag(TAG);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeEventsLists);
        refreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        RecyclerView.ItemAnimator animator = new OvershootInRightAnimator();
        animator.setRemoveDuration(500);
        animator.setAddDuration(1000);

        mRecyclerView.setItemAnimator(animator);



        adapter = new EventsRecyclerAdapter();

        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setAdapter(adapter);



        return root;

    }

    private void updateEvents() {
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        ids = new ArrayList<String>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, MyApplication.EventsURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getActivity(), "Successful Update", Toast.LENGTH_SHORT).show();
                ids.clear();
                for(int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        Event e = new Event(
                                jsonObject.getString("_id"),
                                jsonObject.getString("title"),
                                jsonObject.getString("desc"),
                                jsonObject.getString("author"),
                                jsonObject.getString("date"),
                                jsonObject.getString("timezone"),
                                jsonObject.getInt("groupRelevancy")
                        );
                        ids.add(jsonObject.getString("_id"));
                        adapter.add(e);


                    } catch (JSONException e) {
                        Toast.makeText(MyApplication.getAppContext(), "Sync Failed, Please try again!!", Toast.LENGTH_LONG).show();

                    }
                }

                if (response.length() < adapter.getItemCount()) {

                    // Note: Items must be deleted from back to front, or else we will have issues when deleting more
                    // than one item.
                    for(int i = adapter.getItemCount() - 1; i >= 0; i--) {
                        if(!ids.contains(adapter.get(i).getId())) {
                            adapter.removeItemAt(i);
                        }
                    }



                }

                if(refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                Log.e("Events Recycler", error.toString());

                if(refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }
            }
        });

        requestQueue.add(request);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getActivity().setTitle(R.string.event_title);


        setRandomColours(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday);

        updateEvents();


        setRetainInstance(true);



    }



}
