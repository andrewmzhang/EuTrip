package oldfiles.json;

import oldfiles.pojo.Event;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by andrewmzhang on 4/4/2016.
 */
public class Parser {

    public static ArrayList<Event> parseEventsJSON(JSONArray response) {

        ArrayList<Event> events = null;

        if (response != null) {
            events = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                try {

                    JSONObject jsonObject = response.getJSONObject(i);

                    Event e = new Event(
                            jsonObject.getString("_id"),
                            jsonObject.getString("title"),
                            jsonObject.getString("desc"),
                            jsonObject.getString("author"),
                            jsonObject.getString("date"),
                            jsonObject.getString("timezone"),
                            jsonObject.getInt("groupRelevancy")
                    );

                    events.add(e);


                } catch (JSONException e) {
                    //Toast.makeText(MyApplication.getAppContext(), "Sync Failed, Please try again!!", Toast.LENGTH_LONG).show();

                }
            }
        }

        return events;
    }


}
