package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class UserEditModel {

    @NonNull
    UUID userId;
    @NonNull
    String name;
    @NonNull
    String email;
    Date birthdate;
    List<FlagModel> flags;
    boolean admin;

}
