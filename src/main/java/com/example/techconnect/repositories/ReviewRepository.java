package com.example.techconnect.repositories;

import com.example.techconnect.models.Interest;
import com.example.techconnect.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


}