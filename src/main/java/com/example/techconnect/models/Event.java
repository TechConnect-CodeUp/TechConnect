package com.example.techconnect.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "events")

public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EventId;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;


    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String location;

    @Column(name = "date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    // Entity Relationship diagram


    @ManyToOne
    @JoinColumn(name="host_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="interest_id")
    private Interest interest;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Attendee>attendees = new ArrayList<>();

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Review>reviews = new ArrayList<>();







}
