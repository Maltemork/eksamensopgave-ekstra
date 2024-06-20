package edu.semester3.eksamen.backend.project.controller;

import edu.semester3.eksamen.backend.project.dto.AthleteDTO;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.service.AthleteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter
@Setter
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
        System.out.println("Getting all athletes");
        return athleteService.getAllAthletes();
    }

    @Operation(summary = "Get athlete by id", description = "Get a athlete by id.")
    @GetMapping("/find-by-id/{id}")
    public AthleteDTO getAthleteById(@PathVariable int id) {
        Athlete foundAthlete = athleteService.getById(id);
        return new AthleteDTO(foundAthlete.getId(), foundAthlete.getName(), foundAthlete.getGender(), foundAthlete.getClub());
    }

    @Operation(summary = "Get athlete by name", description = "Get a athlete by name.")
    @GetMapping("/find-by-name/{name}")
    public AthleteDTO getAthleteByName(@PathVariable String name) {
        Athlete foundAthlete = athleteService.getByName(name);
        return new AthleteDTO(foundAthlete.getId(), foundAthlete.getName(), foundAthlete.getGender(), foundAthlete.getClub());
    }
}
