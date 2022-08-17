package com.techelevator.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Event {

    private int eventId;
    private Date eventDate;
    private LocalTime eventTime;
    private String venue;
    private int bandId;

    public Event() {
    }

    public Event(int eventId, Date eventDate, LocalTime eventTime, String venue, int bandId) {
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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
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
