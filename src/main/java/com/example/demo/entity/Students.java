package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Maciej Okliński
 * Encje klasy Students
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class Students implements Iterable<Students> {

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
    private String fieldOfStudy;
    @ManyToMany
    private Set<Teachers> followers;

    public Students(String name, String surName, int age, String email, String fieldOfStudy, Set<Teachers> followers) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.email = email;
        this.fieldOfStudy = fieldOfStudy;
        this.followers = followers;
    }
    public void addTeachers(Teachers teachers){
        followers.add(teachers);
    }

    @Override
    public Iterator<Students> iterator() {
        return null;
    }
}
