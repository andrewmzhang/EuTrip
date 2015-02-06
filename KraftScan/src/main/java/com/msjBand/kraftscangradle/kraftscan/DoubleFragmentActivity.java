package com.msjBand.kraftscangradle.kraftscan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public abstract class DoubleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    protected abstract Fragment createFragment2();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment);

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