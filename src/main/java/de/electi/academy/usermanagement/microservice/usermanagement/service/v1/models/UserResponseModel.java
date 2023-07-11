package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class UserResponseModel {
    UUID userId;
    String name;
    String email;
    Date birthdate;
    List<FlagModel> flags;
    boolean admin;
    List<CommentResponseModel> comments;

    public static UserResponseModel fromEntity(User user, List<CommentResponseModel> comments) {
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setName(user.getName());
        userResponseModel.setAdmin(user.isAdmin());
        userResponseModel.setBirthdate(user.getBirthDate());
        userResponseModel.setFlags(user.getFlags().stream().map(f -> new FlagModel(f.getName(), f.getColor())).collect(Collectors.toList()));
        userResponseModel.setEmail(user.getEmail());
        userResponseModel.setComments(comments);
        userResponseModel.setUserId(user.getUserId());
        return userResponseModel;
    }
}
