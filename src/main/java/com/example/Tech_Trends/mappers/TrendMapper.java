package com.example.Tech_Trends.mappers;

import com.example.Tech_Trends.dtos.TrendRequest;
import com.example.Tech_Trends.dtos.TrendResponse;
import com.example.Tech_Trends.dtos.UserResponse;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;

public class TrendMapper {

    public static Trend toEntity(TrendRequest trendRequest, User user) {
        return new Trend(
                trendRequest.title(),
                trendRequest.description(),
                trendRequest.category(),
                trendRequest.imgUrl(),
                user
        );
    }

    public static TrendResponse toResponse(Trend trend) {
        UserResponse userResponse = UserMapper.toResponse(trend.getUser());
        return new TrendResponse(
                trend.getId(),
                trend.getTitle(),
                trend.getDescription(),
                trend.getCategory(),
                trend.getImgUrl(),
                trend.getCreateAt().toString(),
                userResponse);
    }
}
