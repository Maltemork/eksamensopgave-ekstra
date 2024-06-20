package edu.semester3.eksamen.backend.project.model;

import edu.semester3.eksamen.backend.project.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "athletes")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Gender gender;
    private String club;
    private int age;
    private double weight;
    private double height;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String postalCode;
    private String comment;


    @ManyToMany
    private Set<Discipline> disciplines;

    public Athlete(String name, Gender gender, String club) {
        this.name = name;
        this.gender = gender;
        this.club = club;
    }

    public Athlete(String name, Gender gender, String club, Set<Discipline> disciplines) {
        this.name = name;
        this.gender = gender;
        this.club = club;
        this.disciplines = disciplines;
    }
}
