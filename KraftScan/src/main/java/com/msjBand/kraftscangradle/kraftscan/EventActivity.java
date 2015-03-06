package com.msjBand.kraftscangradle.kraftscan;

import android.support.v4.app.Fragment;


public class EventActivity extends DoubleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        int id = getIntent().getIntExtra(EventFragment.EVENT_ID,0);
        return EventFragment.newInstance(id);
    }

    @Override
    protected Fragment createFragment2() {
        int id = getIntent().getIntExtra(EventFragment.EVENT_ID, 0);
        return EventDescriptionFragment.newInstance(id);
    }
}
