package edu.semester3.eksamen.backend.project.configuration;

import edu.semester3.eksamen.backend.project.enums.Gender;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.repository.AthleteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final AthleteRepository athleteRepository;

    public InitData(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initData();
    }


    private void initData() {
        createAthletes();


    }

    private void createAthletes() {
        athleteRepository.save(new Athlete("John Doe", Gender.MALE, "M"));
        athleteRepository.save(new Athlete("Jane Doe", Gender.FEMALE, "F" ));
        athleteRepository.save(new Athlete("Hans Hansen", Gender.MALE, "M"));
        athleteRepository.save(new Athlete("Hanne Hansen", Gender.FEMALE, "F"));
    }


}
