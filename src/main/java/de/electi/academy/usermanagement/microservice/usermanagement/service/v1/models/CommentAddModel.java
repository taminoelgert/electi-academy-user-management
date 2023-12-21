package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentAddModel {
    private UUID userId;
    private String message;
}
