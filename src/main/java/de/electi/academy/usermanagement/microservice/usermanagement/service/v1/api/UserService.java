package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api;

import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.UserAddModel;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.UserEditModel;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.UserListResponseModel;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.UserResponseModel;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseModel addUser(UserAddModel userAddModel);

    UserResponseModel updateUser(UserEditModel userEditModel);

    UserResponseModel getUser(UUID userId);

    void deleteUser(UUID userId);

    List<UserListResponseModel> listUser();
}
