package edu.semester3.eksamen.backend.project.dto;

import edu.semester3.eksamen.backend.project.enums.Gender;

public record AthleteDTO(Integer id, String name,Gender gender, String club) {

}
