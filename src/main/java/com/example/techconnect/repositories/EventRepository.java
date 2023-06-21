package com.example.techconnect.repositories;

import com.example.techconnect.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // This method helps us find events by UserID

    List<Event> findAllByHostId(long id);


}

