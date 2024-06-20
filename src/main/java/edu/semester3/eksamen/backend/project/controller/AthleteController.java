package edu.semester3.eksamen.backend.project.controller;

import edu.semester3.eksamen.backend.project.dto.AthleteDTO;
import edu.semester3.eksamen.backend.project.dto.ResultDTO;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.service.AthleteService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/athletes")
public class AthleteController {
    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @Operation(summary = "Get all athletes", description = "Get a list of all athletes currently in the database.")
    @GetMapping
    public List<AthleteDTO> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    @Operation(summary = "Get athlete by id", description = "Get a athlete by id.")
    @GetMapping("/find-by-id/{id}")
    public AthleteDTO getAthleteById(@PathVariable int id) {
        Athlete foundAthlete = athleteService.getById(id);
        return new AthleteDTO(foundAthlete.getId(), foundAthlete.getName(), foundAthlete.getGender(), foundAthlete.getClub());
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
        return athleteService.addAthlete(new Athlete(athleteDTO.name(), athleteDTO.gender(), athleteDTO.club()));
    }

    @Operation(summary = "Update athlete", description = "Update a athlete.")
    @PutMapping
    public AthleteDTO updateAthlete(@RequestBody AthleteDTO athleteDTO) {
        return athleteService.updateAthlete(new Athlete(athleteDTO.id(), athleteDTO.name(), athleteDTO.gender(), athleteDTO.club(), athleteDTO.age(), athleteDTO.weight(), athleteDTO.height(), athleteDTO.email(), athleteDTO.phone(), athleteDTO.address(), athleteDTO.city(), athleteDTO.postalCode(), athleteDTO.comment(), athleteDTO.disciplines()));
    }

    @Operation(summary = "Delete athlete", description = "Delete a athlete.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAthlete(@PathVariable int id) {
        return athleteService.deleteAthlete(id);
    }

    @Operation(summary = "Add result to athlete", description = "Add a result to an athlete.")
    @PostMapping("/{id}/results")
    public ResponseEntity addResult(@PathVariable int id, @RequestBody ResultDTO resultDTO) {
        try {
            athleteService.addResult(new Result(resultDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Update result for athlete", description = "Update a result for an athlete.")
    @PutMapping("/{id}/results")
    public ResponseEntity updateResult(@PathVariable int id, @RequestBody ResultDTO resultDTO) {
        try {
            athleteService.updateResult(new Result(resultDTO));
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
