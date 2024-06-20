package edu.semester3.eksamen.backend.project.dto;

import edu.semester3.eksamen.backend.project.enums.Gender;

public record ParticipantDTO(Integer id, String name, String club, Gender gender) {

}
