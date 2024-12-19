package com.example.Tech_Trends.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FavoriteRequest(
        Long trendId
) {
}
