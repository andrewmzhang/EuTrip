package com.msjBand.kraftscangradle.kraftscan;


import android.content.Context;

import java.util.ArrayList;
import java.util.TimeZone;


//"\n\n\t On your arrival in to Paris, after clearing immigration and customs, you will be men in the arrivals area by your tour assistant and taken on a guided" +
//" coach and walking tour of Paris to see its iconic sites and landmarks including the Eiffel Tower, the Arch de Triomphe, Notre Dame Cathedral, and the Lourve." +
 //       "\n\n Lunch at Leisure."

public class EventsLab {

    // Settings Data
    private boolean removeIrrelevant;
    private boolean isUserSet;
    private String mUserName;
    private boolean autoScroll;
    private boolean removePastEvents;
    private boolean disableAllAlarms;



    private static EventsLab sEventsLab;
    private String studentName;
    private Context mAppContext;
    private final static String sParisZone= "Europe/Paris";

    private ArrayList<Event> mEvents;
    private String mName = "Search Flight Roster";

    private String Flight2283Roster = "Monica Kraft\nWenhan Fang\nSathvik Vivek\nYu-Cheng Chou\nYu-Ting Chou\nJemmy Zhou\nRaymong Yin\nJianXiang Liu\nNikhil Pathania\nAllison Xu\nCharles Xu\nSharleen Zhou" +
            "\n\nChuck Goodman\nAnne Riley\nRoy Ho\nKai Goodman\nBrandon Chen\nStanley Zhang\nTiernan Barrie\nMichelle Dalarossa\nRushil Chakrabarty\nHelen Chang" +
            "\n\nSandra Kaye\nAllison Chan\nApril Huang\nAlan Liu\nAkhil Reddy\nHetav Gore\nNishir Shelat\nWilliam Ho\nGraciela Friend\nSamutratankul Mujarin" +
            "\n\nHoward Mai\nGrant Fu\nMackenzie Lim\nSarah Chong\nTrevor Wu\nSai Goli\nWilliam Kim\nNina Vasan\nDorothy Du\nYu Shu\nQiFei Teng" +
            "\n\nDorothy Griswold\nAndrew Zhang\nWaylon Peng\nRahul Chatwani\nNathon Ma\nAmanda Wang\nTiancheng Cheng\nKevin Zhu\nKevin Yu\nAlbert Stanley\nNingNing Zhou" +
            "\n\nJanet Baker\nSean Li\nDylan Lim\nJelena Lee\nAlison Chen\nShagun Srivastava\nClaire Yung\nJoshua Zeng\nAnthony Herget\nRamona Gonzalez\nMarcos Jung\nRoselyn Jung" +
            "\n\nJean Jea\nEmily Xu\nAngela Yang\nMallika Chatterjee\nTanushri Sundar\nKevin Zhang\nIrene Yin\nVivasvan Vykunta\nZachary Kekoa\nAlexia Kekoa\nHerkea Jea" +
            "\n\nSamuel Chou\nAneri Parikh\nSeona Patel\nKenneth Leung\nAlex Yin\nRohan Nair\nShiva Ramani\nNicole Hsu\nDerek Xia\nAkshita Gandra\nTeresa Hsu";

