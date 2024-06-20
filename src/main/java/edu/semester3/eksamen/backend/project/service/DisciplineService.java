package edu.semester3.eksamen.backend.project.service;

import edu.semester3.eksamen.backend.project.dto.DisciplineDTO;
import edu.semester3.eksamen.backend.project.dto.ResultDTO;
import edu.semester3.eksamen.backend.project.model.Discipline;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.repository.DisciplineRepository;
import edu.semester3.eksamen.backend.project.repository.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DisciplineService {
    private final ResultRepository resultRepository;
    private final DisciplineRepository disciplineRepository;
    public List<DisciplineDTO> getAllDisciplines() {
        List<Discipline> disciplines = disciplineRepository.findAll();
        return disciplines.stream().map(discipline -> new DisciplineDTO(discipline.getId(), discipline.getName(), discipline.getDescription())).toList();
    }

    public Discipline getById(int id) {
        return disciplineRepository.findById(id).orElse(null);
    }

    public List<Discipline> getByName(String name) {
        try {
            return disciplineRepository.findByNameContainingIgnoreCase(name);
        } catch (Exception e) {
            return null;
        }
    }

    public DisciplineDTO addDiscipline(Discipline newDiscipline) {
        disciplineRepository.save(newDiscipline);
        return new DisciplineDTO(newDiscipline.getId(), newDiscipline.getName(), newDiscipline.getDescription());
    }

    public Discipline updateDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
        return disciplineRepository.findById(discipline.getId()).orElse(null);
    }

    public void deleteDiscipline(int id) {
        disciplineRepository.deleteById(id);
    }

    public List<Result> getResults(int id) {
        return resultRepository.findAllByDisciplineId(id);
    }

    public void addResult(Result result) {
        resultRepository.save(result);
    }

    public void updateResult(Result result) {
        resultRepository.save(result);
    }

    public void deleteResult(int id) {
        resultRepository.deleteById(id);
    }

}
