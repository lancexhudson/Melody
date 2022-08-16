package com.techelevator.model;

import java.util.Objects;

public class Event {

    private int eventId;
    private String eventDate;
    private String eventTime;
    private String venue;
    private int bandId;

    public Event() {
    }

    public Event(int eventId, String eventDate, String eventTime, String venue, int bandId) {
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.bandId = bandId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }
}
