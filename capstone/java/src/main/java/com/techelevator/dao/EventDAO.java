package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventDTO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface EventDAO {

    List<Event> listEvents();

    List<Event> listEventsById(int eventId);

    List<Event> listEventsByBandId(int bandId);

    boolean createEvent(Date eventDate, Time eventTime, String venue, int bandId);

    boolean updateEvent(EventDTO eventDTO);

    void deleteEvent(int eventId);




}
