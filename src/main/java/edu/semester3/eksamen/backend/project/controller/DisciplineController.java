package edu.semester3.eksamen.backend.project.controller;

import edu.semester3.eksamen.backend.project.dto.DisciplineDTO;
import edu.semester3.eksamen.backend.project.dto.ResultDTO;
import edu.semester3.eksamen.backend.project.model.Discipline;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.service.DisciplineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
    DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @Operation(summary = "Get all disciplines", description = "Get a list of all disciplines currently in the database.")
    @GetMapping
    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @Operation(summary = "Get specific discipline", description = "Get a specific discipline.")
    @GetMapping("/{id}")
    public DisciplineDTO getDiscipline(@PathVariable int id) {
        Discipline foundDiscipline = disciplineService.getById(id);
        return new DisciplineDTO(foundDiscipline.getId(), foundDiscipline.getName(), foundDiscipline.getDescription());
    }

    @Operation(summary = "Get discipline by id", description = "Get a discipline by its id.")
    @GetMapping("/find-by-id/{id}")
    public DisciplineDTO getDisciplineById(@PathVariable int id) {
        Discipline foundDiscipline = disciplineService.getById(id);
        return new DisciplineDTO(foundDiscipline.getId(), foundDiscipline.getName(), foundDiscipline.getDescription());
    }

    @Operation(summary = "Get discipline by name", description = "Get a discipline by its name.")
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<DisciplineDTO>> getDisciplineByName(@PathVariable String name) {
        List<Discipline> foundDisciplines = disciplineService.getByName(name.replace("-", " "));
        if (foundDisciplines.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<DisciplineDTO> disciplineDTOs = foundDisciplines.stream().map(DisciplineDTO::new).toList();
        return ResponseEntity.ok(disciplineDTOs);
    }

    @Operation(summary = "Create discipline", description = "Create a new discipline.")
    @PostMapping
    public ResponseEntity<DisciplineDTO> createDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        Discipline newDiscipline = new Discipline(disciplineDTO.name(), disciplineDTO.description());
        DisciplineDTO createdDiscipline = disciplineService.addDiscipline(newDiscipline);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscipline);
    }

    @Operation(summary = "Update discipline", description = "Update a discipline.")
    @PutMapping
    public ResponseEntity<DisciplineDTO> updateDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        try {
            Discipline updatedDiscipline = disciplineService.updateDiscipline(new Discipline(disciplineDTO.id(), disciplineDTO.name(), disciplineDTO.description()));
            return ResponseEntity.ok(new DisciplineDTO(updatedDiscipline.getId(), updatedDiscipline.getName(), updatedDiscipline.getDescription()));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Get results from a discipline", description = "Get results from a discipline.")
    @GetMapping("/{id}/results")
    public ResponseEntity<List<ResultDTO>> getResults(@PathVariable int id) {
        List<Result> foundResults = disciplineService.getResults(id);
        if (foundResults.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<ResultDTO> resultDTOs = foundResults.stream().map(ResultDTO::new).toList();
        return ResponseEntity.ok(resultDTOs);
    }

    @Operation(summary = "Delete discipline", description = "Delete a discipline.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDiscipline(@PathVariable int id) {
        try {
            disciplineService.deleteDiscipline(id);
            return ResponseEntity.ok("Discipline successfully removed.");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Add result to discipline", description = "Add a result to a discipline.")
    @PostMapping("/{id}/results")
    public ResponseEntity addResult(@PathVariable int id, @RequestBody ResultDTO resultDTO) {
        try {
            disciplineService.addResult(new Result(resultDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Update result for discipline", description = "Update a result for a discipline.")
    @PutMapping("/{id}/results")
    public ResponseEntity updateResult(@PathVariable int id, @RequestBody ResultDTO resultDTO) {
        try {
            disciplineService.updateResult(new Result(resultDTO));
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Remove result from discipline", description = "Remove a result from a discipline.")
    @DeleteMapping("/{id}/results/{resultId}")
    public ResponseEntity deleteResult(@PathVariable int id, @PathVariable int resultId) {
        try {
            disciplineService.deleteResult(resultId);
            return ResponseEntity.ok("Result successfully removed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
