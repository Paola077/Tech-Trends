package com.example.Tech_Trends.dtos;

import com.example.Tech_Trends.enums.Role;

public record UsersRequest(
    String username,
    String email,
    String password,
    Role role
) {
}
