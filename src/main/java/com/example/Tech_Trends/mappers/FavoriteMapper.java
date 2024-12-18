package com.example.Tech_Trends.mappers;

import com.example.Tech_Trends.dtos.FavoriteResponse;
import com.example.Tech_Trends.entity.Favorite;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;

public class FavoriteMapper {
    public static Favorite toEntity(User user, Trend trend) {
        return new Favorite(
                user,
                trend
        );
    }

    public static FavoriteResponse toResponse(Favorite favorite) {
        return new FavoriteResponse(
                favorite.getId(),
                favorite.getTrend().getId(),
                favorite.getTrend().getTitle(),
                favorite.getUser().getId(),
                favorite.getUser().getUsername()

        );
    }
}
