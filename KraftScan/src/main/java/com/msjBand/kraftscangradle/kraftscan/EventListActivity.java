package com.msjBand.kraftscangradle.kraftscan;


import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class EventListActivity extends ActionBarActivity {

    public ArrayList<String> mSettings;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerRelativeLayout;
    public ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_events_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);

        if (fragment == null) {
            fragment = new EventListFragment();
            fm.beginTransaction()
                    .add(R.id.content_frame, fragment)
                    .commit();

        }
        mSettings = new ArrayList<String>();
        if (EventsLab.get(getApplicationContext()).getStudentName() == null)
            mSettings.add("Set Name");
        else
            mSettings.add(EventsLab.get(getApplicationContext()).getStudentName());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                mSettings);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.primary_dark));
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getSupportFragmentManager();


                if ((position == 0) && fm.getBackStackEntryCount() < 1) {
                    Fragment fragment = new SetFlightFragment();
                    fm = getSupportFragmentManager();
                    fm.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.content_frame, fragment).addToBackStack("detail").commit();
                    mDrawerList.setItemChecked(position, true);
                    mDrawerLayout.closeDrawers();
                }

                if ((position == 0) && fm.getBackStackEntryCount() >= 1) {
                    fm.popBackStack();
                    Fragment fragment = new SetFlightFragment();
                    fm = getSupportFragmentManager();
                    fm.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.content_frame, fragment).addToBackStack("detail").commit();
                    mDrawerList.setItemChecked(position, true);
                    mDrawerLayout.closeDrawers();
                }



            }
        });


        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
                ) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (EventsLab.get(getApplicationContext()).getStudentName() == null)
                    mSettings.set(0, "Set Name");
                else
                    mSettings.set(0, EventsLab.get(getApplicationContext()).getStudentName());
                adapter.notifyDataSetChanged();
                super.onDrawerSlide(drawerView, slideOffset);
            }

            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);

            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }


        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


}
