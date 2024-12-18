package com.example.Tech_Trends.dtos;

import com.example.Tech_Trends.enums.Category;

public record TrendResponse(
        Long id,
        String title,
        String description,
        Category category,
        String imgUrl,
        String createdAt,
        UserResponse user
) {
}
