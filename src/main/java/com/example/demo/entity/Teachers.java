package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Maciej Okliński
 * Encje klasy Teachers
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class Teachers  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 40, min=3, message = "criteria not met")
    private String name;
    private String surName;
    @Min(value = 18)
    private int age;
    @Email
    private String email;
    private String subject;
    @ManyToMany
    private Set<Students> followedStreams;

    public Teachers(String name, String surName, int age, String email, String subject, Set<Students> followedStreams) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.email = email;
        this.subject = subject;
        this.followedStreams=followedStreams;
    }

    public void followStream(Students students){
        followedStreams.add(students);
    }
}
