package com.msjBand.android.trip;


import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;




public class oldEventsLab {

    // Settings Data
    private boolean removeIrrelevant = false;
    private String studentName = "";
    private int mFlightOne = -1;   //-1 is unknown, 0 is not on Flight One (2283), 1 is on Flight One
    private boolean mAutoScroll = false;
    private int mLastAutoScrollId = 0;




    private UUID mIntentUUID;

    private static oldEventsLab sOldEventsLab;
    private Context mAppContext;
    private final static String sParisZone= "Europe/Paris";

    private ArrayList<oldEvent> mMasterOldEvents;

    private Map<String, oldEvent> mEvents;


    private String mName = "Search Flight Roster";
    private static final String FILENAME = "eventData.json";

    private static final String TAG = "EventsLab";


    public ArrayList<oldEvent> getMasterOldEvents() {
        return mMasterOldEvents;
    }

    private EventsIntentJSONSerializer mSerializer;

    public void setMasterOldEvents(ArrayList<oldEvent> masterOldEvents) {
        mMasterOldEvents = masterOldEvents;
    }

    private static final String JSON_Remove_Relevant = "id";
    private static final String JSON_StudentName = "title";
    private static final String JSON_mFlightOne = "solved";
    private static final String JSON_AutoScroll = "set";
    private static final String JSON_lastScrollId = "scrollid";

    public JSONObject toJSON() throws JSONException {


        JSONObject json = new JSONObject();
        json.put(JSON_Remove_Relevant, removeIrrelevant);
        json.put(JSON_StudentName, studentName);
        json.put(JSON_mFlightOne, mFlightOne);
        json.put(JSON_AutoScroll, mAutoScroll);
        json.put(JSON_lastScrollId, mLastAutoScrollId);

        return json;

    }

    public UUID getIntentUUID() {
        return mIntentUUID;
    }

    public void setIntentUUID(UUID intentUUID) {
        mIntentUUID = intentUUID;
    }

    public int getLastAutoScrollId() {
        return mLastAutoScrollId;
    }

    public void setLastAutoScrollId(int lastAutoScrollId) {
        mLastAutoScrollId = lastAutoScrollId;
    }


    private oldEventsLab(Context appContext) {
        mSerializer = new EventsIntentJSONSerializer(appContext, FILENAME);

        mAppContext = appContext;



        mMasterOldEvents = new ArrayList<oldEvent>();
        mMasterOldEvents = getNormalEvents();


    }

    public int getCurrentCrimePosition(ArrayList<oldEvent> eventsList) {

        for (int i = 0; i < eventsList.size(); i++) {
            if (eventsList.get(i).determineIsOccured() == false) {
                Log.d(TAG, "Event Position" + i);
                if (mLastAutoScrollId != i) {
                    mLastAutoScrollId = i;
                    return i;

                } else {
                    break;
                }

            }

        }

        return -1;


    }

    public boolean saveCrimes(oldEventsLab oldEventsLab) {
        try {
            mSerializer.saveCrimes(oldEventsLab);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes", e);
            return false;
        }

    }

    public void setOptions(JSONObject jsonObject) throws JSONException {
        removeIrrelevant = jsonObject.getBoolean(JSON_Remove_Relevant);
        this.setStudentName(jsonObject.getString(JSON_StudentName));
        this.setTrueFalse();
        mFlightOne = jsonObject.getInt(JSON_mFlightOne);
        mAutoScroll = jsonObject.getBoolean(JSON_AutoScroll);
        mLastAutoScrollId = jsonObject.getInt(JSON_lastScrollId);


    }

    public boolean isAutoScroll() {
        return mAutoScroll;
    }

    public void setAutoScroll(boolean autoScroll) {
        mAutoScroll = autoScroll;
    }

    private String Flight2283Roster = "Monica Kraft\nWenhan Fang\nSathvik Vivek\nYu-Cheng Chou\nYu-Ting Chou\nJemmy Zhou\nRaymong Yin\nJianXiang Liu\nNikhil Pathania\nAllison Xu\nCharles Xu\nSharleen Zhou" +
            "\n\nChuck Goodman\nAnne Riley\nRoy Ho\nKai Goodman\nBrandon Chen\nStanley Zhang\nTiernan Barrie\nMichelle Dalarossa\nRushil Chakrabarty\nHelen Chang" +
            "\n\nSandra Kaye\nAllison Chan\nApril Huang\nAlan Liu\nAkhil Reddy\nHetav Gore\nNishir Shelat\nWilliam Ho\nGraciela Friend\nSamutratankul Mujarin" +
            "\n\nHoward Mai\nGrant Fu\nMackenzie Lim\nSarah Chong\nTrevor Wu\nSai Goli\nWilliam Kim\nNina Vasan\nDorothy Du\nYu Shu\nQiFei Teng" +
            "\n\nDorothy Griswold\nAndrew Zhang\nWaylon Peng\nRahul Chatwani\nNathon Ma\nAmanda Wang\n\"TCJ Cheng\"\nKevin Zhu\nKevin Yu\nAlbert Stanley\nNingNing Zhou" +
            "\n\nJanet Baker\nSean Li\nDylan Lim\nJelena Lee\nAlison Chen\nShagun Srivastava\nClaire Yung\nJoshua Zeng\nAnthony Herget\nRamona Gonzalez\nMarcos Jung\nRoselyn Jung" +
            "\n\nJean Jea\nEmily Xu\nAngela Yang\nMallika Chatterjee\nTanushri Sundar\nKevin Zhang\nIrene Yin\nVivasvan Vykunta\nZachary Kekoa\nAlexia Kekoa\nHerkea Jea" +
            "\n\nSamuel Chou\nAneri Parikh\nSeona Patel\nKenneth Leung\nAlex Yin\nRohan Nair\nShiva Ramani\nNicole Hsu\nDerek Xia\nAkshita Gandra\nTeresa Hsu";

