package edu.semester3.eksamen.backend.project.dto;

import edu.semester3.eksamen.backend.project.enums.Gender;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.model.Discipline;

import java.util.Set;

public record AthleteDTO(
        Integer id,
        String name,
        Gender gender,
        String club,
        Integer age,
        Double weight,
        Double height,
        String email,
        String phone,
        String address,
        String city,
        String postalCode,
        String comment,
        Set<Discipline> disciplines) {

    public AthleteDTO(
            Integer id,
            String name,
            Gender gender,
            String club) {
        this(
                id,
                name,
                gender,
                club,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public AthleteDTO(Athlete athlete) {
        this(
                athlete.getId(),
                athlete.getName(),
                athlete.getGender(),
                athlete.getClub(),
                athlete.getAge(),
                athlete.getWeight(),
                athlete.getHeight(),
                athlete.getEmail(),
                athlete.getPhone(),
                athlete.getAddress(),
                athlete.getCity(),
                athlete.getPostalCode(),
                athlete.getComment(),
                athlete.getDisciplines());
    }
}