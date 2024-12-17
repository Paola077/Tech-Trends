package com.example.Tech_Trends.controllers;

import com.example.Tech_Trends.dtos.UserRequest;
import com.example.Tech_Trends.dtos.UserResponse;
import com.example.Tech_Trends.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = usersService.createUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> usersList = usersService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
