package de.electi.academy.usermanagement.microservice.usermanagement.endpoints.v1.rest;

import de.electi.academy.usermanagement.microservice.usermanagement.components.AuthenticationCheckComponent;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.AuthorizationService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.UserService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.UserAddModel;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.UserEditModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationCheckComponent authenticationCheckComponent;

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping
    ResponseEntity<?> addUser(@RequestBody UserAddModel userAddModel) {
        UUID executingUserId;
        try {
            executingUserId = authenticationCheckComponent.isUserAuthenticated();
        } catch (RuntimeException runtimeException) {
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        if (!authorizationService.isUserAdmin(executingUserId)) return new ResponseEntity<>("User is not an admin", HttpStatus.FORBIDDEN);
        try {
            return new ResponseEntity<>(userService.addUser(userAddModel), HttpStatus.CREATED);
        } catch (IllegalStateException illegalStateException) {
            return new ResponseEntity<>(illegalStateException.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    ResponseEntity<?> updateUser(@RequestBody UserEditModel userEditModel){
        UUID executingUserId;
        try {
            executingUserId = authenticationCheckComponent.isUserAuthenticated();
        } catch (RuntimeException runtimeException) {
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        if (!authorizationService.isUserAdmin(executingUserId) && !executingUserId.equals(userEditModel.getUserId())) return new ResponseEntity<>("User is not an admin", HttpStatus.FORBIDDEN);
        try {
            return new ResponseEntity<>(userService.updateUser(userEditModel), HttpStatus.OK);
        } catch (IllegalStateException illegalStateException) {
            return new ResponseEntity<>(illegalStateException.getMessage(), HttpStatus.CONFLICT);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    ResponseEntity<?> getUser(@RequestParam(name = "userId") UUID userId){
        try {
            authenticationCheckComponent.isUserAuthenticated();
        } catch (RuntimeException runtimeException) {
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    ResponseEntity<?> deleteUser(@RequestParam(name = "userId") UUID userId){
        try {
            authenticationCheckComponent.isUserAuthenticated();
        } catch (RuntimeException runtimeException) {
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list")
    ResponseEntity<?> listUser(){
        try {
            authenticationCheckComponent.isUserAuthenticated();
        } catch (RuntimeException runtimeException) {
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }
}
