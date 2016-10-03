package com.msjBand.android.trip.oldfiles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import com.msjBand.android.trip.R;


public abstract class DoubleFragmentActivity extends ActionBarActivity {

    protected abstract Fragment createFragment();

    protected abstract Fragment createFragment2();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        Fragment fragment2 = fm.findFragmentById(R.id.fragmentContainer2);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();

        }

        if (fragment2 == null) {
            fragment2 = createFragment2();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer2, fragment2)
                    .commit();

        }

    }
}