    private String Flight2411Roster = "\tMark Aherns\n\tEvan Lwin\n\tJoyce Wang\n\tJulie ChenYi Wang\n\tAimee Xu\n\tEmma Chang\n\tClaire Wu\n\tApoorva Prakash\n\tKosh Kumar\n\tHsien-Hsiang Lin\n" +
            "\n\tHarrison Cheng\n\tKelly Shi\n\tSavana Wang\n\tJustin Nguyen\n\tFendy Gao\n\tSerena Young\n\tMarianne Rara" +
            "\n\n\tAnn Kwan\n\tJonathan Bright Li\n\tStephanie Bi\n\tAshley Chen\n\tJennifer Wei\n\tNikhita Ganesh\n\tMaya Sudarsan\n\tAaron Zhang\n\tJerry Lin\n\tLi Diao" +
            "\n\n\tClaudia Fort\n\tAnna Pi\n\tVivian Ross\n\tLeon Ming\n\tVarsha Rajagopalan\n\tWilliam Zeng\n\tDonna li\n\tVineeth Yeevani\n\tKiran Raja\n\tRaja Jayakumar" +
            "\n\n\tMargaret Taylor\n\tNivedha Karthikeyan\n\tKate Lin\n\tAnjali Joseph (Mitter)\nJolene Tsai\n\tSabrina Liu\n\tJoyce Pi\n\tAn Tran\n\tKhang Tran\n\tThach Ngo Tran" +
            "\n\n\tLesley Wilhite\n\tKunal Agarwal\n\tCory Lam\n\tColby Huang\n\tBrian Zhao\n\tAlexander Chen\n\tSai Dwibhashyam\n\tAshank Verma\n\tVictor He\n\tLi Deng" +
            "\n\n\tYue Zhao\n\tSara Tsai\n\tJessica Eng\n\tDouglas Lam\n\tAngus Fung\n\tWilliam Luk\n\tKathleen Zhou\n\tBrandon Lu\n\tDiana Minyi Lu" +
            "\n\n\tRaymond Mendonca\n\tJessica Mao\n\tDelaine Rogers\n\tAnnie Xu\n\tLyann Choi\n\tRushalee Nirodi\n\tMyra Awan\n\tDanice Long\n\tDiana Gia Tran";

    private int mFlightOne = -1;   //-1 is unknown, 0 is not on Flight One (2283), 1 is on Flight One

