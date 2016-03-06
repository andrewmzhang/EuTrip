package com.msjBand.android.trip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewAnimator;

public class EventRecyclerActivity extends AppCompatActivity {

    public static final String TAG = "EventRecyclerActivity";


    private boolean mLogShown;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_list_activity);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            EventRecyclerFragment fragment = new EventRecyclerFragment();

            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();

        }

    }





}
