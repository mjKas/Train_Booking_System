package com.assignment.trainbookingsystem.repository;
import com.assignment.trainbookingsystem.model.Role;
import com.assignment.trainbookingsystem.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}