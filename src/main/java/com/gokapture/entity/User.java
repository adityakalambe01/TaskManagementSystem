package com.gokapture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new LinkedList<>();
}
