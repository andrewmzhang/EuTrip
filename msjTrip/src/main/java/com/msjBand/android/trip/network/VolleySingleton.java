package com.msjBand.android.trip.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.msjBand.android.trip.extras.MyApplication;

public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;
    private VolleySingleton() {

        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getInstance() {
        if (sInstance == null) {

            sInstance = new VolleySingleton();
        }

        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
