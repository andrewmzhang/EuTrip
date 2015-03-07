package com.msjBand.kraftscangradle.kraftscan;


import android.content.Context;

import java.util.ArrayList;
import java.util.TimeZone;


public class EventsLab {

    private static EventsLab sEventsLab;
    private String studentName;
    private Context mAppContext;

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


    private int mFlightOne = -1;   //-1 is unknown, 0 is not on Flight One (2283), 1 is on Flight One

    private EventsLab(Context appContext) {

        mAppContext = appContext;
        mEvents = new ArrayList<Event>();

        // Mandatory Meeting
        Event e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 1, 29, 15, 30, 0);
        e.setTitle("Mandatory Meeting");
        e.setOccurred(false);
        mEvents.add(e);

        // Arrival @ 5am
        e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 5, 0, 0);
        e.setTitle("Airport SFO");
        e.setNotes("Arrive at SFO American Airlines Domestic Departures Terminal 2");
        e.setOccurred(false);
        mEvents.add(e);

        // Departure of Flight 2283
        e =  new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 8, 35, 0);
        e.setTitle("Flight 2283 Departure");
        e.setNotes("\tSan Fransisco Airport to Dallas Forth Worth International Airport. Remember to transfer to flight 048 at 15:05 local time. \n\nFlight 2283 Roster:\n"
                + "\tMonica Kraft\n\tWenhan Fang\n\tSathvik Vivek\n\tYu-Cheng Chou\n\tYu-Ting Chou\n\tJemmy Zhou\n\tRaymong Yin\n\tJianXiang Liu\n\tNikhil Pathania\n\tAllison Xu\n\tCharles Xu\n\tSharleen Zhou" +
                "\n\n\tChuck Goodman\n\tAnne Riley\n\tRoy Ho\n\tKai Goodman\n\tBrandon Chen\n\tStanley Zhang\n\tTiernan Barrie\n\tMichelle Dalarossa\n\tRushil Chakrabarty\n\tHelen Chang" +
                "\n\n\tSandra Kaye\n\tAllison Chan\n\tApril Huang\n\tAlan Liu\n\tAkhil Reddy\n\tHetav Gore\n\tNishir Shelat\n\tWilliam Ho\n\tGraciela Friend\n\tSamutratankul Mujarin" +
                "\n\n\tHoward Mai\n\tGrant Fu\n\tMackenzie Lim\n\tSarah Chong\n\tTrevor Wu\n\tSai Goli\n\tWilliam Kim\n\tNina Vasan\n\tDorothy Du\n\tYu Shu\n\tQiFei Teng" +
                "\n\n\tDorothy Griswold\n\tAndrew Zhang\n\tWaylon Peng\n\tRahul Chatwani\n\tNathon Ma\n\tAmanda Wang\n\tTCJ Cheng\n\tKevin Zhu\n\tKevin Yu\n\tAlbert Stanley\n\tNingNing Zhou" +
                "\n\n\tJanet Baker\n\tSean Li\n\tDylan Lim\n\tJelena Lee\n\tAlison Chen\n\tShagun Srivastava\n\tClaire Yung\n\tJoshua Zeng\n\tAnthony Herget\n\tRamona Gonzalez\n\tMarcos Jung\n\tRoselyn Jung" +
                "\n\n\tJean Jea\n\tEmily Xu\n\tAngela Yang\n\tMallika Chatterjee\n\tTanushri Sundar\n\tKevin Zhang\n\tIrene Yin\n\tVivasvan Vykunta\n\tZachary Kekoa\n\tAlexia Kekoa\n\tHerkea Jea" +
                "\n\n\tSamuel Chou\n\tAneri Parikh\n\tSeona Patel\n\tKenneth Leung\n\tAlex Yin\n\tRohan Nair\n\tShiva Ramani\n\tNicole Hsu\n\tDerek Xia\n\tAkshita Gandra\n\tTeresa Hsu");
        e.setOccurred(false);
        e.setIsFlightOne(true);
        e.setIsFlight(true);
        mEvents.add(e);

        e = new Event(TimeZone.getTimeZone("America/Los_Angeles"), 2015, 3, 30, 7, 45, 0);
        e.setTitle("Flight 2411 Departure");
        e.setNotes("\tSan Fransisco Airport to Dallas Forth Worth International Airport. Remember to transfer to flight 042 at 15:10 local time. \n\nFlight 2411 Roster: \n"
                + "\tMark Aherns\n\tEvan Lwin\n\tJoyce Wang\n\tJulie ChenYi Wang\n\tAimee Xu\n\tEmma Chang\n\tClaire Wu\n\tApoorva Prakash\n\tKosh Kumar\n\tHsien-Hsiang Lin\n" +
                        "\n\tHarrison Cheng\n\tKelly Shi\n\tSavana Wang\n\tJustin Nguyen\n\tFendy Gao\n\tSerena Young\n\tMarianne Rara" +
                        "\n\n\tAnn Kwan\n\tJonathan Bright Li\n\tStephanie Bi\n\tAshley Chen\n\tJennifer Wei\n\tNikhita Ganesh\n\tMaya Sudarsan\n\tAaron Zhang\n\tJerry Lin\n\tLi Diao" +
                        "\n\n\tClaudia Fort\n\tAnna Pi\n\tVivian Ross\n\tLeon Ming\n\tVarsha Rajagopalan\n\tWilliam Zeng\n\tDonna li\n\tVineeth Yeevani\n\tKiran Raja\n\tRaja Jayakumar" +
                        "\n\n\tMargaret Taylor\n\tNivedha Karthikeyan\n\tKate Lin\n\tAnjali Joseph (Mitter)\nJolene Tsai\n\tSabrina Liu\n\tJoyce Pi\n\tAn Tran\n\tKhang Tran\n\tThach Ngo Tran" +
                        "\n\n\tLesley Wilhite\n\tKunal Agarwal\n\tCory Lam\n\tColby Huang\n\tBrian Zhao\n\tAlexander Chen\n\tSai Dwibhashyam\n\tAshank Verma\n\tVictor He\n\tLi Deng" +
                        "\n\n\tYue Zhao\n\tSara Tsai\n\tJessica Eng\n\tDouglas Lam\n\tAngus Fung\n\tWilliam Luk\n\tKathleen Zhou\n\tBrandon Lu\n\tDiana Minyi Lu" +
                        "\n\n\tRaymond Mendonca\n\tJessica Mao\n\tDelaine Rogers\n\tAnnie Xu\n\tLyann Choi\n\tRushalee Nirodi\n\tMyra Awan\n\tDanice Long\n\tDiana Gia Tran"

        );
        e.setOccurred(false);
        e.setIsFlight(false);
        e.setIsFlight(true);
        mEvents.add(e);




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

        if ((Flight2283Roster.toLowerCase()).contains(studentName.toLowerCase()))
            mFlightOne = 1;

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
