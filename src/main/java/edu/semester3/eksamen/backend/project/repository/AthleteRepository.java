package edu.semester3.eksamen.backend.project.repository;
import edu.semester3.eksamen.backend.project.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    Participant findByName(String name);
}