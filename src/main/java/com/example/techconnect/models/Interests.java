package com.example.techconnect.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "interests")


public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String Interest;

//    @OneToMany(mappedBy = "interest", cascade = CascadeType.ALL)
//    private List<Event> events;


}
