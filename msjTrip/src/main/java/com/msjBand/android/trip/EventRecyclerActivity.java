package com.msjBand.android.trip;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

//import com.google.android.gms.gcm.GcmNetworkManager;
//import com.google.android.gms.gcm.PeriodicTask;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import services.UpdateEventsService;

//import services.MyService;

public class EventRecyclerActivity extends AppCompatActivity  {

    public static final String TAG = "EventRecyclerActivity";
    private static final String JOB_TAG = "MyServices";

    private GcmNetworkManager mGcmNetworkManager;
    private Handler mHandler;


    private void constructJob() {


        PeriodicTask task = new PeriodicTask.Builder()
                .setService(UpdateEventsService.class)
                .setPersisted(true)
                .setTag(JOB_TAG)
                .setPeriod(10)
                .setFlex(5)
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                .setRequiresCharging(false)
                .build();

        mGcmNetworkManager.schedule(task);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_recycler_list);





        mGcmNetworkManager = GcmNetworkManager.getInstance(this);
        constructJob();


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            EventsRecyclerFragment fragment = new EventsRecyclerFragment();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }



    }






}
