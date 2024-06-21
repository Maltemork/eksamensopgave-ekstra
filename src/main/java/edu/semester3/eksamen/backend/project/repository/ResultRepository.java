package edu.semester3.eksamen.backend.project.repository;
import edu.semester3.eksamen.backend.project.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result findByAthleteIdAndDisciplineId(int athleteId, int disciplineId);
    void deleteAllByAthleteId(int athleteId);
    List<Result> findAllByAthleteId(int athleteId);
    List<Result> findAllByDisciplineId(int disciplineId);
    void deleteById(int id);
}
