package com.event_management.even_management.event;

import java.util.List;

public interface EventService {
    void addEvent(EventRequest requestUser);
    void deleteEvent(int id);
    EventResponse getUser(int id);
    List<EventResponse> getAllEvents();
    List<EventResponse> getAllClientsEvent(int id);
    List<EventResponse> getAllOrganizersEvent(int id);
}
