package com.example.Tech_Trends.services;

import com.example.Tech_Trends.dtos.UsersRequest;
import com.example.Tech_Trends.dtos.UsersResponse;
import com.example.Tech_Trends.entity.Users;
import com.example.Tech_Trends.exceptions.TechTrendExistingUserException;
import com.example.Tech_Trends.mappers.UsersMappers;
import com.example.Tech_Trends.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersResponse createUser(UsersRequest usersRequest) {
        Optional<Users> existUser = usersRepository.findByEmail(usersRequest.email());
        if(existUser.isPresent())
            throw new TechTrendExistingUserException("User already exist with this email");

        Users user = UsersMappers.toEntity(usersRequest);
        Users savedUser = usersRepository.save(user);
        return UsersMappers.toResponse(savedUser);
    }

    public List<UsersResponse> getAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        return usersList.stream()
                .map(UsersMappers::toResponse).toList();
    }
}
