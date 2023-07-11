package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api;

import java.util.UUID;

public interface AuthorizationService {
    boolean isUserAdmin(UUID userId);
}