    private String Flight2411Roster = "\tMark Aherns\n\tEvan Lwin\n\tJoyce Wang\n\tJulie ChenYi Wang\n\tAimee Xu\n\tEmma Chang\n\tClaire Wu\n\tApoorva Prakash\n\tKosh Kumar\n\tHsien-Hsiang Lin\n" +
            "\n\tHarrison Cheng\n\tKelly Shi\n\tSavana Wang\n\tJustin Nguyen\n\tFendy Gao\n\tSerena Young\n\tMarianne Rara" +
            "\n\n\tAnn Kwan\n\tJonathan Bright Li\n\tStephanie Bi\n\tAshley Chen\n\tJennifer Wei\n\tNikhita Ganesh\n\tMaya Sudarsan\n\tAaron Zhang\n\tJerry Lin\n\tLi Diao" +
            "\n\n\tClaudia Fort\n\tAnna Pi\n\tVivian Ross\n\tLeon Ming\n\tVarsha Rajagopalan\n\tWilliam Zeng\n\tDonna li\n\tVineeth Yeevani\n\tKiran Raja\n\tRaja Jayakumar" +
            "\n\n\tMargaret Taylor\n\tNivedha Karthikeyan\n\tKate Lin\n\tAnjali Joseph\nJolene Tsai\n\tSabrina Liu\n\tJoyce Pi\n\tAn Tran\n\tKhang Tran\n\tThach Ngo Tran" +
            "\n\n\tLesley Wilhite\n\tKunal Agarwal\n\tCory Lam\n\tColby Huang\n\tBrian Zhao\n\tAlexander Chen\n\tSai Dwibhashyam\n\tAshank Verma\n\tVictor He\n\tLi Deng" +
            "\n\n\tYue Zhao\n\tSara Tsai\n\tJessica Eng\n\tDouglas Lam\n\tAngus Fung\n\tWilliam Luk\n\tKathleen Zhou\n\tBrandon Lu\n\tDiana Minyi Lu" +
            "\n\n\tRaymond Mendonca\n\tJessica Mao\n\tDelaine Rogers\n\tAnnie Xu\n\tLyann Choi\n\tRushalee Nirodi\n\tMyra Awan\n\tDanice Long\n\tDiana Gia Tran";





    public ArrayList<oldEvent> getNormalEvents() {

        ArrayList<oldEvent> mOldEvents = new ArrayList<oldEvent>();

        oldEvent e = new oldEvent(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 4, 45, 0);
        e.setTitle("Airport SFO 2411");
        e.setNotes("\t Arrive at SFO American Airlines Domestic Departures Terminal 2");
        e.setDrawableId(R.drawable.airport);
        e.setIsFlightOne(false);
        e.setIsFlight(true);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 5, 15, 0);
        e.setTitle("Airport SFO 2283");
        e.setNotes("\t Arrive at SFO American Airlines Domestic Departures Terminal 2");
        e.setDrawableId(R.drawable.airport);
        e.setIsFlightOne(true);
        e.setIsFlight(true);
        mOldEvents.add(e);

        // 2411 -Harrison
        e = new oldEvent(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 7, 45, 0);
        e.setTitle("Flight 2411 Departure");
        e.setNotes("\tSan Fransisco Airport to Chicago O'Hare Airport. Remember to transfer to flight 042 at 3:10 pm local time. \n" +
                        "\n" +
                        "Contact: Harrison Cheng\n" +
                        "1-(510)-579-8858\n\nFlight 2411 Roster: \n"
                        + "\tMark Aherns\n\tEvan Lwin\n\tJoyce Wang\n\tJulie ChenYi Wang\n\tAimee Xu\n\tEmma Chang\n\tClaire Wu\n\tApoorva Prakash\n\tKosh Kumar\n\tHsien-Hsiang Lin\n" +
                        "\n\tHarrison Cheng\n\tKelly Shi\n\tSavana Wang\n\tJustin Nguyen\n\tFendy Gao\n\tSerena Young\n\tMarianne Rara" +
                        "\n\n\tAnn Kwan\n\tJonathan Bright Li\n\tStephanie Bi\n\tAshley Chen\n\tJennifer Wei\n\tNikhita Ganesh\n\tMaya Sudarsan\n\tAaron Zhang\n\tJerry Lin\n\tLi Diao" +
                        "\n\n\tClaudia Fort\n\tAnna Pi\n\tVivian Ross\n\tLeon Ming\n\tVarsha Rajagopalan\n\tWilliam Zeng\n\tDonna li\n\tVineeth Yeevani\n\tKiran Raja\n\tRaja Jayakumar" +
                        "\n\n\tMargaret Taylor\n\tNivedha Karthikeyan\n\tKate Lin\n\tAnjali Joseph (Mitter)\nJolene Tsai\n\tSabrina Liu\n\tJoyce Pi\n\tAn Tran\n\tKhang Tran\n\tThach Ngo Tran" +
                        "\n\n\tLesley Wilhite\n\tKunal Agarwal\n\tCory Lam\n\tColby Huang\n\tBrian Zhao\n\tAlexander Chen\n\tSai Dwibhashyam\n\tAshank Verma\n\tVictor He\n\tLi Deng" +
                        "\n\n\tYue Zhao\n\tSara Tsai\n\tJessica Eng\n\tDouglas Lam\n\tAngus Fung\n\tWilliam Luk\n\tKathleen Zhou\n\tBrandon Lu\n\tDiana Minyi Lu" +
                        "\n\n\tRaymond Mendonca\n\tJessica Mao\n\tDelaine Rogers\n\tAnnie Xu\n\tLyann Choi\n\tRushalee Nirodi\n\tMyra Awan\n\tDanice Long\n\tDiana Gia Tran"

        );
        e.setIsFlight(true);
        e.setIsFlightOne(false);
        e.setDepart(true);
        e.setDrawableId(R.drawable.plane_takeoff);
        mOldEvents.add(e);