    private EventsLab(Context appContext) {

        mAppContext = appContext;
        mEvents = new ArrayList<Event>();

        this.disableAllAlarms = false;
        this.isUserSet = false;
        mUserName = null;
        this.removeIrrelevant = false;
        this.removePastEvents = true;


        // Arrival @ 5am
        Event e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 5, 0, 0);
        e.setTitle("Airport SFO");
        e.setNotes("\t Arrive at SFO American Airlines Domestic Departures Terminal 2");
        e.setDrawableId(R.drawable.airport);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 7, 45, 0);
        e.setTitle("Flight 2411 Departure");
        e.setNotes("\tSan Fransisco Airport to Chicago O'Hare Airport. Remember to transfer to flight 042 at 3:10 pm local time. \n\nFlight 2411 Roster: \n"
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
        mEvents.add(e);

        // Departure of Flight 2283 (one)
        e =  new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 8, 35, 0);
        e.setTitle("Flight 2283 Departure");
        e.setNotes("\tSan Fransisco Airport to Dallas Forth Worth International Airport. Remember to transfer to flight 048 at 3:05 pm local time. \n\nFlight 2283 Roster:\n"
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
        mEvents.add(e);


        // Touchdown of Flight 2283 (one)
        e = new Event(TimeZone.getTimeZone("America/Chicago"), 2015, 3, 30, 14, 05, 0);
        e.setTitle("Flight 2283 Arrival");
        e.setNotes("Flight Duration: 3h 30min \n\n\tTouchdown at Dallas Forth Worth Airport. According to Google reviews, this place has really fast internet speeds...\n\n\tDon't forget to transfer flights in 1 hour (15:05)");
        e.setIsFlight(true);
        e.setDepart(false);
        e.setIsFlightOne(true);
        e.setDrawableId(R.drawable.plane_landing);
        mEvents.add(e);

        // Touchdown of Flight 2411
        e = new Event((TimeZone.getTimeZone("America/Chicago")), 2015, 3, 30, 14, 07, 0);
        e.setTitle("Flight 2411 Arrival");
        e.setNotes("Flight Duration: 4h 22min \n\n\tTouchdown at Chicago O'Hare Airport. According to Google reviews, this airport is notorious for delaying flights, and rates a 3.6/5... \n\n\t Don't forget" +
                " to transfer flights in 1 hour (15:10)");
        e.setIsFlight(true);
        e.setIsFlightOne(false);
        e.setDepart(false);
        e.setDrawableId(R.drawable.plane_landing);
        mEvents.add(e);

        // Departure of flight 048 (one)
        e = new Event((TimeZone.getTimeZone("America/Chicago")), 2015, 3, 30, 15, 05, 0);
        e.setTitle("Flight 48 Transfer");
        e.setNotes("Dallas Fort Worth to Aeroports de Paris. " +
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
        mEvents.add(e);

        // Departure of flight 42
        e = new Event((TimeZone.getTimeZone("America/Chicago")), 2015, 3, 30, 15, 20, 0);
        e.setTitle("Flight 42 Transfer");
        e.setNotes("Chicago O'Hare to Aeroports de Paris. " +
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
        mEvents.add(e);

        // Flight 42 Arrival
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 6, 45, 0);
        e.setTitle("Flight 42 Arrival");
        e.setNotes("Flight Duration: 8h 35min" +
                "\n\n\t Touchdown at Aeroports de Paris. Welcome to France!");
        e.setIsFlight(true);
        e.setIsFlightOne(false);
        e.setDrawableId(R.drawable.plane_landing);
        mEvents.add(e);

        // Flight 48 Arrival
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 7, 35, 0);
        e.setTitle("Flight 48 Arrival");
        e.setNotes("Flight Duration: 9h 35min" +
                "\n\n\t Touchdown at Aeroports de Paris. Welcome to France");
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        e.setDrawableId(R.drawable.plane_landing);
        mEvents.add(e);

        //First Event !!! Needs a drawable ID, make it morning sun
        e = new Event((TimeZone.getTimeZone("Europe/Parios")), 2015, 3, 31, 8, 0, 0);
        e.setTitle("Coach/Walking Tour");
        e.setNotes("\tOn your arrival in Paris after clearing the immigration checkpoint, you will be met in the arrivals area " +
                "by your tour assistant and taken on a guided coach and walking tour of Paris to see its iconic sites and landmarks " +
                "including the Eiffel Tower, the Arch de Triomphe, Notre Dame Cathedral, and the Lourve. \n\nLunch at Leisure!");
        e.setIsFlight(false);
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        //Curise on the river seine @ 3pm, use mid day icon
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 15, 0, 0);
        e.setTitle("River Seine Cruise");
        e.setNotes("\tAfter lunch, enjoy a guided cruise on the river seine. Draw attention to the monuments, history and " +
                "architecture along the left and right banks of the river.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Travel back to the hotel
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 16, 0, 0);
        e.setTitle("Return to Hotel");
        e.setNotes("\tTravel by coach to your hotel to check in and freshen up.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Return to the Hotel, needs icon, use twilight
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 18, 0, 0);
        e.setTitle("Info Meeting");
        e.setNotes("\tYour tour assistant will present an orientation meeting for you, where you will receive your information " +
                "and itinerary book, and learn all about your week in Paris and Maastricht. You will also have time to explore " +
                "the vicinity of your hotel to find the nearest general stores and ATM machines.");
        e.setIsFlight(false);
        e.setDrawableId(R.drawable.sunset);
        mEvents.add(e);

        // Dinner
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 19, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("Dinner will be served in your hotel restaurant in Paris.");
        e.setIsFlight(false);
        e.setDrawableId(R.drawable.dinner_secondary);
        mEvents.add(e);
        
        // Concert by Pinewood High School Chorale
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 3, 31, 20, 0, 0);
        e.setTitle("Attend Chorale");
        e.setNotes("\tAttend a performance by the Pinewood High School Chorale in central Paris. \n\n\t" +
                "Overnight at Novotel Paris Est, Paris");
        e.setDrawableId(R.drawable.note);
        mEvents.add(e);


        // DAY TWO -------------------------------------------------------------------------------------------------------------------------------------------------

        // Breakfast, use plate symbol
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 8, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mEvents.add(e);

        // Versailles
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 9, 0, 0);
        e.setTitle("Versailles");
        e.setNotes("\tDepart for Versailles and take an audio guided tour around The Palace of Versailles built by the 'Sun King', Louis XIV. You will have the chance " +
                "to visit the most famous places in the Palace including the hall of mirrors, the grand apartments of the King and Queen and the formal gardens. \n\n Lunch at leisure!");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);


        // Free Time
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 13, 0, 0);
        e.setTitle("Free Time");
        e.setNotes("\t This afternoon has been left free for independent sightseeing and shopping in Paris. Your tour assistant will be on hand to suggest plenty " +
                "of things to you that you might like to see or do.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Dinner
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 18, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("\t An early dinner will be served in a restaurant in central Paris.");
        e.setDrawableId(R.drawable.dinner_secondary);
        mEvents.add(e);

        // Performance
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 1, 20, 0, 0);
        e.setTitle("Performance");
        e.setNotes("\t There is a performance at Notre Dame or Basilica de Sacre Coeur in Paris for the Full Orchestra, Wind Symphony, and Brass Choir" +
                " \n\nOvernight at Novotel Paris Est, Paris");
        e.setDrawableId(R.drawable.note);
        mEvents.add(e);

        // Day Thre ---------------------------------------------------------------------------------------------------------------------------------------------------

        //Breakfast
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 8, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mEvents.add(e);

        // Lourve
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 10, 0, 0);
        e.setTitle("Lourve");
        e.setNotes("\t This morning visit the Lourve, Paris' most famous museum as well as one of the world's largest museums as well as a historic monument." +
                " The Lourve is home to one of the most famous paintings in the world, Leonardo da Vinci's Mona Lisa.\n\nLunch at leisure");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        // marching band performance
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 15, 0, 0);
        e.setTitle("Parade");
        e.setNotes("\t This afternoon performance for the marching band in the famous Luxembourg Gardens followed by an outdoor concert in the band shell.");
        e.setDrawableId(R.drawable.note);
        mEvents.add(e);

        // Dinner
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 19, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner will be served in a restaurant in central Paris");
        e.setDrawableId(R.drawable.dinner_secondary);
        mEvents.add(e);

        // Free Time
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 2, 20, 30, 0);
        e.setTitle("Free Time");
        e.setNotes("\t Evening free to explore Bastille Square or the Latin Quarter" +
                "\n\n\"Time to go on an Adventure!!\" - Kai Goodman, after 2nd period, Spanish I, 2012" +
                "\n\nOvernight at Novotel Paris Est, Paris");
        e.setDrawableId(R.drawable.night);
        mEvents.add(e);

        // day Four ------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Breakfast
        e = new Event((TimeZone.getTimeZone(sParisZone)),2015, 4, 3, 7, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel");
        e.setDrawableId(R.drawable.food);
        mEvents.add(e);

        // Depart to Maastricht
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 8, 0, 0);
        e.setTitle("Maastrich");
        e.setNotes("\t Depart by coach to Maastricht.");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        // Stop of in Reims
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 9, 30, 0);
        e.setTitle("Reims Stop");
        e.setNotes("\t En route stop off in Reism in France to have a look in the Notre-Dame Cathedral of Reims where the Kings of France were crowned. Time also for coffee and snack break.");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        // Depart from Reims
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 11, 0, 0);
        e.setTitle("Depart from Reims");
        e.setNotes("\t Depart from Reims");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Arrive in Bastogne
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 13, 0, 0);
        e.setTitle("Arrive in Bastogne");
        e.setNotes("\t Arrive in Bastogne in Belgium, the location of the most famous battle in World War II: The Battle of the Bulges. Stopp off here to take a look at the War Museum and the chance to have some lunch.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Depart Bastogne
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 14, 30, 0);
        e.setTitle("Depart Bastogne");
        e.setNotes("\t Depart from Bastogne");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Arrive in Margraten
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 16, 0, 0);
        e.setTitle("Margraten");
        e.setNotes("\t Arrive in Margraten and the Margraten Cemetary. The WWII Netherlands American Cemetery and Memorial, Europe's third largest war cemetery for unidentified soldiers who died in WWII. Visit the cemetery and the Brass Choir" +
                " perform taps.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Depart Margraten
        e = new Event((TimeZone.getTimeZone(sParisZone)), 2015, 4, 3, 17, 30, 0);
        e.setTitle("Depart Margraten");
        e.setNotes("\t Depart from Margraten");
        e.setDrawableId(R.drawable.sunset);
        mEvents.add(e);

        // Arrive in Maastricht
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 3, 18, 30, 0);
        e.setTitle("Maastricht");
        e.setNotes("\t Arrive in Maastricht and check into your hotel.");
        e.setDrawableId(R.drawable.sunset);
        mEvents.add(e);

        // Dinner
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 3, 20, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner this evening will be served in your hotel restaurant in Maastricht.\n\n Overnight at NH Hotel, Maastricht");
        e.setDrawableId(R.drawable.dinner_secondary);
        mEvents.add(e);

        // Day Five (4 April) -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Breakfast
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 8, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel");
        e.setDrawableId(R.drawable.food);
        mEvents.add(e);

        // Tour of Maastricht
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 10, 0, 0);
        e.setTitle("Tour of Maastricht");
        e.setNotes("\t Tour Ends at 3:30 pm. \n\n\t This morning depart on a guided coach and walking tour of Maastricht taking you all around the old city on one side of the Maas River and the contemporary new Maastricht on the other." +
                " Discover the ancient fortifications, seventeenth century townhouses the main square of the Vrijthof and the Markt. \n\nIndependent lunch in Maastricht");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        // Boat Trip
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 15, 0, 0);
        e.setTitle("Boat Trip");
        e.setNotes("\t Event ends at 6 pm.\n\n\t This afternoon take a short boat trip through the city passing the Bonnefanten Museum, the provincial government building and the slopes of the St. Pieters hill up to the Belgium border." +
                " You will have the chance to go ashore at the St.Pieters Hill for a guided tour in the Marlstone caves before returning by boat to Maastricht");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Dinner
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 4, 20, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner will be served at a restaurant in Maastricht. The evening is left free, so you can explore the tiny cobble stoned " +
                "streets of Maastricht and try some fires and mayonnaise, waffles, and chocolate. \n\nOvernight at NH Hotel, Maastricht");
        e.setDrawableId(R.drawable.dinner_secondary);
        mEvents.add(e);
        
        // Day Six (April 5th, Easter Sunday
        
        // Breakfast
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 7, 0, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Happy Easter Sunday! Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mEvents.add(e);

        // Depart for Aachen
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 8, 0, 0);
        e.setTitle("Depart to Aachen");
        e.setNotes("\t Depart for Aachen, Germany, located 30 minutes from Maastricht");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);
        
        // Guided tour of Aachen
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 8, 30, 0);
        e.setTitle("Tour of Aachen");
        e.setNotes("\t Take a guided walking tour of the city and visit Aachen Cathedral. The spa town of Aachen " +
                "was favoured residence of Charlemagne, and later the place of coronation of the German Kings.\n\n Independent lunch.");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);
        
        // Return to Maastricht
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 12, 30, 0);
        e.setTitle("Return to Maastricht");
        e.setNotes("Return to Maastricht");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);
        
        // Performance Streetshow
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 14, 0, 0);
        e.setTitle("Marching Performance");
        e.setNotes("Performance / Streetshow for the marching band around Maastricht Old Town including the Maarkt and " +
                "the Vrijthof.");
        e.setDrawableId(R.drawable.note);
        mEvents.add(e);

        // Technical rehearsal
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 16, 30, 0);
        e.setTitle("Technical Rehearsal");
        e.setNotes("\t Technical rehearsal for the concert this evening.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        // Dinner
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 18, 0, 0);
        e.setTitle("Dinner");
        e.setNotes("\t Dinner will be served at a restaurant in Maastricht near the Conservatorium");
        e.setDrawableId(R.drawable.dinner_secondary);
        mEvents.add(e);

        // Performance
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 5, 20, 0, 0);
        e.setTitle("Performance");
        e.setNotes("\t Performane for the Band and Orchestra at the Conservatorium - Maasticht Academy of Music. \n\n" +
                " Overnight at NH Hotel, Maastricht");
        e.setDrawableId(R.drawable.note);
        mEvents.add(e);

        // April 6th  Day 7??
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 6, 30, 0);
        e.setTitle("Breakfast");
        e.setNotes("\t Breakfast is served in your hotel.");
        e.setDrawableId(R.drawable.food);
        mEvents.add(e);

        // Depart this morning
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 7, 30, 0);
        e.setTitle("Depart for Paris");
        e.setNotes("\t Depart this morning by coach to Paris");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 10, 0, 0);
        e.setTitle("Arrive in Bruges");
        e.setNotes("\t Arrive in Bruges, Belgium, and take a guided walking tour around the fairy-tale " +
                "medieval city, to see Saint Salvator's Cathedral, the Belfry Tower, and the Old St John's " +
                "Hospital.");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 12, 0, 0);
        e.setTitle("Boat Ride");
        e.setNotes("\t Continue your tour of the city by taking a 30 min boat ride along the canals in Bruges " +
                "showing you the city from a different perspective and taking you to some of the hard to reach areas by " +
                "foot or bus.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        e =  new Event(TimeZone.getTimeZone(sParisZone) ,2015, 4, 6, 14, 0, 0);
        e.setTitle("Depart Bruges");
        e.setNotes("\t Depart Bruges. En route stop in Waterloo to view the magnificent monument to one of the most decisive " +
                "battles 100 years after Napolean lost to the Duke of Wellington.");
        e.setDrawableId(R.drawable.noon);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 18, 30, 0);
        e.setTitle("Arrive in Paris");
        e.setNotes("\t You will have the chance to check into your hotel and freshen up before dinner.");
        e.setDrawableId(R.drawable.sunset);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 6, 20, 0, 0);
        e.setTitle("Dinner + Eiffel Tower");
        e.setNotes("\t Farewell dinner will be served in a restaurant in Paris. After dinner visit the Eiffel tower, perhaps " +
                "Paris' most iconic landmark with a fantastic view out across Paris, a marvellous Au Revior to our trip abroad. " +
                "Afterwards stop to get some last minute souvenirs before returning to your hotel. \n\nOvernight at Novotel Paris Est, Paris.");
        e.setDrawableId(R.drawable.dinner_secondary);
        mEvents.add(e);

        // Last day -----------------------------------------------------------------------------------------------------------------
        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 6, 0, 0);
        e.setTitle("Breakfast Info");
        e.setNotes("Breakfast boxes will be prepared for breakfast this morning.");
        e.setDrawableId(R.drawable.food);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 6, 30, 0);
        e.setTitle("Flight 41 Leaves Hotel");
        e.setNotes("\t Flight 41 departures depart from the hotel.");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 7, 45, 0);
        e.setTitle("Flight 49 Leaves Hotel");
        e.setNotes("\t Flight 49 departures depart from the hotel.");
        e.setDrawableId(R.drawable.morn);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 10, 0, 0);
        e.setTitle("FLight 41 Departs");
        e.setNotes("\t Flight 41 departs from Aeroports de Paris. Set to arrive in O'Hare at 12:25 local." +
                "\n\n Flight Data:");
        e.setDrawableId(R.drawable.plane_takeoff);
        e.setIsFlightOne(false);
        e.setIsFlight(true);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone(sParisZone), 2015, 4, 7, 11, 20, 0);
        e.setTitle("Flight 49 Departs");
        e.setNotes("\t Flight 49 departs for Dallas Fort Worth. Set to arrive at 3:20 pm local." +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        e.setDrawableId(R.drawable.plane_takeoff);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 12, 25, 0);
        e.setTitle("Flight 41 Touchdown");
        e.setNotes("Flight 41 lands at O'Hare. Don't forget to transfer to fight 1611 in 4hr 35 min after landing (5pm local)" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(false);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 15, 20, 0);
        e.setTitle("Flight 49 Touchdown");
        e.setNotes("Flight 49 lands at Dallas Fort worth. Remember to transfer to flight 1388 in 2 hours after landing (5:20pm local)" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(true);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 17, 0, 0);
        e.setTitle("Transfer 1611 Departs");
        e.setNotes("Flight 1611 departs Chicago for San Fransisco International. Set to arrive at 7:26 local" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.flight_transfer);
        e.setIsFlightOne(false);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone("America/Chicago"), 2015, 4, 7, 17, 20, 0);
        e.setTitle("Transfer 1388 Departs");
        e.setNotes("FLight 1388 departs Dallas Fort Worth for San Fransisco International. Set to arrive at 7:16 local" +
                "\n" +
                "\n" +
                " Flight Data:");
        e.setDrawableId(R.drawable.flight_transfer);
        e.setIsFlight(true);
        e.setIsFlightOne(true);
        mEvents.add(e);

        // Landings
        e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 4, 7, 19, 16, 0);
        e.setTitle("Flight 1388 Touchdown");
        e.setNotes("Flight 1388 arrives at SFO." + "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(true);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 4, 7, 19, 56, 0);
        e.setTitle("Flight 1611 Touchdown");
        e.setNotes("Flight 1611 arrives at SFO." + "\n" +
                "\n" +
                " Flight Data:");
        e.setIsFlight(true);
        e.setDrawableId(R.drawable.plane_landing);
        e.setIsFlightOne(false);
        mEvents.add(e);




    }


    public boolean isDisableAllAlarms() {
        return disableAllAlarms;
    }

    public void setDisableAllAlarms(boolean disableAllAlarms) {
        this.disableAllAlarms = disableAllAlarms;
    }

    public boolean isUserset() {
        return isUserSet;
    }

    public void setIsUserset(boolean isUserset) {
        this.isUserSet = isUserset;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public boolean isOnlyShowRelevant() {
        return removeIrrelevant;
    }

    public void setOnlyShowRelevant(boolean onlyShowRelevant) {
        this.removeIrrelevant = onlyShowRelevant;
    }

    public boolean isRemovePastEvents() {
        return removePastEvents;
    }

    public void setRemovePastEvents(boolean removePastEvents) {
        this.removePastEvents = removePastEvents;
    }

    public boolean isAutoScroll() {
        return autoScroll;
    }

    public void setAutoScroll(boolean autoScroll) {
        this.autoScroll = autoScroll;
    }

    public boolean isRemoveIrrelevant() {
        return removeIrrelevant;
    }

    public void setRemoveIrrelevant(boolean removeIrrelevant) {
        this.removeIrrelevant = removeIrrelevant;
    }

    public static EventsLab get(Context c) {
        if (sEventsLab == null) {

            sEventsLab = new EventsLab(c.getApplicationContext());
        }

        return sEventsLab;

    }

    public int getFlightOne() {
        return mFlightOne;
    }

    public void setFlightOne(int flightOne) {
        mFlightOne = flightOne;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public Event getEvent(int position) {

        return mEvents.get(position);

    }

    public ArrayList<Event> getEvents() {return mEvents;}





}
