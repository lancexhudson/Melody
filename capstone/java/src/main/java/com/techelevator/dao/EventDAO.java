package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDAO {

    List<Event> listEvents();

    List<Event> listEventsById(int eventId);

    List<Event> listEventsByBandId(int bandId);

    boolean createEvent(String eventDate, String eventTime, String venue, int bandId);




}
