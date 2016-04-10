package com.msjBand.android.trip.json;

import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.msjBand.android.trip.extras.MyApplication;
import com.msjBand.android.trip.logging.L;
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
                Log.e("Requestor", e + "");
            } catch (ExecutionException e) {
                Log.e("Requestor", e + "");
            } catch (TimeoutException e) {
                Log.e("Requestor", e + "");
            }
            return response;
        }


}
