package com.example.Tech_Trends.mappers;

import com.example.Tech_Trends.dtos.UsersRequest;
import com.example.Tech_Trends.dtos.UsersResponse;
import com.example.Tech_Trends.entity.Users;

public class UsersMappers {

    public static Users toEntity(UsersRequest usersRequest) {
        return new Users(
                usersRequest.username(),
                usersRequest.email(),
                usersRequest.password(),
                usersRequest.role()
        );
    }

    public static UsersResponse toResponse(Users users) {
        return new UsersResponse(
                users.getUsername()
        );
    }
}
