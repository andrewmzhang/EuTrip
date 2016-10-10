package oldfiles;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;

public class EventsIntentJSONSerializer {

    private Context mContext;
    private String mFilename;

    public EventsIntentJSONSerializer(Context c, String f) {

        mContext = c;
        mFilename = f;

    }

    public void loadCrimes(oldEventsLab oldEventsLab) throws IOException, JSONException {
        ArrayList<oldEventsLab> crimes = new ArrayList<oldEventsLab>();
        BufferedReader reader = null;
        try {
            // Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            // Parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // Build the array of crimes from JSONObjects

            oldEventsLab.setOptions(array.getJSONObject(0));
        } catch (FileNotFoundException e) {
            // Ignore
        } finally {
            if (reader != null)
                reader.close();
        }

    }


    public void saveCrimes(oldEventsLab oldEventsLab) throws JSONException, IOException {

        // Build an array in JSON
        JSONArray array = new JSONArray();

        array.put(oldEventsLab.toJSON());
        // Write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }


    }


}
