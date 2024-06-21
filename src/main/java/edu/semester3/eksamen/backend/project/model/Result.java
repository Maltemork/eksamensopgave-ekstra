package edu.semester3.eksamen.backend.project.model;


import edu.semester3.eksamen.backend.project.dto.ResultDTO;
import edu.semester3.eksamen.backend.project.dto.ResultWithDisciplineIntegerDTO;
import edu.semester3.eksamen.backend.project.enums.ResultTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Athlete athlete;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Discipline discipline;

    private double result;
    private ResultTypes resultType;

    private String date;

    private String location;

    private String competition;

    private String placement;

    private String comment;

    public Result(Athlete athlete, Discipline discipline, double result, ResultTypes resultType, String date) {
        this.athlete = athlete;
        this.discipline = discipline;
        this.result = result;
        this.resultType = resultType;
        this.date = date;
    }

    public Result(ResultDTO resultDTO) {
        this.athlete = resultDTO.athlete();
        this.discipline = resultDTO.discipline();
        this.result = resultDTO.result();
        this.resultType = resultDTO.resultType();
        this.date = resultDTO.date();
        this.location = resultDTO.location();
        this.competition = resultDTO.competition();
        this.placement = resultDTO.placement();
        this.comment = resultDTO.comment();
    }

    public Result(ResultWithDisciplineIntegerDTO resultDTO, Discipline discipline) {
        this.athlete = resultDTO.athlete();
        this.discipline = discipline;
        this.result = resultDTO.result();
        this.resultType = resultDTO.resultType();
        this.date = resultDTO.date();
        this.location = resultDTO.location();
        this.competition = resultDTO.competition();
        this.placement = resultDTO.placement();
        this.comment = resultDTO.comment();
    }
}
