package edu.semester3.eksamen.backend.project.repository;

import edu.semester3.eksamen.backend.project.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    @Query("SELECT d FROM Discipline d WHERE lower(d.name) LIKE lower(concat('%', :name, '%'))")
    List<Discipline> findByNameContainingIgnoreCase(@Param("name") String name);

    Discipline findByNameIgnoreCase(String name);
}