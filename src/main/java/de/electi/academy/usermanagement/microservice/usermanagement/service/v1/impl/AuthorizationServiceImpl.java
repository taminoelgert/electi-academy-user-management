package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.impl;

import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.User;
import de.electi.academy.usermanagement.microservice.usermanagement.database.repository.UserRepository;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isUserAdmin(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new RuntimeException("User authentication failed");
        return userOptional.get().isAdmin();
    }
}
