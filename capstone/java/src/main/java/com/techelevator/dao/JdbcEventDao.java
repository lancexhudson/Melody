package com.techelevator.dao;

import com.techelevator.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        String sql = "SELECT event_id, event_date, event_time, venue, band_id FROM events WHERE band_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        while (results.next()){
            Event event = mapRowToEvent(results);
            allBandEvents.add(event);
        }
        return allBandEvents;

    }

    @Override
    public boolean createEvent(String eventDate, String eventTime, String venue, int bandId) {
        String sql = "INSERT INTO events (event_date, event_time, venue, band_id) VALUES (?,?,?,?) RETURNING event_id";
        int eventId = jdbcTemplate.queryForObject(sql, int.class, eventDate, eventTime, venue, bandId);
        return true;
    }

    private Event mapRowToEvent(SqlRowSet rs){
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventDate(rs.getString("event_date"));
        event.setEventTime(rs.getString("event_time"));
        event.setVenue(rs.getString("venue"));
        event.setBandId(rs.getInt("band_id"));
        return event;

    }
}
