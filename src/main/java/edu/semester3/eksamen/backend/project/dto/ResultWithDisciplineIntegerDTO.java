package edu.semester3.eksamen.backend.project.dto;

import edu.semester3.eksamen.backend.project.enums.ResultTypes;
import edu.semester3.eksamen.backend.project.model.Athlete;

public record ResultWithDisciplineIntegerDTO(Integer id, Athlete athlete, Integer disciplineId, double result, ResultTypes resultType, String date, String location, String competition, String placement, String comment) {

}
