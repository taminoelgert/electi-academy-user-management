package de.electi.academy.usermanagement.microservice.usermanagement.database.repository;

import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findFirstByEmailEquals(String email);

    User findFirstByEmailEqualsAndUserIdNot(String email, UUID userId);
}
