package edu.semester3.eksamen.backend.security.repository;

import edu.semester3.eksamen.backend.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

}
