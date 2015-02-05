package com.msjBand.kraftscangradle.kraftscan;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Event {

    private UUID mId;
    private String mTitle;
    private GregorianCalendar mDate;
    private boolean mOccurred;
    private DateFormat mDateFormat;
    private String mAddress;
    private String mNotes;
    private String mConcern;
    private TimeZone mTimeZone;
    private SimpleDateFormat fmt;

    public Event(TimeZone timeZone, int year, int month, int date, int hourOfDay, int minute, int second) {

        mId = UUID.randomUUID();
        mDate = new GregorianCalendar(timeZone, Locale.US);
        mDate.set(year, month - 1, date, hourOfDay, minute, second);
        fmt = new SimpleDateFormat(("EEEE, MMM d, yyyy, hh:mm:ss a zzz"));

    }


    public String getLocal() {
        return fmt.format(mDate.getTime());
    }

    public String getGMT() {
        TimeZone obj = TimeZone.getTimeZone("GMT");
        fmt.setTimeZone(obj);
        return fmt.format(mDate.getTime());
    }

    public String getETA() {
        Calendar now = Calendar.getInstance();
        long end = mDate.getTimeInMillis();
        long start = now.getTimeInMillis();
        long interval = end - start;

        if (interval < 0)
            return "Passed";

        String dayText = "";
        String hourText = "";
        String minText = "";
        String secText = "";

        int day = (int) (interval / (1000 * 60 * 60 * 24));
        int hours = (int) (interval / (1000 * 60 * 60)) - (day * 24);
        int minutes = (int) (interval/ (1000 * 60)) - (day * 24 * 60) - (hours * 60);
        int seconds = (int) (interval/ (1000)) - (day * 24 * 60 *60) - (hours * 60 *60) - (minutes * 60);
        if (day != 0) {dayText = day + "d ";}
        if (hours != 0) {hourText = hours + "h ";}
        if (minutes != 0) {minText = minutes + "m ";}
        if (seconds != 0) {secText = seconds + "s";}

        return (dayText + hourText + minText + secText);

    }


    public String getTimeZone() {
        return mTimeZone.toString();
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public boolean isOccurred() {
        return mOccurred;
    }

    public void setOccurred(boolean occurred) {
        mOccurred = occurred;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
