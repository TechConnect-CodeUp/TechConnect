package com.example.techconnect.repositories;

import com.example.techconnect.models.Interests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestsRepository extends JpaRepository<Interests,Long> {


}
