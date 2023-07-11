package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class UserListResponseModel {
    UUID userId;
    String name;
    Date birthdate;
    String email;
    List<FlagModel> flags;
    boolean admin;


    public static UserListResponseModel fromEntity(User user){
        UserListResponseModel userListResponseModel = new UserListResponseModel();
        userListResponseModel.setName(user.getName());
        userListResponseModel.setAdmin(user.isAdmin());
        userListResponseModel.setBirthdate(user.getBirthDate());
        userListResponseModel.setUserId(user.getUserId());
        userListResponseModel.setEmail(user.getEmail());
        userListResponseModel.setFlags(user.getFlags().stream().map(f -> new FlagModel(f.getName(), f.getColor())).collect(Collectors.toList()));
        return userListResponseModel;
    }
}
