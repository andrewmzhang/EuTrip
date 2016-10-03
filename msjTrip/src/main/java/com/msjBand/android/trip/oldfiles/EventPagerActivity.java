package com.msjBand.android.trip.oldfiles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import com.msjBand.android.trip.R;

import java.util.ArrayList;
import java.util.UUID;

public class EventPagerActivity extends ActionBarActivity {

    private ViewPager mViewPager;
    private ArrayList<oldEvent> mOldEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);


        mOldEvents = oldEventsLab.get(this).getEvents();


        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                oldEvent oldEvent = mOldEvents.get(position);
                return EventFragment.newInstance(oldEvent.getId());
            }

            @Override
            public int getCount() {
                return mOldEvents.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                oldEvent oldEvent = mOldEvents.get(position);
                if (oldEvent.getTitle() != null) {
                    setTitle(oldEvent.getTitle());
                } else {
                    setTitle("Null");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID id = (UUID) getIntent().getSerializableExtra(EventFragment.EVENT_ID);
        for (int i = 0; i < mOldEvents.size(); i++) {
            if (mOldEvents.get(i).getId().equals(id)) {
                mViewPager.setCurrentItem(i);
                setTitle(mOldEvents.get(i).getTitle());
                break;
            }

        }


    }
}
