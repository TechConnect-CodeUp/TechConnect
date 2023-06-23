package com.example.techconnect.repositories;

import com.example.techconnect.models.Attendee;
import com.example.techconnect.models.Event;
import com.example.techconnect.models.Interest;
import com.example.techconnect.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review>findAllByEventEventId(long id);
}