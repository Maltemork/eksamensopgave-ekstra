package edu.semester3.eksamen.backend.project.controller;

import edu.semester3.eksamen.backend.project.dto.AthleteDTO;
import edu.semester3.eksamen.backend.project.dto.ResultDTO;
import edu.semester3.eksamen.backend.project.dto.ResultWithDisciplineIntegerDTO;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.model.Discipline;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.service.AthleteService;
import edu.semester3.eksamen.backend.project.service.DisciplineService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/athletes")
public class AthleteController {
    private final AthleteService athleteService;
    private final DisciplineService disciplineService;

    public AthleteController(AthleteService athleteService, DisciplineService disciplineService) {
        this.athleteService = athleteService;
        this.disciplineService = disciplineService;
    }

    @Operation(summary = "Get all athletes", description = "Get a list of all athletes currently in the database.")
    @GetMapping
    public List<AthleteDTO> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    @Operation(summary = "Get athlete by id", description = "Get a athlete by id.")
    @GetMapping("/find-by-id/{id}")
    public AthleteDTO getAthleteById(@PathVariable int id) {
        try {
            Athlete foundAthlete = athleteService.getById(id);
            return new AthleteDTO(foundAthlete);
        } catch (Exception e) {
            return null;
        }

    }

    @Operation(summary = "Get athlete by name", description = "Get a athlete by their name.")
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<AthleteDTO>> getAthletesByName(@PathVariable String name) {
        System.out.println("AthleteController has received the request to get athletes by name." + " Name: " + name + ".");
        List<Athlete> foundAthletes = athleteService.getByName(name.replace("-", " "));

        if (foundAthletes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<AthleteDTO> athleteDTOs = foundAthletes.stream().map(AthleteDTO::new).toList();
        return ResponseEntity.ok(athleteDTOs);
    }

    @Operation(summary = "Get results from an athlete", description = "Get results from an athlete.")
    @GetMapping("/{id}/results")
    public ResponseEntity<List<ResultDTO>> getResults(@PathVariable int id) {
        List<Result> foundResults = athleteService.getResults(id);
        if (foundResults.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<ResultDTO> resultDTOs = foundResults.stream().map(ResultDTO::new).toList();
        return ResponseEntity.ok(resultDTOs);
    }

    @Operation(summary = "Create athlete", description = "Create a new athlete.")
    @PostMapping
    public AthleteDTO createAthlete(@RequestBody AthleteDTO athleteDTO) {
        return athleteService.addAthlete(new Athlete(athleteDTO));
    }

    @Operation(summary = "Update athlete", description = "Update a athlete.")
    @PutMapping
    public AthleteDTO updateAthlete(@RequestBody Athlete athlete) {
        return athleteService.updateAthlete(athlete);
    }

    @Operation(summary = "Delete athlete", description = "Delete an athlete.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAthlete(@PathVariable int id) {
        System.out.println("AthleteController has received the request to delete athlete with id: " + id + ".");
        try {
            // Then delete the athlete
            athleteService.deleteAthlete(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            System.out.println("AthleteController failed to delete athlete with id: " + id + ".");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Add result to athlete", description = "Add a result to an athlete.")
    @PostMapping("/{id}/results")
    public ResponseEntity addResult(@PathVariable int id, @RequestBody ResultWithDisciplineIntegerDTO resultDTO) {
        try {
            Athlete athlete = athleteService.getById(id);
            if (athlete == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            Discipline discipline = disciplineService.getById(resultDTO.disciplineId());
            Result result = new Result(resultDTO, discipline);
            result.setAthlete(athlete);
            athleteService.addResult(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Update result for athlete", description = "Update a result for an athlete.")
    @PutMapping("/results/{resultId}")
    public ResponseEntity updateResult(@PathVariable int resultId, @RequestBody ResultDTO resultDTO) {
        try {
            athleteService.updateResult(resultId, resultDTO);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get result by id", description = "Get a result by id.")
    @GetMapping("/results/find-by-id/{resultId}")
    public ResultDTO getResultById(@PathVariable int resultId) {
        try {
            Result foundResult = athleteService.getResultById(resultId);
            return new ResultDTO(foundResult);
        } catch (Exception e) {
            return null;
        }
    }

    @Operation(summary = "Get all results", description = "Get a list of all results currently in the database.")
    @GetMapping("/results")
    public ResponseEntity<List<ResultDTO>> getAllResults() {
        List<Result> foundResults = athleteService.getAllResults();
        if (foundResults.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            List<ResultDTO> resultDTOs = foundResults.stream().map(ResultDTO::new).toList();
            return ResponseEntity.ok(resultDTOs);
        }
    }

    @Operation(summary = "Delete result", description = "Delete a result.")
    @DeleteMapping("/results/{resultId}")
    public ResponseEntity deleteResult(@PathVariable int resultId) {
        try {
            athleteService.deleteResult(resultId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}

