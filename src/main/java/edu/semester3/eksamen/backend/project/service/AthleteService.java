package edu.semester3.eksamen.backend.project.service;

import edu.semester3.eksamen.backend.project.dto.AthleteDTO;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.repository.AthleteRepository;
import edu.semester3.eksamen.backend.project.repository.ResultRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    AthleteRepository athleteRepository;
    ResultRepository resultRepository;

    public AthleteService(AthleteRepository athleteRepository, ResultRepository resultRepository) {
        this.athleteRepository = athleteRepository;
        this.resultRepository = resultRepository;
    }

    public ResponseEntity createAthlete(AthleteDTO athleteDTO) {
        Athlete newAthlete = new Athlete(athleteDTO.name(), athleteDTO.gender(),  athleteDTO.club());
        athleteRepository.save(newAthlete);
        return new ResponseEntity("Athlete created", null, 201);
    }
    public AthleteDTO addAthlete(Athlete newAthlete) {
        athleteRepository.save(newAthlete);
        return new AthleteDTO(newAthlete.getId(), newAthlete.getName(), newAthlete.getGender(), newAthlete.getClub());
    }

    public ResponseEntity deleteAthlete(int id) {
        athleteRepository.deleteById(id);
        return new ResponseEntity("Athlete successfully removed.", null, 200);
    }

    public AthleteDTO updateAthlete(Athlete athlete) {
        athleteRepository.save(athlete);
        return athleteRepository.findById(athlete.getId()).map(b -> new AthleteDTO(b.getId(), b.getName(), b.getGender(), b.getClub())).orElse(null);
    }

    public Athlete getById(int id) {
        return athleteRepository.findById(id).orElse(null);
    }

    public List<Athlete> getByName(String name) {
        System.out.println("AthleteService has received the request to get athletes by name." + " Name: " + name + ".");
        try {
            return athleteRepository.findByNameContainingIgnoreCase(name);
        } catch (Exception e) {
            return null;
        }
    }

    public List<AthleteDTO> getAllAthletes() {
        System.out.println("AthleteService has received the request to get all athletes.");
        List<Athlete> athletes = athleteRepository.findAll();
        System.out.println(athletes);
        return athletes.stream().map(b -> new AthleteDTO(b.getId(), b.getName(), b.getGender(), b.getClub(), b.getAge(), b.getWeight(), b.getHeight(), b.getEmail(), b.getPhone(), b.getAddress(), b.getCity(), b.getPostalCode(), b.getComment(), b.getDisciplines())).toList();
    }

    public List<Result> getResults(int id) {
        return resultRepository.findAllByAthleteId(id);
    }

    public void addResult(Result result) {
        resultRepository.save(result);
    }

    public void deleteResult(int id) {
        resultRepository.deleteById(id);
    }

    public void updateResult(Result result) {
        resultRepository.save(result);
    }

}