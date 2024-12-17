package com.example.Tech_Trends.dtos;

import com.example.Tech_Trends.enums.Category;

public record TrendRequest(
        String title,
        String description,
        Category category,
        String imgUrl,
        Long userId
) {
}