        // Departure of Flight 2283 (one)
        e =  new oldEvent(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 8, 35, 0);
        e.setTitle("Flight 2283 Departure");
        e.setNotes("\tSan Fransisco Airport to Dallas Forth Worth International Airport. Remember to transfer to flight 048 at 3:05 pm local time.\n" +
                "\n" +
                "Contact: Anne Riley 1-(510)-569-7064 \n\nFlight 2283 Roster:\n"
                + "\tMonica Kraft\n\tWenhan Fang\n\tSathvik Vivek\n\tYu-Cheng Chou\n\tYu-Ting Chou\n\tJemmy Zhou\n\tRaymong Yin\n\tJianXiang Liu\n\tNikhil Pathania\n\tAllison Xu\n\tCharles Xu\n\tSharleen Zhou" +
                "\n\n\tChuck Goodman\n\tAnne Riley\n\tRoy Ho\n\tKai Goodman\n\tBrandon Chen\n\tStanley Zhang\n\tTiernan Barrie\n\tMichelle Dalarossa\n\tRushil Chakrabarty\n\tHelen Chang" +
                "\n\n\tSandra Kaye\n\tAllison Chan\n\tApril Huang\n\tAlan Liu\n\tAkhil Reddy\n\tHetav Gore\n\tNishir Shelat\n\tWilliam Ho\n\tGraciela Friend\n\tSamutratankul Mujarin" +
                "\n\n\tHoward Mai\n\tGrant Fu\n\tMackenzie Lim\n\tSarah Chong\n\tTrevor Wu\n\tSai Goli\n\tWilliam Kim\n\tNina Vasan\n\tDorothy Du\n\tYu Shu\n\tQiFei Teng" +
                "\n\n\tDorothy Griswold\n\tAndrew Zhang\n\tWaylon Peng\n\tRahul Chatwani\n\tNathon Ma\n\tAmanda Wang\n\tTCJ Cheng\n\tKevin Zhu\n\tKevin Yu\n\tAlbert Stanley\n\tNingNing Zhou" +
                "\n\n\tJanet Baker\n\tSean Li\n\tDylan Lim\n\tJelena Lee\n\tAlison Chen\n\tShagun Srivastava\n\tClaire Yung\n\tJoshua Zeng\n\tAnthony Herget\n\tRamona Gonzalez\n\tMarcos Jung\n\tRoselyn Jung" +
                "\n\n\tJean Jea\n\tEmily Xu\n\tAngela Yang\n\tMallika Chatterjee\n\tTanushri Sundar\n\tKevin Zhang\n\tIrene Yin\n\tVivasvan Vykunta\n\tZachary Kekoa\n\tAlexia Kekoa\n\tHerkea Jea" +
                "\n\n\tSamuel Chou\n\tAneri Parikh\n\tSeona Patel\n\tKenneth Leung\n\tAlex Yin\n\tRohan Nair\n\tShiva Ramani\n\tNicole Hsu\n\tDerek Xia\n\tAkshita Gandra\n\tTeresa Hsu");
        e.setIsFlightOne(true);
        e.setIsFlight(true);
        e.setDepart(true);
        e.setDrawableId(R.drawable.plane_takeoff);
        mOldEvents.add(e);


        // Touchdown of Flight 2283 (one)
        e = new oldEvent(TimeZone.getTimeZone("America/Chicago"), 2015, 3, 30, 14, 05, 0);
        e.setTitle("Flight 2283 Arrival");
        e.setNotes("Flight Duration: 3h 30min \n\n\tTouchdown at Dallas Forth Worth Airport. According to Google reviews, this place has really fast internet speeds...\n\n\tDon't forget to transfer flights in 1 hour (15:05)\n" +
                "\n" +
                "Contact: Anne Riley 1-(510)-569-7064");
        e.setIsFlight(true);
        e.setDepart(false);
        e.setIsFlightOne(true);
        e.setDrawableId(R.drawable.plane_landing);
        mOldEvents.add(e);

        // Touchdown of Flight 2411
        e = new oldEvent((TimeZone.getTimeZone("America/Chicago")), 2015, 3, 30, 14, 07, 0);
        e.setTitle("Flight 2411 Arrival");
        e.setNotes("Flight Duration: 4h 22min \n\n\tTouchdown at Chicago O'Hare Airport. According to Google reviews, this airport_level is notorious for delaying flights, and rates a 3.6/5... \n\n\t Don't forget" +
                " to transfer flights in 1 hour (15:10)\n" +
                "\n" +
                "Contact: Harrison Cheng\n" +
                "1-(510)-579-8858");
        e.setIsFlight(true);
        e.setIsFlightOne(false);
        e.setDepart(false);
        e.setDrawableId(R.drawable.plane_landing);
        mOldEvents.add(e);

        // Departure of flight 048 (one)
        e = new oldEvent((TimeZone.getTimeZone("America/Chicago")), 2015, 3, 30, 15, 05, 0);
        e.setTitle("Flight 48 Transfer");
        e.setNotes("Dallas Fort Worth to Aeroports de Paris. \n" +
                "\n" +
                "Contact: Anne Riley 1-(510)-569-7064" +
                "\n\nFlight 48 Roster:\n"
                + "\n\tMonica Kraft\n\tWenhan Fang\n\tSathvik Vivek\n\tYu-Cheng Chou\n\tYu-Ting Chou\n\tJemmy Zhou\n\tRaymong Yin\n\tJianXiang Liu\n\tNikhil Pathania\n\tAllison Xu\n\tCharles Xu\n\tSharleen Zhou" +
                "\n\n\tChuck Goodman\n\tAnne Riley\n\tRoy Ho\n\tKai Goodman\n\tBrandon Chen\n\tStanley Zhang\n\tTiernan Barrie\n\tMichelle Dalarossa\n\tRushil Chakrabarty\n\tHelen Chang" +
                "\n\n\tSandra Kaye\n\tAllison Chan\n\tApril Huang\n\tAlan Liu\n\tAkhil Reddy\n\tHetav Gore\n\tNishir Shelat\n\tWilliam Ho\n\tGraciela Friend\n\tSamutratankul Mujarin" +
                "\n\n\tHoward Mai\n\tGrant Fu\n\tMackenzie Lim\n\tSarah Chong\n\tTrevor Wu\n\tSai Goli\n\tWilliam Kim\n\tNina Vasan\n\tDorothy Du\n\tYu Shu\n\tQiFei Teng" +
                "\n\n\tDorothy Griswold\n\tAndrew Zhang\n\tWaylon Peng\n\tRahul Chatwani\n\tNathon Ma\n\tAmanda Wang\n\tTCJ Cheng\n\tKevin Zhu\n\tKevin Yu\n\tAlbert Stanley\n\tNingNing Zhou" +
                "\n\n\tJanet Baker\n\tSean Li\n\tDylan Lim\n\tJelena Lee\n\tAlison Chen\n\tShagun Srivastava\n\tClaire Yung\n\tJoshua Zeng\n\tAnthony Herget\n\tRamona Gonzalez\n\tMarcos Jung\n\tRoselyn Jung" +
                "\n\n\tJean Jea\n\tEmily Xu\n\tAngela Yang\n\tMallika Chatterjee\n\tTanushri Sundar\n\tKevin Zhang\n\tIrene Yin\n\tVivasvan Vykunta\n\tZachary Kekoa\n\tAlexia Kekoa\n\tHerkea Jea" +
                "\n\n\tSamuel Chou\n\tAneri Parikh\n\tSeona Patel\n\tKenneth Leung\n\tAlex Yin\n\tRohan Nair\n\tShiva Ramani\n\tNicole Hsu\n\tDerek Xia\n\tAkshita Gandra\n\tTeresa Hsu");
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        e.setDepart(true);
        e.setDrawableId(R.drawable.flight_transfer);
        mOldEvents.add(e);

