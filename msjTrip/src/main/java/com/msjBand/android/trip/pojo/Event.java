package com.msjBand.android.trip.pojo;

/**
 * Created by porter on 10/4/16.
 */
public class Event {

    public String author;
    public long date;
    public String desc;
    public long groupRelevancy;
    public String id;
    public String picture;
    public String timezone;
    public String title;
    public long pending;
    public boolean seperator;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getGroupRelevancy() {
        return groupRelevancy;
    }

    public void setGroupRelevancy(Long groupRelevancy) {
        this.groupRelevancy = groupRelevancy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public long getPending() {
        return pending;
    }

    public void setPending(long pending) {
        this.pending = pending;
    }

    public boolean isSeperator() {
        return seperator;
    }

    public void setSeperator(boolean seperator) {
        this.seperator = seperator;
    }
}
