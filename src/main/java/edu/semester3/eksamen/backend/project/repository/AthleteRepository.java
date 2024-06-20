package edu.semester3.eksamen.backend.project.repository;
import edu.semester3.eksamen.backend.project.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {

    @Override
    List<Athlete> findAll();
    Athlete findByName(String name);
}