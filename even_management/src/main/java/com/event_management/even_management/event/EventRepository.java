package com.event_management.even_management.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.event_management.even_management.user.organizer.OrganizerResponse;
import com.event_management.even_management.event.EventResponse;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("""
        SELECT DISTINCT e FROM Event e
        LEFT JOIN FETCH e.clients
        LEFT JOIN FETCH e.organizer o
        WHERE e.organizer.id = :organizerId
    """)
    List<Event> getEventWithClients(@Param("organizerId") Integer organizerId);


}
