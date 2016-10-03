package com.msjBand.android.trip.oldfiles.json;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import org.json.JSONArray;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Archimedes on 4/4/2016.
 */
public class Requester {
        public static JSONArray requestMasterEventsJSON(RequestQueue requestQueue, String url) {
            JSONArray response = null;
            RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();

            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    requestFuture,
                    requestFuture);

            requestQueue.add(request);


            try {
                response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.e("MyService", e + "");
            } catch (ExecutionException e) {
                Log.e("MyService", e + "");
            } catch (TimeoutException e) {
                Log.e("MyService", e + "");
            }
            return response;
        }


}
