package com.msjBand.kraftscangradle.kraftscan;


import android.app.ListFragment;
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
import android.view.ViewGroup;
import android.widget.*;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class EventListActivity extends ActionBarActivity {

    public ArrayList<String> mSettings;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerRelativeLayout;
    public ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> adapter;
    public int lastPosition = 100;

    public class DrawerAdapter extends ArrayAdapter<String> {

        public DrawerAdapter(List<String> Strings) {

            super(getApplicationContext(), 0, Strings);


        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.settings_item, null);
            }
            EventsLab lab = EventsLab.get(getApplicationContext());

            TextView main;
            main = (TextView) convertView.findViewById(R.id.text_main);

            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_1);

            if(position == 0) {
                main.setText(lab.getStudentName());
                checkBox.setVisibility(View.GONE);
            }

            if(position == 1) {
                main.setText("Remove Irrelevant Flights");
                checkBox.setClickable(false);
                checkBox.setChecked(EventsLab.get(getApplicationContext()).isRemoveIrrelevant());
            }

            if (position == 2) {
                main.setText("Disable Notifications");
                checkBox.setClickable(false);
                checkBox.setChecked(EventsLab.get(getApplicationContext()).isDisableAllAlarms());

            }

            if (position == 3) {
                main.setText("AutoScroll");
                checkBox.setClickable(false);
                checkBox.setChecked(EventsLab.get(getApplicationContext()).isAutoScroll());

            }




            return convertView;



        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() < 1)
            lastPosition = 11;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_events_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);

        if (!EventsLab.get(getApplicationContext()).isDisableAllAlarms()) {
            NotifServicer.setServiceAlarm(getApplicationContext(), true);
            System.out.println("Systems True");


        } else {
            NotifServicer.setServiceAlarm(getApplicationContext(), false);
            System.out.println("Systems Out");
        }


        if (fragment == null) {
            fragment = new EventListFragment();
            fm.beginTransaction()
                    .add(R.id.content_frame, fragment)
                    .commit();

        }
        mSettings = new ArrayList<String>();
        mSettings.add("1");
        mSettings.add("2");
        mSettings.add("3");
        mSettings.add("4");


        adapter = new DrawerAdapter(mSettings);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.primary_dark));
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getSupportFragmentManager();
                int count = fm.getBackStackEntryCount();


                if ((position == 0) && ((position != lastPosition) || count < 1) ) {
                    if ( count >= 1)
                        fm.popBackStack();
                    Fragment fragment = new SetFlightFragment();
                    fm.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.content_frame, fragment).addToBackStack("detail").commit();
                    lastPosition = position;
                    mDrawerList.setItemChecked(position, true);
                    mDrawerLayout.closeDrawers();
                }


                if (position == 1) {
                    if(EventsLab.get(getApplicationContext()).getFlightOne() != -1) {
                        EventsLab.get(getApplicationContext()).setRemoveIrrelevant(!EventsLab.get(getApplicationContext()).isRemoveIrrelevant());
                        adapter.notifyDataSetChanged();
                        mDrawerList.setItemChecked(position, true);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Name Must Be Set!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                if (position == 2) {
                    EventsLab.get(getApplicationContext()).setDisableAllAlarms(!EventsLab.get(getApplicationContext()).isDisableAllAlarms());
                    mDrawerList.setItemChecked(position, true);
                    if (!EventsLab.get(getApplicationContext()).isDisableAllAlarms()) {
                        NotifServicer.setServiceAlarm(getApplicationContext(), true);
                        System.out.println("Systems Tru 2");

                    } else {
                        NotifServicer.setServiceAlarm(getApplicationContext(), false);
                        System.out.println("Systems Out 2");

                    }

                }

                if (position == 3) {
                    EventsLab.get(getApplicationContext()).setAutoScroll(!EventsLab.get(getApplicationContext()).isAutoScroll());
                    mDrawerList.setItemChecked(position, true);
                    if (EventsLab.get(getApplicationContext()).isAutoScroll()) {
                        Fragment listFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                        if (listFragment instanceof EventListFragment)
                            ((EventListFragment) listFragment).autoScroll();

                    } else {
                        EventsLab.get(getApplicationContext()).setLastAutoScrollId(0);
                    }

                }


                Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (fragment1 instanceof EventListFragment){
                    fragment1 = (EventListFragment) fragment1;
                    ((EventListFragment) fragment1).sendFilters();

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
