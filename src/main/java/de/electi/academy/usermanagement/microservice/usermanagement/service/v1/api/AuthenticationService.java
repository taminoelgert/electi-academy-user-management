package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api;

import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.LoginResponseModel;

public interface AuthenticationService {
    LoginResponseModel login(String email, String password);
}
