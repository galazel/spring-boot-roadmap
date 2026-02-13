package com.event_management.even_management.event;

import com.event_management.even_management.user.User;
import com.event_management.even_management.user.UserResponse;
import com.event_management.even_management.user.client.ClientRepository;
import com.event_management.even_management.user.client.ClientResponse;
import com.event_management.even_management.user.organizer.Organizer;
import com.event_management.even_management.user.organizer.OrganizerRepository;
import com.event_management.even_management.user.organizer.OrganizerResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImp implements EventService {

    private final OrganizerRepository organizerRepository;
    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository,  OrganizerRepository organizerRepository, ClientRepository clientRepository) {
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.clientRepository = clientRepository;
    }
    @Override
    public void addEvent(EventRequest requestUser) {
        Event event = Event.builder()
                .title(requestUser.title())
                .description(requestUser.description())
                .eventDateTime(requestUser.eventDateTime())
                .location(requestUser.location())
                .capacity(requestUser.capacity())
                .eventType(requestUser.eventType())
                .build();
        Organizer organizer = organizerRepository.findById(requestUser.organizerId()).orElse(null);
        event.setOrganizer(organizer);
        event.setClients(null);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(int id) {

    }

    @Override
    public EventResponse getUser(int id) {
        return null;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.getEventWithClients(1);
        List<OrganizerResponses> organizer =
        return events.stream().map(event ->
                new EventResponse(event.getTitle(), event.getDescription(), event.getEventDateTime(), event.getLocation(), event.getCapacity(), event.getEventType(), new OrganizerResponse(new UserResponse(event.getOrganizer().getUser().getLastname(),event.getOrganizer().getUser().getFirstname(), event.getOrganizer().getUser().getEmail(),event.getOrganizer().getUser().getContact(),event.getOrganizer().getUser().getUsername(), event.getOrganizer().getUser().getRole(),null)),event.getClients())
                ).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<EventResponse> getAllClientsEvent(int id) {
        return List.of();
    }

    @Override
    public List<EventResponse> getAllOrganizersEvent(int id) {
        return List.of();
    }
}
