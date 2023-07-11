package de.electi.academy.usermanagement.microservice.usermanagement.database.repository;

import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlagRepository extends JpaRepository<Flag, UUID> {
}
