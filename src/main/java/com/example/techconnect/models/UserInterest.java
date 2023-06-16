package com.example.techconnect.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_interest")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInterest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "interest_id")
//    private Interests interest;

}


