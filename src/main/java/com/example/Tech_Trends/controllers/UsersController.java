package com.example.Tech_Trends.controllers;

import com.example.Tech_Trends.dtos.UsersRequest;
import com.example.Tech_Trends.dtos.UsersResponse;
import com.example.Tech_Trends.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersResponse> addUser(@RequestBody UsersRequest usersRequest) {
        UsersResponse usersResponse = usersService.createUser(usersRequest);
        return new ResponseEntity<>(usersResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsersResponse>> getAll() {
        List<UsersResponse> usersList = usersService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
