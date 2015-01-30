package com.msjBand.kraftscangradle.kraftscan;


import android.location.Address;
import android.location.Location;
import android.text.format.DateFormat;

import java.util.*;

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
    private GregorianCalendar mEndDate;

    public Event(TimeZone timeZone,int year, int month, int date, int hourOfDay, int minute, int second) {

        mId = UUID.randomUUID();
        mDate = new GregorianCalendar(timeZone, Locale.US);
        mDate.set(year, month, date, hourOfDay, minute, second);

    }

    public void setmEndDate(TimeZone timeZone, int year, int month, int date, int hourOfDay, int minute, int second) {
        mEndDate = new GregorianCalendar(timeZone, Locale.US);
        mEndDate.set(year, month, date, hourOfDay, minute,second);
    }

    public String getDateString() {
        return mDateFormat.format("EEEE, MMM d, yyyy", mDate).toString();
    }

    public String getTimeZone() {
        return  mTimeZone.toString();
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
