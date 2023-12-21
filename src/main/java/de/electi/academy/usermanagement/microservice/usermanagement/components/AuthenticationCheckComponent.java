package de.electi.academy.usermanagement.microservice.usermanagement.components;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationCheckComponent {
    @Autowired
    private HttpServletRequest httpServletRequest;

    public UUID isUserAuthenticated() {
        Object userId = httpServletRequest.getSession().getAttribute("userId");
        if (userId == null) throw new RuntimeException("User is not logged in");
        return (UUID) userId;
    }
}
