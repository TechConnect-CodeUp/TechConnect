package com.example.techconnect.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "interests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String Interest;


    @OneToMany
    private List<Event> events;


}
