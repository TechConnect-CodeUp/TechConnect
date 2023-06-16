package com.example.techconnect.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;


    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;


}
