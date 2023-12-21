package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
public class UserAddModel {

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private Date birthdate;
    private List<FlagModel> flags;
    private Boolean admin;

}
