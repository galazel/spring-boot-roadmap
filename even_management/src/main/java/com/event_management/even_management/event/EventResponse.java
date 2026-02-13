package com.event_management.even_management.event;

import com.event_management.even_management.user.client.ClientResponse;
import com.event_management.even_management.user.organizer.OrganizerResponse;

import java.time.LocalDateTime;
import java.util.List;

public record EventResponse(String title, String description, LocalDateTime eventDateTime, String location, int capacity, Event.EventType eventType, OrganizerResponse organizerResponse, List<ClientResponse> clientResponses) {
}
