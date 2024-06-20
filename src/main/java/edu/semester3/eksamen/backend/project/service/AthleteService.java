package edu.semester3.eksamen.backend.project.service;

import edu.semester3.eksamen.backend.project.dto.AthleteDTO;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.repository.AthleteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    AthleteRepository athleteRepository;

    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
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

    public Athlete getByName(String name) {
        return athleteRepository.findByName(name);
    }

    public List<AthleteDTO> getAllAthletes() {
        System.out.println("AthleteService has received the request to get all athletes.");
        List<Athlete> athletes = athleteRepository.findAll();
        System.out.println(athletes);
        return athletes.stream().map(b -> new AthleteDTO(b.getId(), b.getName(), b.getGender(), b.getClub())).toList();
    }
}
