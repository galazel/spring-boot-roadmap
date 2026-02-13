package com.event_management.even_management.user.client;

import com.event_management.even_management.event.EventResponse;
import com.event_management.even_management.user.UserResponse;

import java.util.List;

public record ClientResponse(UserResponse userResponse, List<EventResponse> participatedEvents) {
}
