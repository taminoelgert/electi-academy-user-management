package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class UserEditModel {

    @NonNull
    private UUID userId;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private Date birthdate;
    private List<FlagModel> flags;
    private Boolean admin;

}
