package com.msjBand.kraftscangradle.kraftscan;


import android.support.v4.app.Fragment;

public class EventListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EventListFragment();
    }
}
