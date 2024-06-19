package edu.semester3.eksamen.backend.project.configuration;

import edu.semester3.eksamen.backend.project.model.Burrito;
import edu.semester3.eksamen.backend.project.repository.BurritoRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SetupTestBurritos implements ApplicationRunner {
    private final BurritoRepository burritoRepository;
    public void run(ApplicationArguments args) {
        createTestBurritos();
    }

    public SetupTestBurritos(BurritoRepository burritoRepository) {
        this.burritoRepository = burritoRepository;
    }

    public void createTestBurritos() {
        // CREATE AND SAVE BURRITOS
        Burrito burrito1 = new Burrito("Beef Burrito", "Burrito with beef, cheese and salsa", 50.0, "Tortilla, beef, cheese, salsa", "burrito1.jpg");
        Burrito burrito2 = new Burrito("Chicken Burrito", "Burrito with chicken, cheese and salsa", 60.0, "Tortilla, chicken, cheese, salsa", "burrito2.jpg");
        Burrito burrito3 = new Burrito("Pork Burrito", "Burrito with pork, cheese and salsa", 70.0, "Tortilla, pork, cheese, salsa", "burrito3.jpg");
        burritoRepository.save(burrito1);
        burritoRepository.save(burrito2);
        burritoRepository.save(burrito3);
    }
}
