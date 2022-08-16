package com.techelevator.controller;

import com.techelevator.dao.EventDAO;
import com.techelevator.model.Event;
import com.techelevator.model.EventDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    private EventDAO eventDAO;

    public EventController(EventDAO eventDAO){
        this.eventDAO = eventDAO;
    }

    @RequestMapping(path ="/events", method = RequestMethod.GET)
    public List<Event> listAllEvents(){
        return eventDAO.listEvents();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/events/register", method = RequestMethod.POST)
    public void createEvent(@RequestBody EventDTO eventDTO){
        eventDAO.createEvent(eventDTO.getEventDate(), eventDTO.getEventTime(), eventDTO.getVenue(), eventDTO.getBandId());
    }

    @RequestMapping(path = )


}
