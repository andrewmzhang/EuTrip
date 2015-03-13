package com.msjBand.kraftscangradle.kraftscan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class FlightListFragment extends Fragment {

    private static final String TAG = "Flight2283ListFragment";
    private ArrayList<String> mPeople;
    private ArrayAdapter<String> adapter;
    private EditText mSearchBar;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.student_list_searchable, container, false);
        mPeople = new ArrayList<String>();
        mPeople = populatePeople(mPeople);


        mSearchBar = (EditText) v.findViewById(R.id.student_search_bar);
        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != "")
                    adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mListView = (ListView) v.findViewById(R.id.student_list);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mPeople);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = adapter.getItem(position);
                EventsLab.get(getActivity()).setStudentName(name);
                Log.d(TAG, "Set name to" + name + " Status: " + EventsLab.get(getActivity()).getFlightOne());
                getActivity().finish();
            }
        });

        return v;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }





    private ArrayList<String> populatePeople(ArrayList<String> arrayList) {

        arrayList.add("Monica Kraft");
        arrayList.add("Wenhan Fang");
        arrayList.add("Sathvik Vivek");
        arrayList.add("Yu-Cheng Chou");
        arrayList.add("Yu-Ting Chou");
        arrayList.add("Jemmy Zhou");
        arrayList.add("Raymong Yin");
        arrayList.add("JianXiang Liu");
        arrayList.add("Nikhil Pathania");
        arrayList.add("Allison Xu");
        arrayList.add("Charles Xu");
        arrayList.add("Sharleen Zhou");
        arrayList.add("Chuck Goodman");
        arrayList.add("Anne Riley");
        arrayList.add("Roy Ho");
        arrayList.add("Kai Goodman");
        arrayList.add("Brandon Chen");
        arrayList.add("Stanley Zhang");
        arrayList.add("Tiernan Barrie");
        arrayList.add("Michelle Dalarossa");
        arrayList.add("Rushil Chakrabarty");
        arrayList.add("Helen Chang");
        arrayList.add("Sandra Kaye");
        arrayList.add("Allison Chan");
        arrayList.add("April Huang");
        arrayList.add("Alan Liu");
        arrayList.add("Akhil Reddy");
        arrayList.add("Hetav Gore");
        arrayList.add("Nishir Shelat");
        arrayList.add("William Ho");
        arrayList.add("Graciela Friend");
        arrayList.add("Samutratankul Mujarin");
        arrayList.add("Howard Mai");
        arrayList.add("Grant Fu");
        arrayList.add("Mackenzie Lim");
        arrayList.add("Sarah Chong");
        arrayList.add("Trevor Wu");
        arrayList.add("Sai Goli");
        arrayList.add("William Kim");
        arrayList.add("Nina Vasan");
        arrayList.add("Dorothy Du");
        arrayList.add("Yu Shu");
        arrayList.add("QiFei Teng");
        arrayList.add("Dorothy Griswold");
        arrayList.add("Andrew Zhang");
        arrayList.add("Waylon Peng");
        arrayList.add("Rahul Chatwani");
        arrayList.add("Nathon Ma");
        arrayList.add("Amanda Wang");
        arrayList.add("Tiancheng (TCJ) Cheng");
        arrayList.add("Kevin Zhu");
        arrayList.add("Kevin Yu");
        arrayList.add("Albert Stanley");
        arrayList.add("NingNing Zhou");
        arrayList.add("Janet Baker");
        arrayList.add("Sean Li");
        arrayList.add("Dylan Lim");
        arrayList.add("Jelena Lee");
        arrayList.add("Alison Chen");
        arrayList.add("Shagun Srivastava");
        arrayList.add("Claire Yung");
        arrayList.add("Joshua Zeng");
        arrayList.add("Anthony Herget");
        arrayList.add("Ramona Gonzalez");
        arrayList.add("Marcos Jung");
        arrayList.add("Roselyn Jung");
        arrayList.add("Jean Jea");
        arrayList.add("Emily Xu");
        arrayList.add("Angela Yang");
        arrayList.add("Mallika Chatterjee");
        arrayList.add("Tanushri Sundar");
        arrayList.add("Kevin Zhang");
        arrayList.add("Irene Yin");
        arrayList.add("Vivasvan Vykunta");
        arrayList.add("Zachary Kekoa");
        arrayList.add("Alexia Kekoa");
        arrayList.add("Herkea Jea");
        arrayList.add("Samuel Chou");
        arrayList.add("Aneri Parikh");
        arrayList.add("Seona Patel");
        arrayList.add("Kenneth Leung");
        arrayList.add("Alex Yin");
        arrayList.add("Rohan Nair");
        arrayList.add("Shiva Ramani");
        arrayList.add("Nicole Hsu");
        arrayList.add("Derek Xia");
        arrayList.add("Akshita Gandra");
        arrayList.add("Teresa Hsu");
        Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);


        return arrayList;


    }
}
