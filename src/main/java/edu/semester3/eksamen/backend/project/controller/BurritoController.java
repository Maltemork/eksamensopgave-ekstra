package edu.semester3.eksamen.backend.project.controller;


import edu.semester3.eksamen.backend.project.dto.BurritoDTO;
import edu.semester3.eksamen.backend.project.model.Burrito;
import edu.semester3.eksamen.backend.project.service.BurritoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/burritos")
public class BurritoController {
    private final BurritoService burritoService;

    public BurritoController(BurritoService burritoService) {
        this.burritoService = burritoService;
    }

    @Operation(summary = "Get all burritos", description = "Get a list of all burritos currently in the database.")
    @GetMapping("/all")
    public List<BurritoDTO> getBurritos() {
        return burritoService.getAllBurritos();
    }

    @Operation(summary = "Get burrito by id", description = "Get a burrito by its id.")
    @GetMapping("/{id}")
    public BurritoDTO getBurritoById(@PathVariable int id) {
        Burrito foundBurrito = burritoService.getBurritoById(id);
        return new BurritoDTO(foundBurrito.getId(), foundBurrito.getName(), foundBurrito.getDescription(), foundBurrito.getPrice(), foundBurrito.getIngredients(), foundBurrito.getImage());
    }

}