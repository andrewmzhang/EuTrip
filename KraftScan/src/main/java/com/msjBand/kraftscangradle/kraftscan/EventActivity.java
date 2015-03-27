package com.msjBand.kraftscangradle.kraftscan;

import android.support.v4.app.Fragment;

import java.util.UUID;


public class EventActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(EventFragment.EVENT_ID);
        return EventFragment.newInstance(id);
    }


}
