package com.isproj2.regainmobile.model;

public class GreenZone {
    private String title;
    private String dateTime;
    private String link;
    private String summary;

    // Constructors
    public GreenZone() {}

    public GreenZone(String title, String dateTime, String link, String summary) {
        this.title = title;
        this.dateTime = dateTime;
        this.link = link;
        this.summary = summary;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}