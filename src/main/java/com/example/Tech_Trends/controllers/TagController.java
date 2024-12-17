package com.example.Tech_Trends.controllers;

import com.example.Tech_Trends.dtos.TagRequest;
import com.example.Tech_Trends.dtos.TagResponse;
import com.example.Tech_Trends.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getTagsByTrendId(@RequestParam Long trendId) {
        List<TagResponse> tags = tagService.getTagsByTrendId(trendId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TagResponse> addTag(@RequestBody TagRequest tagRequest) {
        TagResponse tagResponse = tagService.createTag(tagRequest);
        return new ResponseEntity<>(tagResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTagById(id);
        return new ResponseEntity<>("The tag has been deleted.", HttpStatus.OK);
    }
}
