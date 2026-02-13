package com.event_management.even_management.event;

import com.event_management.even_management.user.Client;
import com.event_management.even_management.user.Organizer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Event title is required")
    @Size(max = 100, message = "Event title must be at most 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    @NotNull(message = "Event date and time is required")
    private LocalDateTime eventDateTime;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location must be at most 200 characters")
    private String location;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Event type is required")
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "joinedEvents")
    private Set<Client> clients;

    public enum EventType {
        CONFERENCE, WORKSHOP, MEETUP, PARTY
    }
}