        // Departure of flight 42
        e = new oldEvent((TimeZone.getTimeZone("America/Chicago")), 2015, 3, 30, 15, 20, 0);
        e.setTitle("Flight 42 Transfer");
        e.setNotes("Chicago O'Hare to Aeroports de Paris. \n" +
                "\n" +
                "Contact: Harrison Cheng\n" +
                "1-(510)-579-8858" +
                "\n\nFlight 42 Roster:\n" +
                "\n\tMonica Kraft\n\tWenhan Fang\n\tSathvik Vivek\n\tYu-Cheng Chou\n\tYu-Ting Chou\n\tJemmy Zhou\n\tRaymong Yin\n\tJianXiang Liu\n\tNikhil Pathania\n\tAllison Xu\n\tCharles Xu\n\tSharleen Zhou" +
                "\n\n\tChuck Goodman\n\tAnne Riley\n\tRoy Ho\n\tKai Goodman\n\tBrandon Chen\n\tStanley Zhang\n\tTiernan Barrie\n\tMichelle Dalarossa\n\tRushil Chakrabarty\n\tHelen Chang" +
                "\n\n\tSandra Kaye\n\tAllison Chan\n\tApril Huang\n\tAlan Liu\n\tAkhil Reddy\n\tHetav Gore\n\tNishir Shelat\n\tWilliam Ho\n\tGraciela Friend\n\tSamutratankul Mujarin" +
                "\n\n\tHoward Mai\n\tGrant Fu\n\tMackenzie Lim\n\tSarah Chong\n\tTrevor Wu\n\tSai Goli\n\tWilliam Kim\n\tNina Vasan\n\tDorothy Du\n\tYu Shu\n\tQiFei Teng" +
                "\n\n\tDorothy Griswold\n\tAndrew Zhang\n\tWaylon Peng\n\tRahul Chatwani\n\tNathon Ma\n\tAmanda Wang\n\tTCJ Cheng\n\tKevin Zhu\n\tKevin Yu\n\tAlbert Stanley\n\tNingNing Zhou" +
                "\n\n\tJanet Baker\n\tSean Li\n\tDylan Lim\n\tJelena Lee\n\tAlison Chen\n\tShagun Srivastava\n\tClaire Yung\n\tJoshua Zeng\n\tAnthony Herget\n\tRamona Gonzalez\n\tMarcos Jung\n\tRoselyn Jung" +
                "\n\n\tJean Jea\n\tEmily Xu\n\tAngela Yang\n\tMallika Chatterjee\n\tTanushri Sundar\n\tKevin Zhang\n\tIrene Yin\n\tVivasvan Vykunta\n\tZachary Kekoa\n\tAlexia Kekoa\n\tHerkea Jea" +
                "\n\n\tSamuel Chou\n\tAneri Parikh\n\tSeona Patel\n\tKenneth Leung\n\tAlex Yin\n\tRohan Nair\n\tShiva Ramani\n\tNicole Hsu\n\tDerek Xia\n\tAkshita Gandra\n\tTeresa Hsu");
        e.setIsFlight(true);
        e.setIsFlightOne(false);
        e.setDepart(true);
        e.setDrawableId(R.drawable.flight_transfer);
        mOldEvents.add(e);

