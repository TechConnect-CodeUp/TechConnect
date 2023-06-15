package com.example.techconnect.repositories;

import com.example.techconnect.models.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeesRepository extends JpaRepository<Attendees,Long> {






}
