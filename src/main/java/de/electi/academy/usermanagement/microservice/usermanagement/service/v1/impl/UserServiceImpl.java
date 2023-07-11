package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.impl;

import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.Flag;
import de.electi.academy.usermanagement.microservice.usermanagement.database.entity.User;
import de.electi.academy.usermanagement.microservice.usermanagement.database.repository.FlagRepository;
import de.electi.academy.usermanagement.microservice.usermanagement.database.repository.UserRepository;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.CommentService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.UserService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    FlagRepository flagRepository;

    @Override
    public UserResponseModel addUser(UserAddModel userAddModel) {
        if(userRepository.findFirstByEmailEquals(userAddModel.getEmail())!=null) throw new IllegalStateException("There is already a user with this email");
        User user = new User();
        user.setAdmin(userAddModel.isAdmin());
        user.setEmail(userAddModel.getEmail());
        user.setName(userAddModel.getName());
        user.setBirthDate(userAddModel.getBirthdate());
        user.setModifiedDate(new Date());
        user.setCreationDate(new Date());
        user.setPassword(userAddModel.getPassword());
        userRepository.save(user);
        List<Flag> flags = new ArrayList<>();
        if(userAddModel.getFlags() != null)
            for (FlagModel flagModel : userAddModel.getFlags()) {
                Flag flag = new Flag();
                flag.setColor(flagModel.getColor());
                flag.setName(flagModel.getName());
                flag.setUser(user);
                flags.add(flag);
            }
        user.setFlags(flags);
        flagRepository.saveAll(flags);
        return UserResponseModel.fromEntity(user, commentService.list(user.getUserId()));
    }

    @Override
    public UserResponseModel updateUser(UserEditModel userEditModel) {
        Optional<User> userOptional = userRepository.findById(userEditModel.getUserId());
        if(userOptional.isEmpty()) throw new NoSuchElementException("User not found");
        if(userRepository.findFirstByEmailEqualsAndUserIdNot(userEditModel.getEmail(), userEditModel.getUserId())!=null) throw new IllegalStateException("There is already a user with this email");
        User user = userOptional.get();
        user.setAdmin(userEditModel.isAdmin());
        user.setEmail(userEditModel.getEmail());
        user.setName(userEditModel.getName());
        user.setBirthDate(userEditModel.getBirthdate());
        user.setModifiedDate(new Date());
        userRepository.save(user);
        List<Flag> flags = new ArrayList<>();
        if(userEditModel.getFlags() != null)
            for (FlagModel flagModel : userEditModel.getFlags()) {
                Flag flag = new Flag();
                flag.setColor(flagModel.getColor());
                flag.setName(flagModel.getName());
                flag.setUser(user);
                flags.add(flag);
            }
        if(!flags.equals(user.getFlags())){
            System.out.println("updated flags");
            flagRepository.deleteAll(user.getFlags());
            user.setFlags(flags);
            flagRepository.saveAll(flags);
        }
        return UserResponseModel.fromEntity(user, commentService.list(user.getUserId()));
    }

    @Override
    public UserResponseModel getUser(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new NoSuchElementException("User not found");
        return UserResponseModel.fromEntity(userOptional.get(), commentService.list(userId));
    }

    @Override
    public void deleteUser(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new NoSuchElementException("User not found");
        userRepository.delete(userOptional.get());
    }

    @Override
    public List<UserListResponseModel> listUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> UserListResponseModel.fromEntity(u)).collect(Collectors.toList());
    }
}
