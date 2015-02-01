package com.msjBand.kraftscangradle.kraftscan;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class EventActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        int id = getIntent().getIntExtra(EventFragment.EVENT_ID,0);



        return new EventFragment.newInstanse(id);
    }
}
