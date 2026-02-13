package com.event_management.even_management.event;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events/")
public class EventController {

    private final EventService eventService;
    public EventController(EventService eventService)
    {
        this.eventService = eventService;
    }
    @GetMapping("get-all")
    public List<Event> getAllEvents()
    {
        return eventService.getAllEvents();
    }
    @PostMapping("add")
    public void addEvent(@RequestBody EventRequest eventRequest)
    {
        eventService.addEvent(eventRequest);
    }
}
