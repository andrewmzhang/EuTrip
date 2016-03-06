package com.msjBand.android.trip;


import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

public class StudentListFragment extends ListFragment {

    ArrayList<String> mStudents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStudents = StudentsLab.get(getActivity().getApplicationContext()).getmStudents();


    }
}