        // Flight 42 Arrival
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 6, 45, 0);
        e.setTitle("Flight 42 Arrival");
        e.setNotes("Flight Duration: 8h 35min" +
                "\n\n\t Touchdown at Aeroports de Paris. Welcome to France! \n" +
                "\n" +
                "Contact: Harrison Cheng\n" +
                "1-(510)-579-8858");
        e.setIsFlight(true);
        e.setIsFlightOne(false);
        e.setDrawableId(R.drawable.plane_landing);
        mOldEvents.add(e);

        // Flight 48 Arrival
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 7, 35, 0);
        e.setTitle("Flight 48 Arrival");
        e.setNotes("Flight Duration: 9h 35min" +
                "\n\n\t Touchdown at Aeroports de Paris. Welcome to France! \n\nContact: Anne Riley 1-(510)-569-7064");
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        e.setDrawableId(R.drawable.plane_landing);
        mOldEvents.add(e);

        //First Event !!! Needs a drawable ID, make it morning sun
        e = new oldEvent((TimeZone.getTimeZone("Europe/Parios")), 2015, 3, 31, 8, 0, 0);
        e.setTitle("Coach/Walking Tour");
        e.setNotes("\tOn your arrival in Paris after clearing the immigration checkpoint, you will be met in the arrivals area " +
                "by your tour assistant and taken on a guided coach and walking tour of Paris to see its iconic sites and landmarks " +
                "including the Eiffel Tower, the Arch de Triomphe, Notre Dame Cathedral, and the Lourve. \n\nLunch at Leisure!");
        e.setIsFlight(false);
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        //Curise on the river seine @ 3pm, use mid day icon
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 15, 0, 0);
        e.setTitle("River Seine Cruise");
        e.setNotes("\tAfter lunch, enjoy a guided cruise on the river seine. Draw attention to the monuments, history and " +
                "architecture along the left and right banks of the river.");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Travel back to the hotel
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 16, 0, 0);
        e.setTitle("Return to Hotel");
        e.setNotes("\tTravel by coach to your hotel to check in and freshen up.");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Return to the Hotel, needs icon, use twilight
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 18, 0, 0);
        e.setTitle("Info Meeting");
        e.setNotes("\tYour tour assistant will present an orientation meeting for you, where you will receive your information " +
                "and itinerary book, and learn all about your week in Paris and Maastricht. You will also have time to explore " +
                "the vicinity of your hotel to find the nearest general stores and ATM machines.");
        e.setIsFlight(false);
        e.setDrawableId(R.drawable.sunset);
        mOldEvents.add(e);

        // Dinner
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 18, 30, 0);
        e.setTitle("Dinner");
        e.setNotes("Dinner will be served in your hotel restaurant in Paris.");
        e.setIsFlight(false);
        e.setDrawableId(R.drawable.dinner_secondary);
        mOldEvents.add(e);

        // Depart to Pinewood watch
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 19, 45, 0);
        e.setTitle("Basilique Saint-Clotilde");
        e.setNotes("Depart by coach from the hotel.");
        e.setIsFlight(false);
        e.setDrawableId(R.drawable.night);
        mOldEvents.add(e);



        // Concert by Pinewood High School Chorale
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 20, 30, 0);
        e.setTitle("Attend Chorale");
        e.setNotes("\tAttend a performance by the Pinewood High School Chorale in central Paris. \n\n\t" +
                "Overnight at Novotel Paris Est, Paris");
        e.setDrawableId(R.drawable.note);
        mOldEvents.add(e);


        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 21, 30, 0);
        e.setTitle("Hotel");
        e.setNotes("\tReturn by coach to hotel. \n\n Overnight at Novotel Paris Est, Paris.\n\n Novotel Paris Est\n" +
                "1 Avenue de la Republique,\n" +
                "93177 Bagnolet,\n" +
                "France\n" +
                "Phone:+33 1 49 93 63 00");
        e.setDrawableId(R.drawable.night);
        mOldEvents.add(e);

        // DAY TWO -------------------------------------------------------------------------------------------------------------------------------------------------

        // Breakfast, use plate symbol
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 8, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mOldEvents.add(e);

        // Lourve
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 9, 0, 0);
        e.setTitle("Louvre");
        e.setNotes("\tDepart from the hotel by metro for the Louvre. This morning guided tour of the Louvre (3 groups 11.30, 3 groups 11.45 " +
                "entrance) Paris’ most famous museum as well as one of the world's largest " +
                "museums as well as a historic monument. The Louvre is home to one of the most " +
                "famous paintings in the world, Leonardo da Vinci’s Mona Lisa. \n\n Lunch at leisure!");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);


        // Hotel by Metro
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 14, 0, 0);
        e.setTitle("Hotel");
        e.setNotes("\tReturn to the hotel by metro.");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Arrondissement
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 15, 0, 0);
        e.setTitle("Arrondissement");
        e.setNotes("\tDepart from the hotel by coach for the Mairie du ème Arrondissement.");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Performance at 4
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 16, 0, 0);
        e.setTitle("Performance");
        e.setNotes("\tPerformance in the Salle d’honneur in the Mairie.");
        e.setDrawableId(R.drawable.note);
        mOldEvents.add(e);

        // Montmarte
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 19, 0, 0);
        e.setTitle("Montmartre");
        e.setNotes("\tDepart from the performance by coach for dinner this evening in Montmartre.");
        e.setDrawableId(R.drawable.sunset);
        mOldEvents.add(e);

        // Dinner
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 18, 0, 0);
        e.setTitle("Restaurant");
        e.setNotes("\t Dinner will be served in the La Bonne Franquette Restaurant.");
        e.setDrawableId(R.drawable.dinner_secondary);
        mOldEvents.add(e);

        // Dinner
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 22, 30, 0);
        e.setTitle("Hotel");
        e.setNotes("\t Return by coach to the hotel after dinner. \n\n Overnight at Novotel Paris Est, Paris.\n\nNovotel Paris Est\n" +
                "1 Avenue de la Republique,\n" +
                "93177 Bagnolet,\n" +
                "France\n" +
                "Phone:+33 1 49 93 63 00");
        e.setDrawableId(R.drawable.night);
        mOldEvents.add(e);


        // Day Three ---------------------------------------------------------------------------------------------------------------------------------------------------

        //Breakfast
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 7, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mOldEvents.add(e);

        //Breakfast
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 8, 0, 0);
        e.setTitle("Versailles");
        e.setNotes("\t Depart from the hotel by coach for Versailles");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        // Lourve
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 9, 0, 0);
        e.setTitle("Lourve");
        e.setNotes("\t Take an audio guided tour around The Palace of Versailles " +
                "built by the ‘Sun King’, Louis XIV. You will have the chance to visit the most famous " +
                "places in the Palace including the hall of mirrors, the grand apartments of the King " +
                "and Queen and the formal gardens.\n\nLunch at leisure \n\nReturn to the hotel to change into performance uniforms.");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 14, 0, 0);
        e.setTitle("Luxembourg Gardens");
        e.setNotes("\t Depart by coach from the hotel to the Luxembourg Gardens.");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // marching band performance
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 15, 0, 0);
        e.setTitle("Parade");
        e.setNotes("\t This afternoon performance for the marching band in the famous Luxembourg Gardens. followed by an outdoor concert in the band shell.");
        e.setDrawableId(R.drawable.note);
        mOldEvents.add(e);

        // Dinner
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 19, 0, 0);
        e.setTitle("Palais Royal");
        e.setNotes("\t Depart by coach for dinner this evening near the Palais Royal.");
        e.setDrawableId(R.drawable.sunset);
        mOldEvents.add(e);

        // Free Time
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 20, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner will be served in the Fontaines Saint Honoré Restaurant." +
                "\n\n\"Time to go on an Adventure!!\" - Kai Goodman, after 2nd period, Spanish I, 2012" +
                "\n\nOvernight at Novotel Paris Est, Paris Novotel Paris Est\n" +
                "1 Avenue de la Republique,\n" +
                "93177 Bagnolet,\n" +
                "France\n" +
                "Phone:+33 1 49 93 63 00");
        e.setDrawableId(R.drawable.night);
        mOldEvents.add(e);

        // day Four ------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Breakfast
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)),2015, 4, 3, 7, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel");
        e.setDrawableId(R.drawable.food);
        mOldEvents.add(e);

        // Depart to Maastricht
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 8, 0, 0);
        e.setTitle("Maastrich");
        e.setNotes("\t Depart by coach to Maastricht.");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        // Stop of in Reims
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 9, 30, 0);
        e.setTitle("Reims Stop");
        e.setNotes("\t En route stop off in Reims in France to have a look in the Notre-Dame Cathedral of Reims where the Kings of France were crowned. Time also for coffee and snack break.\n\nReims Cathedral: Place du Cardinal Luçon\n" +
                "Free entry");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        // Depart from Reims
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 11, 0, 0);
        e.setTitle("Depart from Reims");
        e.setNotes("\t Depart from Reims");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Arrive in Bastogne
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 13, 0, 0);
        e.setTitle("Arrive in Bastogne");
        e.setNotes("\t Arrive in Bastogne in Belgium, the location of the most famous battle in World War II: The Battle of the Bulges. Visit the War Museum (entrance ALL). A sandwich lunch " +
                "has been arranged in the museum. \n\nBastogne War Museum: Colline du Mardasson, 5 - 6600 Bastogne\n" +
                "Contact: Laurence Piens + 32 (0)61 21 02 20. He has organised entrance and\n" +
                "lunch for the group. No guided tour.\n" +
                "1pm—1.30pm – lunch in the museum\n" +
                "1.30pm-2.30pm – look around the museum");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Depart Bastogne
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 14, 30, 0);
        e.setTitle("Depart Bastogne");
        e.setNotes("\t Depart from Bastogne");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Arrive in Margraten
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 16, 0, 0);
        e.setTitle("Margraten");
        e.setNotes("\t Arrive in Margraten and the Margraten Cemetary. The WWII Netherlands American Cemetery and Memorial, Europe's third largest war cemetery for unidentified soldiers who died in WWII. Visit the cemetery and the Brass Choir" +
                " perform taps. \n\nAmerican Battle Monuments Commission, +31 43 45 81 208\n" +
                "Contact: Keith K. Stadler (Superintendent), He has approved the group playing\n" +
                "taps and laying wreaths. Anne Riley has organised wreaths with Brigitte\n" +
                "Ruigrok\n" +
                "Margraten, Tel: (+31) 43-458-9199");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Depart Margraten
        e = new oldEvent((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 17, 30, 0);
        e.setTitle("Depart Margraten");
        e.setNotes("\t Depart from Margraten");
        e.setDrawableId(R.drawable.sunset);
        mOldEvents.add(e);

        // Arrive in Maastricht
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 3, 18, 0, 0);
        e.setTitle("Maastricht");
        e.setNotes("\t Arrive in Maastricht and check into your hotel.");
        e.setDrawableId(R.drawable.sunset);
        mOldEvents.add(e);

        // Dinner
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 3, 19, 30, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner this evening will be served in your hotel restaurant in Maastricht.\n\nBuffet dinner: NH MAASTRICHT BUFFET\n" +
                "Vitello tonnato,\n Ardennes pate with tutti frutti,\n Salmon tartare with lime cream,\n" +
                "Salad with prawns and squid,\n Assortment of salads with bread,\n tapenade and " +
                "butter,\n Tomato soup with basil cream,\n Thai chicken broth,\n Fried redfish with " +
                "antiboise,\n Chicken in a Teriyaki Sauce,\n Vegetarian couscous with raisins and " +
                "nuts,\n Potato gratin,\n Warm seasonal vegetables,\n Panna cotta with grilled " +
                "pineapple,\n Apple strudel with sabayon,\n Fresh fruit salad,\n Cheese platter with" +
                "syrup,\n honey and fig bread. \n\n Overnight at NH Hotel, Maastricht\n\n Hotel NH Maastricht\n" +
                "Forum 110,\n" +
                "6229 GV Maastricht,\n" +
                "Netherlands\n" +
                "Phone:+31 43 383 8281");
        e.setDrawableId(R.drawable.dinner_secondary);
        mOldEvents.add(e);

        // Day Five (4 April) -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Breakfast
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 8, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel");
        e.setDrawableId(R.drawable.food);
        mOldEvents.add(e);

        // Tour of Maastricht
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 10, 0, 0);
        e.setTitle("Tour of Maastricht");
        e.setNotes("\t Tour ends at 12:30. \n\n This morning depart on a guided coach and walking TOUR OF MAASTRICHT ( 4 " +
                "guides for coach tour, 8 guided for walking tour) from your hotel taking you all " +
                "around the old city on one side of the Maas River and the contemporary new " +
                "Maastricht on the other. Discover the ancient fortifications, seventeenth century " +
                "townhouses the main squares of the Vrijthof and the Markt. \n" +
                "\n" +
                "Independent lunch in Maastricht " +
                "\n\n\nMeeting point : lobby of hotel, guides coming to the hotel. " +
                "1 hour on coach. 1.5 hr walking tour. Walking tour finishes in Vrijthof.\n" +
                "Contact: +31 (0)43 3506267 ");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        // Boat Trip
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 15, 0, 0);
        e.setTitle("Boat Trip");
        e.setNotes("\t Event ends at 6 pm.\n\n\t This afternoon take a short boat trip through the city passing the Bonnefanten Museum, the provincial government building and the slopes of the St. Pieters hill up to the Belgium border." +
                " You will have the chance to go ashore at the St. Pieters Hill for a guided tour in the Marlstone caves before returning by boat to Maastricht. " +
                "\n\nStart point: Rederij Stiphout, Maaspromenade 58.\n" +
                "Contact: +31 (0)43 3515300\n" +
                "The caves trip not suitable for wheelchairs.");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Dinner
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 20, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner will be served on an ship which stays in the harbour. Maaspromenade 58 in " +
                "Maastricht, between the St Servaas Bridge and Wilhelmina Bridge. \n\n\nRederij Stiphout Floating Company, Maaspromenade 58.\n" +
                "Contact: +31 (0)43 3515300\n" +
                "Duration: 2 Hours.\n" +
                "Charter of own ship (moored at our landing stage)\n" +
                "3 tickets for 3 drinks (soft drinks, juices, coffee and tea\n" +
                "Cold and warm buffet\n" +
                "Decoration of the boat with flowers and candles\n" +
                "Departure 20:00 hour End 22:00 hour\n" +
                "Cold dishes, Ardennes ham with gala melon, Seasonal pate with sauce\n" +
                "Monegasque, Pork fillet with mixed herbs, Smoked salmon with dill and capers,\n" +
                "Norwegian shrimp with cocktail sauce, Various salads, Baguette, mini rolls and\n" +
                "butter, Jambon Provence with a mushroom sauce, Breast of chicken with a\n" +
                "stroganoff sauce, Potato Slices prepared with cream and topped with old\n" +
                "cheese \n\n\nOvernight at NH Hotel, Maastricht Hotel NH Maastricht\n" +
                "Forum 110,\n" +
                "6229 GV Maastricht,\n" +
                "Netherlands\n" +
                "Phone:+31 43 383 8281");
        e.setDrawableId(R.drawable.dinner_secondary);
        mOldEvents.add(e);

        // Day Six (April 5th, Easter Sunday-----------------------------------------------------------------------------------------------------------------------------

        // Breakfast
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 7, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Happy Easter Sunday! Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mOldEvents.add(e);

        // Depart for Aachen
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 8, 30, 0);
        e.setTitle("Depart to Aachen");
        e.setNotes("\t Depart for Aachen, Germany, located 30 minutes from Maastricht. \n\nThe spa town of Aachen was a favoured residence of Charlemagne, and later the " +
                "place of coronation of the German kings.");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        // Guided tour of Aachen
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 9, 30, 0);
        e.setTitle("Tour of Aachen");
        e.setNotes("\t Take a guided walking tour of the city and visit Aachen Cathedral. The spa town of Aachen " +
                "was favoured residence of Charlemagne, and later the place of coronation of the German Kings.\n\n Independent lunch. \n\n\n(6 groups total, 2 group of 28 " +
                "people at 9.30am, 4 groups of 27/28 people at 11am)\n" +
                "Meeting point: Tourist office (Friedrich-Wilhelm-Platz)\n" +
                "End point: For 11am start groups tour ends at tourist information office by\n" +
                "Busstop Tjheaterplatz to be near to the coach.\n" +
                "Contact: Dominik Herff, Aachen Tourist Office +49 (0)2 41 180 2960/61");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        // Return to Maastricht
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 13, 0, 0);
        e.setTitle("Return to Maastricht");
        e.setNotes("Return to Maastricht");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Performance Streetshow
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 14, 30, 0);
        e.setTitle("Marching Performance");
        e.setNotes("Performance / Streetshow for the marching band around Maastricht Old Town including the Maarkt and " +
                "the Vrijthof.");
        e.setDrawableId(R.drawable.note);
        mOldEvents.add(e);

        // Technical rehearsal
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 16, 30, 0);
        e.setTitle("Technical Rehearsal");
        e.setNotes("\t Technical rehearsal for the concert this evening.\n\n\nBrusselseweg 150 152, 6217 HB Maastricht, Netherlands " +
                "Contact: Raissa Reintjens");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        // Dinner
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 17, 45, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner will be served at a restaurant in Maastricht near the Conservatorium. \n\n\nGrand Café D'n Ingel, (90 people)/ Restaurant Edd's, (76 people)\n" +
                "Grand Café d’Ingel – Vrijthof 13. +31 (0)43 321 7226\n" +
                "Restaurant Edd's, Heggenstraat 3, Maastricht +31 (0)43 352 1717\n" +
                "Same menu being served in both restaurants.\n" +
                "Wheelchair access only in Edd’s\n" +
                "\n\n\nMenu:" +
                "Beef carpaccio with truffle cream,\n Parmesan and rocket salad\n" +
                "Chickenbreast Tuscany style, filled with mozzarella,sun dried tomatoes and\n" +
                "spinach\n" +
                "Chocolate delicacies\n" +
                "Including 2 softdrinks.\n" +
                "Start 17:45 hour End 19:30 hour");
        e.setDrawableId(R.drawable.dinner_secondary);
        mOldEvents.add(e);

        // Performance
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 20, 0, 0);
        e.setTitle("Performance");
        e.setNotes("\t Performane for the Band and Orchestra at the Conservatorium - Maasticht Academy of Music. \n\n" +
                " Overnight at NH Hotel, Maastricht" +
                "\n\n\nBrusselseweg 150 152, 6217 HB Maastricht, Netherlands\n" +
                "Contact: Raissa Reintjens" +
                "\n\n\nHotel NH Maastricht\n" +
                "Forum 110,\n" +
                "6229 GV Maastricht,\n" +
                "Netherlands\n" +
                "Phone:+31 43 383 8281");
        e.setDrawableId(R.drawable.note);
        mOldEvents.add(e);

        // April 6th  Day 7??
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 6, 30, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mOldEvents.add(e);

        // Depart this morning
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 7, 30, 0);
        e.setTitle("Depart for Paris");
        e.setNotes("\t Depart this morning by coach to Paris");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 10, 0, 0);
        e.setTitle("Arrive in Bruges");
        e.setNotes("\t Arrive in BRUGES, Belgium and take a World Heritage Walk GUIDED WALKING " +
                "TOUR around the fairy-tale medieval city, to see Saint Salvator's Cathedral, the " +
                "Belfry Tower and the Old St John’s Hospital.");
        e.setDrawableId(R.drawable.morn);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 12, 0, 0);
        e.setTitle("Boat Ride");
        e.setNotes("\t Continue your tour of the city by taking a 30 min boat ride along the canals in Bruges " +
                "showing you the city from a different perspective and taking you to some of the hard to reach areas by " +
                "foot or bus.\n\nIndependent Lunch \n\n\n(67 people boat station 2, 100 people boat " +
                "station 1 – the guides will finish their tour at appropriate boat station)BOAT STATION 1 - 100 people, will be dropped off by 4 guides\n" +
                "Bootexcursies Gruuthuse (Nr. 3) - Gruuthuse, Nieuwstraat 11, 8000 Brugge Tel: " +
                "+32 (0)50 333293\n" +
                "BOAT STATION 2 - 67 people, will be dropped off by 3 guides " +
                "Coudenys aanlegsteiger (Nr. 2) - Dhr. Kevin Coudenys, Rozenhoedkaai, 8000\n" +
                "Brugge Tel: +32 (0)50 331375");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        e =  new oldEvent(TimeZone.getTimeZone(sParisZone) ,2015, 4, 6, 14, 0, 0);
        e.setTitle("Depart Bruges");
        e.setNotes("\t Depart Bruges. En route stop in Waterloo to view the magnificent monument to one of the most decisive " +
                "battles 100 years after Napolean lost to the Duke of Wellington.");
        e.setDrawableId(R.drawable.noon);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 18, 30, 0);
        e.setTitle("Arrive in Paris");
        e.setNotes("\t You will have the chance to check into your hotel and freshen up before dinner.");
        e.setDrawableId(R.drawable.sunset);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 19, 0, 0);
        e.setTitle("Depart for Dinner");
        e.setNotes("\t Depart by coach from the hotel for dinner.");
        e.setDrawableId(R.drawable.sunset);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 20, 0, 0);
        e.setTitle("Farewell Dinner");
        e.setNotes("\t Farewell dinner will be served in a restaurant in Paris (Clement Champs-Elysses). After dinner visit the Eiffel tower, perhaps " +
                "Paris' most iconic landmark with a fantastic view out across Paris, a marvellous Au Revior to our trip abroad. " +
                "Afterwards stop to get some last minute souvenirs before returning to your hotel. \n\nOvernight at Novotel Paris Est, Paris.");
        e.setDrawableId(R.drawable.dinner_secondary);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 22, 0, 0);
        e.setTitle("Eifell Tower");
        e.setNotes("\t After dinner visit the EIFFEL TOWER by night, perhaps Paris’ most iconic landmark\n" +
                "with fantastic views out across Paris. (all as 1 group) \n\n\nOvernight at Novotel Paris Est, Paris. \n\n\nHotel NH Maastricht\n" +
                "Forum 110,\n" +
                "6229 GV Maastricht,\n" +
                "Netherlands\n" +
                "Phone:+31 43 383 8281");
        e.setDrawableId(R.drawable.eifell);
        mOldEvents.add(e);

        // Last day -----------------------------------------------------------------------------------------------------------------
        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 6, 0, 0);
        e.setTitle("Breakfast Info");
        e.setNotes("Breakfast boxes will be prepared for breakfast this morning.");
        e.setDrawableId(R.drawable.food);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 6, 30, 0);
        e.setTitle("Flight 41 Leaves Hotel");
        e.setNotes("\t Flight 41 departures depart from the hotel.");
        e.setDrawableId(R.drawable.morn);
        e.setIsFlight(true);
        e.setIsFlightOne(false);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 7, 00, 0);
        e.setTitle("Flight 49 Leaves Hotel");
        e.setNotes("\t Flight 49 departures depart from the hotel.");
        e.setDrawableId(R.drawable.morn);
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 9, 25, 0);
        e.setTitle("FLight 41 Departs");
        e.setNotes("\t Flight AA41 departs from Aeroports de Paris. Set to arrive in O'Hare at 12:25 local." +
                "\n\n Flight Data:");
        e.setDrawableId(R.drawable.plane_takeoff);
        e.setIsFlightOne(false);
        e.setIsFlight(true);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 11, 20, 0);
        e.setTitle("Flight 49 Departs");
        e.setNotes("\t Flight AA49 departs for Dallas Fort Worth. Set to arrive at 3:20 pm local." +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        e.setDrawableId(R.drawable.plane_takeoff);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 12, 25, 0);
        e.setTitle("Flight 41 Touchdown");
        e.setNotes("Flight 41 lands at O'Hare. Don't forget to transfer to fight 1611 in 4hr 35 min after landing (5pm local)" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(false);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 15, 20, 0);
        e.setTitle("Flight 49 Touchdown");
        e.setNotes("Flight 49 lands at Dallas Fort worth. Remember to transfer to flight 1388 in 2 hours after landing (5:20pm local)" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(true);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 17, 0, 0);
        e.setTitle("Transfer 1611 Departs");
        e.setNotes("Flight 1611 departs Chicago for San Fransisco International. Set to arrive at 7:26 local" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.flight_transfer);
        e.setIsFlightOne(false);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 17, 20, 0);
        e.setTitle("Transfer 1388 Departs");
        e.setNotes("FLight 1388 departs Dallas Fort Worth for San Fransisco International. Set to arrive at 7:16 local" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setDrawableId(R.drawable.flight_transfer);
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        mOldEvents.add(e);

        // Landings
        e = new oldEvent(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 4, 7, 19, 16, 0);
        e.setTitle("Flight 1388 Arrival");
        e.setNotes("Flight 1388 arrives at SFO." + "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(true);
        mOldEvents.add(e);

        e = new oldEvent(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 4, 7, 19, 56, 0);
        e.setTitle("Flight 1611 Arrival");
        e.setNotes("Flight 1611 arrives at SFO." + "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(false);
        mOldEvents.add(e);

        return mOldEvents;

    }



    public void setTrueFalse() {
        int flightstatus = getFlightOne();

        for (oldEvent e : mMasterOldEvents) {

            Boolean isFlightOne = e.getIsFlightOne();
            Boolean isFlight = e.getIsFlight();
            Boolean isDepart = e.isDepart();

            if ((flightstatus == 1) && (isFlightOne) && (isFlight)){ // items is both flight 1 and person is flight one

                e.setIsApplicable(true);

            } else if ((flightstatus == -1) && (isFlight)) { // items is a flight, but flight status unknown

                e.setIsApplicable(true);

            } else if ((flightstatus == 1) && (!isFlightOne) && (isFlight)) { // items is not flight one, but person is flying one
                e.setIsApplicable(false);

            } else if ((flightstatus == 0) && (isFlightOne) && (isFlight)) { // items is flight one, but person not flying flight one
                e.setIsApplicable(false);

            } else if ((flightstatus == 0) && (!isFlightOne) && (isFlight)) { // items is not flight one, and person not flying flihg tone

                e.setIsApplicable(true);
            }

        }

    }




    public boolean isRemoveIrrelevant() {
        return removeIrrelevant;
    }

    public void setRemoveIrrelevant(boolean removeIrrelevant) {
        this.removeIrrelevant = removeIrrelevant;
    }

    public static oldEventsLab get(Context c) {
        if (sOldEventsLab == null) {

            sOldEventsLab = new oldEventsLab(c.getApplicationContext());
        }

        return sOldEventsLab;

    }

    public oldEvent getEvent(UUID id) {

        for (oldEvent e : getEvents()) {
            if (e.getId().equals(id))
                return e;
        }
        return null;

    }

    public int getFlightOne() {
        return mFlightOne;
    }


    public String getStudentName() {

        if ((studentName == null) || (studentName.equals("")))
            return "Set Name";
        return "Reset: " + studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        mName = studentName;
        mFlightOne = -1;

        if ((Flight2283Roster.toLowerCase()).contains(studentName.toLowerCase()))
            mFlightOne = 1;
        else if ((Flight2411Roster.toLowerCase()).contains(studentName.toLowerCase()))
            mFlightOne = 0;

    }

    public String getName(){
        return mName;
    }

    public void setName(String string){
        mName = string;
    }

    public oldEvent getEvent(int position) {

        return mMasterOldEvents.get(position);

    }


    public ArrayList<oldEvent> getEvents() {return mMasterOldEvents;}





}
