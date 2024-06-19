package edu.semester3.eksamen.backend.project.service;

import edu.semester3.eksamen.backend.project.dto.BurritoDTO;
import edu.semester3.eksamen.backend.project.model.Burrito;
import edu.semester3.eksamen.backend.project.repository.BurritoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurritoService {
    BurritoRepository burritoRepository;

    public BurritoService(BurritoRepository burritoRepository) {
        this.burritoRepository = burritoRepository;
    }

    public ResponseEntity createBurrito(BurritoDTO burritoDTO) {
        Burrito newBurrito = new Burrito(burritoDTO.name(), burritoDTO.description(), burritoDTO.price(), burritoDTO.ingredients(), burritoDTO.image());
        burritoRepository.save(newBurrito);
        return new ResponseEntity("Burrito created", null, 201);
    }
    public BurritoDTO addBurrito(Burrito newBurrito) {
        burritoRepository.save(newBurrito);
        return new BurritoDTO(newBurrito.getId(), newBurrito.getName(), newBurrito.getDescription(), newBurrito.getPrice(), newBurrito.getIngredients(), newBurrito.getImage());
    }

    public ResponseEntity deleteBurrito(int id) {
        burritoRepository.deleteById(id);
        return new ResponseEntity("Burrito deleted", null, 200);
    }

    public BurritoDTO updateBurrito(Burrito burrito) {
        burritoRepository.save(burrito);
        return burritoRepository.findById(Math.toIntExact(burrito.getId())).map(b -> new BurritoDTO(b.getId(), b.getName(), b.getDescription(), b.getPrice(), b.getIngredients(), b.getImage())).orElse(null);
    }

    public Burrito getBurritoById(int id) {
        return burritoRepository.findById(id).orElse(null);
    }

    public Burrito getBurritoByName(String name) {
        return burritoRepository.findByName(name);
    }

    public List<BurritoDTO> getAllBurritos() {
        return burritoRepository.findAll().stream().map(b -> new BurritoDTO(b.getId(), b.getName(), b.getDescription(), b.getPrice(), b.getIngredients(), b.getImage())).toList();
    }


}
