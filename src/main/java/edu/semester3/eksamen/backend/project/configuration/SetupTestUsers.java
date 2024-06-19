package edu.semester3.eksamen.backend.project.configuration;
import edu.semester3.eksamen.backend.security.entity.Role;
import edu.semester3.eksamen.backend.security.entity.UserWithRoles;
import edu.semester3.eksamen.backend.security.repository.RoleRepository;
import edu.semester3.eksamen.backend.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class SetupTestUsers implements ApplicationRunner {
    UserWithRolesRepository userWithRolesRepository;
    RoleRepository roleRepository;
    PasswordEncoder pwEncoder;
    String passwordUsedByAll;
    public void run(ApplicationArguments args) {
        createTestUsers();
    }

    public SetupTestUsers(UserWithRolesRepository userWithRolesRepository, RoleRepository roleRepository, PasswordEncoder pwEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.roleRepository = roleRepository;
        this.pwEncoder = pwEncoder;
        this.passwordUsedByAll = "test123";
    }

    public void createTestUsers() {
        // CREATE AND SAVE ROLES
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("STAFF"));
        roleRepository.save(new Role("ADMIN"));

        Role roleUser = roleRepository.findById("USER").orElseThrow(()-> new NoSuchElementException("Role 'user' not found"));
        Role roleAdmin = roleRepository.findById("ADMIN").orElseThrow(()-> new NoSuchElementException("Role 'admin' not found"));
        Role roleStaff = roleRepository.findById("STAFF").orElseThrow(()-> new NoSuchElementException("Role 'staff' not found"));

        // CREATE AND SAVE USERS
        // ADMIN USER
        UserWithRoles user1 = new UserWithRoles("user1", pwEncoder.encode(passwordUsedByAll) , "user1@test-email.com");
        user1.addRole(roleUser);
        user1.addRole(roleAdmin);

        // STAFF USER
        UserWithRoles user2 = new UserWithRoles("user2", pwEncoder.encode(passwordUsedByAll), "user2@test-email.com");
        user2.addRole(roleUser);
        user2.addRole(roleStaff);

        // NORMAL USER
        UserWithRoles user3 = new UserWithRoles("user3", pwEncoder.encode(passwordUsedByAll), "user3@test-email.com");
        user3.addRole(roleUser);

        //Save users
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}
