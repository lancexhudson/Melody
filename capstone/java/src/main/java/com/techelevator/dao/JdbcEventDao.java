package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class JdbcEventDao implements EventDAO {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> listEvents() {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT event_id, event_date, event_time, venue, band_id FROM events";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
        Event event = mapRowToEvent(results);
        allEvents.add(event);
}
        return allEvents;

    }

    @Override
    public List<Event> listEventsById(int eventId) {
        return null;
    }

    @Override
    public List<Event> listEventsByBandId(int bandId) {
        List<Event> allBandEvents = new ArrayList<>();
        String sql = "SELECT event_id, event_date, event_time, venue, band_id FROM events WHERE band_id = ? ORDER BY event_date";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        while (results.next()){
            Event event = mapRowToEvent(results);
            allBandEvents.add(event);
        }
        return allBandEvents;

    }

    @Override
    public boolean createEvent(Date eventDate, Time eventTime, String venue, int bandId) {
        String sql = "INSERT INTO events (event_date, event_time, venue, band_id) VALUES (?,?,?,?) RETURNING event_id";
        int eventId = jdbcTemplate.queryForObject(sql, int.class, eventDate, eventTime, venue, bandId);
        return true;
    }

    @Override
    public boolean updateEvent(EventDTO eventDTO) {
        String sql = "UPDATE events SET event_date = ?, event_time = ?, venue = ?, band_id = ? WHERE event_id = ?;";
        jdbcTemplate.update(sql, eventDTO.getEventDate(), eventDTO.getEventTime(), eventDTO.getVenue(), eventDTO.getBandId(), eventDTO.getEventId());
        return true;
    }

    @Override
    public void deleteEvent(int eventId) {
        String sql = "DELETE FROM events WHERE event_id =?";
        jdbcTemplate.update(sql, eventId);
    }

    private Event mapRowToEvent(SqlRowSet rs){
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventDate(rs.getDate("event_date"));
        String theTime = String.valueOf(rs.getTime("event_time"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime newTime = LocalTime.parse(theTime, formatter);
        event.setEventTime(newTime);
        event.setVenue(rs.getString("venue"));
        event.setBandId(rs.getInt("band_id"));
        return event;

    }
}
