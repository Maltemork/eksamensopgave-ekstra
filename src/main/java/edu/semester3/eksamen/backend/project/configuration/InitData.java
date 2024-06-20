package edu.semester3.eksamen.backend.project.configuration;

import edu.semester3.eksamen.backend.project.enums.Gender;
import edu.semester3.eksamen.backend.project.enums.ResultTypes;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.model.Discipline;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.repository.AthleteRepository;
import edu.semester3.eksamen.backend.project.repository.DisciplineRepository;
import edu.semester3.eksamen.backend.project.repository.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitData implements CommandLineRunner {
    private final AthleteRepository athleteRepository;
    private final DisciplineRepository disciplineRepository;
    private final ResultRepository resultRepository;

    public InitData(AthleteRepository athleteRepository, DisciplineRepository disciplineRepository, ResultRepository resultRepository) {
        this.athleteRepository = athleteRepository;
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initData();
    }


    private void initData() {

        createDisciplines();
        createAthletes();
        createResults();
    }



    private void createDisciplines() {
        disciplineRepository.save(new Discipline("100 meter sprint",  "Løb"));
        disciplineRepository.save(new Discipline("Højdespring", "Spring"));
        disciplineRepository.save(new Discipline("Kuglestød", "Kast"));
        disciplineRepository.save(new Discipline("Længdespring", "Spring"));
        disciplineRepository.save(new Discipline("Spydkast", "Kast"));
        disciplineRepository.save(new Discipline("400 meter løb", "Løb"));
        disciplineRepository.save(new Discipline("800 meter løb", "Løb"));
        disciplineRepository.save(new Discipline("1500 meter løb", "Løb"));
        disciplineRepository.save(new Discipline("100 meter hæk", "Løb"));
        disciplineRepository.save(new Discipline("400 meter hæk", "Løb"));
        disciplineRepository.save(new Discipline("3000 meter forhindringsløb", "Løb"));
        disciplineRepository.save(new Discipline("4 x 100 meter stafet", "Løb"));
        disciplineRepository.save(new Discipline("4 x 400 meter stafet", "Løb"));
        disciplineRepository.save(new Discipline("10 km landevej", "Løb"));
        disciplineRepository.save(new Discipline("Halvmaraton", "Løb"));
        disciplineRepository.save(new Discipline("Maraton", "Løb"));

    }

    private void createAthletes() {
        List<Discipline> disciplines = disciplineRepository.findAll();
        Discipline sprint100m = disciplineRepository.findByNameIgnoreCase("100 meter sprint");
        Discipline hojdespring = disciplineRepository.findByNameIgnoreCase("Højdespring");
        Discipline kuglestod = disciplineRepository.findByNameIgnoreCase("Kuglestød");
        Discipline laengdespring = disciplineRepository.findByNameIgnoreCase("Længdespring");

        athleteRepository.save(new Athlete("John Doe", Gender.MALE, "M", Set.of(sprint100m, hojdespring)));
        athleteRepository.save(new Athlete("Jane Doe", Gender.FEMALE, "F", Set.of(kuglestod, laengdespring)));
        athleteRepository.save(new Athlete("Hans Hansen", Gender.MALE, "M", Set.of(kuglestod, hojdespring)));
        athleteRepository.save(new Athlete("Hanne Hansen", Gender.FEMALE, "F", Set.of(sprint100m, laengdespring)));
        athleteRepository.save(new Athlete("Peter Petersen", Gender.MALE, "Tølløse", Set.of(disciplines.get((int) (Math.random()*disciplines.size())), disciplines.get((int) (Math.random()*disciplines.size())))));

        String[] firstNames = {"Michael", "Usain", "Carl", "Jesse", "Mo", "Haile", "Paula", "Florence", "Fanny", "Allyson", "Marie-José", "Sanya", "Shelly-Ann", "Cathy", "Wilma", "Betty", "Gail", "Elaine", "Dawn", "Sally"};
        String[] lastNames = {"Johnson", "Bolt", "Lewis", "Owens", "Farah", "Gebrselassie", "Radcliffe", "Griffith-Joyner", "Blankers-Koen", "Felix", "Pérec", "Richards-Ross", "Fraser-Pryce", "Freeman", "Rudolph", "Cuthbert", "Devers", "Thompson", "Harper-Nelson", "Pearson"};

        for (int i = 0; i < 120; i++) {
            String name = firstNames[(int) (Math.random()*firstNames.length)] + " " + lastNames[(int) (Math.random()*lastNames.length)];
            Gender gender = i % 2 == 0 ? Gender.MALE : Gender.FEMALE;
            String club = i % 2 == 0 ? "M" : "F";
            Set<Discipline> athleteDisciplines = new HashSet<>();
            while (athleteDisciplines.size() < 2) {
                athleteDisciplines.add(disciplines.get((int) (Math.random()*disciplines.size())));
            }
            athleteRepository.save(new Athlete(name, gender, club, athleteDisciplines));
        }


    }

    private void createResults() {
        List<Athlete> athletes = athleteRepository.findAll();
        List<Discipline> disciplines = disciplineRepository.findAll();

        // Manually create new Results
        Result result1 = new Result(athletes.get(0), disciplines.get(0), 9.45, ResultTypes.TIME, "2009-08-16");
        Result result2 = new Result(athletes.get(1), disciplines.get(1), 12.30, ResultTypes.DISTANCE, "1993-07-30");
        Result result3 = new Result(athletes.get(2), disciplines.get(2), 23.12, ResultTypes.TIME, "1990-05-20");

        // Save the results in the ResultRepository
        resultRepository.save(result1);
        resultRepository.save(result2);
        resultRepository.save(result3);

    }



}
