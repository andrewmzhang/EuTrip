package com.msjBand.android.trip;

import java.util.Date;

public class Event {

    private String id;
    private String title;
    private String desc;
    private String author;
    private Date  date;
    private String timezone;
    private int groupRelevancy;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
