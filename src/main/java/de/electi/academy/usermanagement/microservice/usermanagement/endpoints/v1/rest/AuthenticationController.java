package de.electi.academy.usermanagement.microservice.usermanagement.endpoints.v1.rest;

import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.AuthenticationService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.LoginResponseModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("login")
    ResponseEntity<?> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        try {
            LoginResponseModel loginResponseModel = authenticationService.login(email, password);
            httpServletRequest.getSession().setAttribute("userId", loginResponseModel.getUserId());
            return new ResponseEntity<>(loginResponseModel, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("logout")
    ResponseEntity<?> logout() {
        httpServletRequest.getSession().setAttribute("userId", null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
