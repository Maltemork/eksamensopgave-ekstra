package edu.semester3.eksamen.backend.project.dto;

import edu.semester3.eksamen.backend.project.enums.ResultTypes;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.model.Discipline;
import edu.semester3.eksamen.backend.project.model.Result;

public record ResultDTO(Integer id, Athlete athlete, Discipline discipline, double result, ResultTypes resultType, String date, String location, String competition, String placement, String comment) {
    public ResultDTO(Result result) {
        this(result.getId(), result.getAthlete(), result.getDiscipline(), result.getResult(), result.getResultType(), result.getDate(), result.getLocation(), result.getCompetition(), result.getPlacement(), result.getComment());
    }
}
