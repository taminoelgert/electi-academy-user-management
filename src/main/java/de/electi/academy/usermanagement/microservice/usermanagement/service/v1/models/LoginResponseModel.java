package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponseModel {
    UUID userId;
    String name;
    String email;
    boolean admin;
}
