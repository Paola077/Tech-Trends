package com.example.Tech_Trends.services;

import com.example.Tech_Trends.dtos.TagRequest;
import com.example.Tech_Trends.dtos.TagResponse;
import com.example.Tech_Trends.entity.Tag;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.exceptions.TechTrendNotFoundException;
import com.example.Tech_Trends.mappers.TagMapper;
import com.example.Tech_Trends.repositories.TagRepository;
import com.example.Tech_Trends.repositories.TrendRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TrendRepository trendRepository;

    public TagService(TagRepository tagRepository, TrendRepository trendRepository) {
        this.tagRepository = tagRepository;
        this.trendRepository = trendRepository;
    }

    public List<TagResponse> getTagsByTrendId(Long trendId) {
        List<Tag> tags = tagRepository.findByTrendId(trendId);
        return tags.stream()
                .map(TagMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TagResponse createTag(TagRequest tagRequest) {
        Trend trend = trendRepository.findById(tagRequest.trendId())
                .orElseThrow(() -> new TechTrendNotFoundException("Trend not found with ID: " + tagRequest.trendId()));

        Tag tag = TagMapper.toEntity(tagRequest, trend);
        Tag savedTag = tagRepository.save(tag);
        return TagMapper.toResponse(savedTag);
    }

    public void deleteTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TechTrendNotFoundException("Tag not found with ID: " + id));
        tagRepository.delete(tag);
    }
}
