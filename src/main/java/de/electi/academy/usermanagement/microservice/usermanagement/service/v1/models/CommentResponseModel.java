package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class CommentResponseModel {
    String message;
    Date creationDate;
}
