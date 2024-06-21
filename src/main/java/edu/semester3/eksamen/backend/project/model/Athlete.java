package edu.semester3.eksamen.backend.project.model;

import edu.semester3.eksamen.backend.project.dto.AthleteDTO;
import edu.semester3.eksamen.backend.project.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    public Athlete(String name, Gender gender, Integer age, String club, Set<Discipline> disciplines) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.club = club;
        this.disciplines = disciplines;
    }

    public Athlete(AthleteDTO athleteDTO) {
        this.name = athleteDTO.name();
        this.gender = athleteDTO.gender();
        this.club = athleteDTO.club();
        this.age = athleteDTO.age();
        this.weight = athleteDTO.weight();
        this.height = athleteDTO.height();
        this.email = athleteDTO.email();
        this.phone = athleteDTO.phone();
        this.address = athleteDTO.address();
        this.city = athleteDTO.city();
        this.postalCode = athleteDTO.postalCode();
        this.comment = athleteDTO.comment();
    }
}
