package com.example.techconnect.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "attendees")
public class Attendees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendeeId;


    // We will uncomment this line of after a successfull merge


//    @ManyToOne
//    @JoinColumn(name = "event_id")
//    private Event event;
//
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;











}
