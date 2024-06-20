package edu.semester3.eksamen.backend.project.model;

import edu.semester3.eksamen.backend.project.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    // @ManyToMany
    // private Set<Discipline> disciplines;

    public Athlete(String name, Gender gender, String club) {
        this.name = name;
        this.gender = gender;
        this.club = club;
    }
}
