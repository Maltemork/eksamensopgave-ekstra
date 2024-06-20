package edu.semester3.eksamen.backend.project.controller;

import edu.semester3.eksamen.backend.project.dto.BurritoDTO;
import edu.semester3.eksamen.backend.project.dto.ParticipantDTO;
import edu.semester3.eksamen.backend.project.model.Burrito;
import edu.semester3.eksamen.backend.project.service.ParticipantService;
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
@RequestMapping("/participants")
public class ParticipantsController {
    private final ParticipantService participantService;

    public ParticipantsController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @Operation(summary = "Get all participants", description = "Get a list of all participants currently in the database.")
    @GetMapping
    public List<ParticipantDTO> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @Operation(summary = "Get participants by id", description = "Get a participant by its id.")
    @GetMapping("/{id}")
    public ParticipantDTO getParticipantById(@PathVariable int id) {
        Burrito foundParticipant = participantService.getById(id);
    }
}
