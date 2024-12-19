package com.example.Tech_Trends.dtos;

import com.example.Tech_Trends.enums.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TrendRequest(
        @NotNull(message = "the field title cannot be null")
        @NotEmpty(message = "the field title cannot be empty")
        String title,

        @NotNull(message = "the field description cannot be null")
        @NotEmpty(message = "the field description cannot be empty")
        String description,

        Category category,
        String imgUrl,
        Long userId
) {
}
