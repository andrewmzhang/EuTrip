package oldfiles.json;

import org.json.JSONObject;

/**
 * Created by Archimedes on 4/4/2016.
 */
public class Utils {

    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }

}
