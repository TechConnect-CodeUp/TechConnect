package com.example.techconnect.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "interests")


public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String interest;

    // Entity Relationships

    @OneToMany(mappedBy = "interest")

    private List<UserInterest> userInterests = new ArrayList<>();

    @OneToMany(mappedBy = "interest")

    private List<Event> events = new ArrayList<>();


}
