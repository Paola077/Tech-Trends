package com.example.Tech_Trends.services;

import com.example.Tech_Trends.dtos.UserRequest;
import com.example.Tech_Trends.dtos.UserResponse;
import com.example.Tech_Trends.entity.User;
import com.example.Tech_Trends.exceptions.TechTrendExistingUserException;
import com.example.Tech_Trends.exceptions.TechTrendNotFoundException;
import com.example.Tech_Trends.mappers.UserMapper;
import com.example.Tech_Trends.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        Optional<User> existUser = userRepository.findByEmail(userRequest.email());
        if(existUser.isPresent())
            throw new TechTrendExistingUserException("User already exist with this email");

        User user = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(UserMapper::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        Optional<User> optionalGuardian = userRepository.findById(id);

        if(optionalGuardian.isEmpty()) {
            throw new TechTrendNotFoundException("The user with id " + id + " does not exist.");
        }
        User user = optionalGuardian.get();
        return UserMapper.toResponse(user);
    }

    public UserResponse updateUserById(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setUsername(userRequest.username());
            user.setEmail(userRequest.email());
            user.setPassword(userRequest.password());
            user.setRole(userRequest.role());

            User updatedUser = userRepository.save(user);
            return UserMapper.toResponse(updatedUser);
        }
        throw new TechTrendNotFoundException("The user with id " + id + " does not exist.");
    }


    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new TechTrendNotFoundException("The user with id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }
}
