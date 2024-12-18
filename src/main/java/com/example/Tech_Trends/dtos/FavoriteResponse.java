package com.example.Tech_Trends.dtos;

public record FavoriteResponse(
        Long id,
        Long trendId,
        String trendTitle,
        Long userId,
        String username
) {}
