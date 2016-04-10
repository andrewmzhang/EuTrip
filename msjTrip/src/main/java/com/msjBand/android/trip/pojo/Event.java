package com.msjBand.android.trip.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class Event implements Parcelable, Comparable<Event> {

    public static final Parcelable.Creator<Event> CREATOR
            = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };


    private String id;
    private String title;
    private String desc;
    private String author;
    private DateTime date;
    private String timezone;
    private int groupRelevancy;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(author);

        DateTimeFormatter dtf = ISODateTimeFormat.dateTime();
        String dateStrng = dtf.print(date);
        dest.writeString(dateStrng);
        dest.writeString(timezone);
        dest.writeInt(groupRelevancy);

    }

    public Event(Parcel input) {
        id = input.readString();
        title = input.readString();
        desc = input.readString();
        author = input.readString();
        DateTimeFormatter dtf = ISODateTimeFormat.dateTime();
        date = dtf.parseDateTime(input.readString());
        timezone = input.readString();
        groupRelevancy = input.readInt();
    }

    public Event(String id, String title, String desc, String author, DateTime date, String timezone, int groupRelevancy) {
        this.author = author;
        this.date = date;
        this.desc = desc;
        this.groupRelevancy = groupRelevancy;
        this.id = id;
        this.timezone = timezone;
        this.title = title;
    }


    public Event(String id, String title, String desc, String author, String date, String timezone, int groupRelevancy) {
        this.author = author;
        this.date = DateTime.parse(date);
        this.desc = desc;
        this.groupRelevancy = groupRelevancy;
        this.id = id;
        this.timezone = timezone;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getGroupRelevancy() {
        return groupRelevancy;
    }

    public void setGroupRelevancy(int groupRelevancy) {
        this.groupRelevancy = groupRelevancy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int compareTo(Event another) {
        return this.getDate().compareTo(another.getDate());
    }
}
