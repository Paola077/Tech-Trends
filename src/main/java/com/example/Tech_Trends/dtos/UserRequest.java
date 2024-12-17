package com.example.Tech_Trends.dtos;

import com.example.Tech_Trends.enums.Role;

public record UserRequest(
    String username,
    String email,
    String password,
    Role role
) {
}
