package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.Data;
import lombok.NonNull;

import java.util.*;
@Data
public class UserAddModel {

    @NonNull
    String name;
    @NonNull
    String email;
    @NonNull
    String password;
    Date birthdate;
    List<FlagModel> flags;
    boolean admin;

}
