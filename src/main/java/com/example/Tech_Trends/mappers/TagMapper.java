package com.example.Tech_Trends.mappers;

import com.example.Tech_Trends.dtos.TagRequest;
import com.example.Tech_Trends.dtos.TagResponse;
import com.example.Tech_Trends.entity.Tag;
import com.example.Tech_Trends.entity.Trend;

import java.time.LocalDate;

public class TagMapper {

    public static Tag toEntity(TagRequest tagRequest, Trend trend) {
        return new Tag(
                tagRequest.name(),
                trend
        );
    }

    public static TagResponse toResponse(Tag tag) {
        return new TagResponse(
                tag.getName(),
                tag.getTrend().getId()
        );
    }
}
