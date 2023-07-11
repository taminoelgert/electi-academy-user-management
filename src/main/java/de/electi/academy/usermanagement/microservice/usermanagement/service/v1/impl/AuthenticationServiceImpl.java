package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.impl;

import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.User;
import de.electi.academy.usermanagement.microservice.usermanagement.database.repository.UserRepository;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.AuthenticationService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.LoginResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepository userRepository;

    @Override
    public LoginResponseModel login(String email, String password) {
        User user = userRepository.findFirstByEmailEquals(email);
        if(user == null)
            throw new RuntimeException("Username or password wrong");
        if(!user.getPassword().equals(password))
            throw new RuntimeException("Username or password wrong");
        LoginResponseModel loginResponseModel = new LoginResponseModel();
        loginResponseModel.setAdmin(user.isAdmin());
        loginResponseModel.setName(user.getName());
        loginResponseModel.setEmail(user.getEmail());
        loginResponseModel.setUserId(user.getUserId());
        return loginResponseModel;
    }
}
