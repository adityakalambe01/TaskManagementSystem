package com.gokapture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private String status;

    private String priority;

    private LocalDate due_date;

    private LocalDate created_at;

    private LocalDate updated_at;

    @ManyToOne
    @ToString.Exclude
    private User user;
}
