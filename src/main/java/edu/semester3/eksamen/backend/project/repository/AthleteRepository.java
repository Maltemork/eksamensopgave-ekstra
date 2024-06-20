package edu.semester3.eksamen.backend.project.repository;

import edu.semester3.eksamen.backend.project.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
    List<Athlete> findByNameContainingIgnoreCase(String name);
    Athlete findByNameIgnoreCase(String name);
}