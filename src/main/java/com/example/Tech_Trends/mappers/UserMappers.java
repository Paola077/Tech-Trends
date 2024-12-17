package com.example.Tech_Trends.mappers;

import com.example.Tech_Trends.dtos.UserRequest;
import com.example.Tech_Trends.dtos.UserResponse;
import com.example.Tech_Trends.entity.User;

public class UserMappers {

    public static User toEntity(UserRequest userRequest) {
        return new User(
                userRequest.username(),
                userRequest.email(),
                userRequest.password(),
                userRequest.role()
        );
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUsername()
        );
    }
}
