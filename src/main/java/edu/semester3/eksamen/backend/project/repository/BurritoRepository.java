package edu.semester3.eksamen.backend.project.repository;

import edu.semester3.eksamen.backend.project.model.Burrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BurritoRepository extends JpaRepository<Burrito, Integer> {
    Burrito findByName(String name);
}
