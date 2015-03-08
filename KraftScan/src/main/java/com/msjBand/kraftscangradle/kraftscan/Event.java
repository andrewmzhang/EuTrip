package com.msjBand.kraftscangradle.kraftscan;


import java.sql.Time;
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
    private boolean misFlightOne;
    private boolean isFlight;
    private boolean isDepart;
    private int drawableId;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public boolean determineIsOccured() {
        return false;
    }

    public boolean isDepart() {
        return isDepart;
    }

    public void setDepart(boolean isDepart) {
        this.isDepart = isDepart;
    }

    public Event(TimeZone timeZone, int year, int month, int date, int hourOfDay, int minute, int second) {
        isFlight = false;
        misFlightOne = false;
        mTimeZone = timeZone;
        mId = UUID.randomUUID();
        mOccurred = false;
        drawableId = android.R.drawable.star_big_on;
        mDate = new GregorianCalendar(timeZone, Locale.US);
        mDate.set(year, month - 1, date, hourOfDay, minute, second);
        fmt = new SimpleDateFormat((
                "hh:mm:ss a EEEE, MMM d, yyyy"));
    }


    public void setIsFlight(boolean value){
        isFlight = value;
    }
    public boolean getIsFlight(){
        return isFlight;
    }

    public boolean getIsFlightOne(){
        return misFlightOne;

    }
    public void setIsFlightOne(boolean value){
            misFlightOne = value;

    }
    public String getLocalDate() {
        fmt.setTimeZone(mTimeZone);
        fmt = new SimpleDateFormat("EEE MMM d");
        return fmt.format(mDate.getTime());
    }

    public String getLocalTime() {
        fmt.setTimeZone(mTimeZone);
        fmt = new SimpleDateFormat("hh:mm:ss a");
        return fmt.format(mDate.getTime());

    }

    public String getGMT() {
        TimeZone obj = TimeZone.getTimeZone("GMT");
        fmt.setTimeZone(obj);
        return fmt.format(mDate.getTime());
    }

    public String getETA() {

        if(isOccurred())
            return "Passed";

        Calendar now = Calendar.getInstance();
        long end = mDate.getTimeInMillis();
        long start = now.getTimeInMillis();
        long interval = end - start;

        if (interval < 0) {
            setOccurred(true);
            return "Passed";
        }
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
        secText = seconds + "s";
        if (seconds < 10) {secText = "0" + secText;}

        return (dayText + hourText + minText + secText);

    }


    public String getTimeZone() {
        fmt = new SimpleDateFormat("zzz");
        return fmt.format(mDate.getTime());
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
