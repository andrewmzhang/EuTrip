package com.msjBand.kraftscangradle.kraftscan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

public class EventPagerActivity extends ActionBarActivity {

    private ViewPager mViewPager;
    private ArrayList<Event> mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mEvents = EventsLab.get(this).getEvents();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return EventFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return mEvents.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Event event = mEvents.get(position);
                if (event.getTitle() != null) {
                    setTitle(event.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        int id = getIntent().getIntExtra(EventFragment.EVENT_ID,0);
        System.out.println(id);
        mViewPager.setCurrentItem(id);


    }
}
