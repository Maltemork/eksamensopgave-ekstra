package edu.semester3.eksamen.backend.security.repository;

import edu.semester3.eksamen.backend.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles,String> {
    Boolean existsByEmail(String email);

    UserWithRoles findByUsername(String currentUsername);
}
