package edu.semester3.eksamen.backend.project.model;

import edu.semester3.eksamen.backend.project.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Gender gender;
    private String club;

    @ManyToMany
    private Set<Discipline> disciplines;

}
