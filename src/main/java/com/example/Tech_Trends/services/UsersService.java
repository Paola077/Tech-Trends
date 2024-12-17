package com.example.Tech_Trends.services;

import com.example.Tech_Trends.dtos.UserRequest;
import com.example.Tech_Trends.dtos.UserResponse;
import com.example.Tech_Trends.entity.User;
import com.example.Tech_Trends.exceptions.TechTrendExistingUserException;
import com.example.Tech_Trends.mappers.UserMappers;
import com.example.Tech_Trends.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        Optional<User> existUser = userRepository.findByEmail(userRequest.email());
        if(existUser.isPresent())
            throw new TechTrendExistingUserException("User already exist with this email");

        User user = UserMappers.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return UserMappers.toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(UserMappers::toResponse).toList();
    }
}
