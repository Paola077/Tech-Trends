package com.example.Tech_Trends.controllers;

import com.example.Tech_Trends.dtos.FavoriteResponse;
import com.example.Tech_Trends.services.FavoriteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteResponse>> getFavoritesByUserId(@PathVariable Long userId) {
        List<FavoriteResponse> favorites = favoriteService.getFavoritesByUserId(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FavoriteResponse> addFavorite( @RequestParam Long userId, @RequestParam @Valid Long trendId) {
        FavoriteResponse favoriteResponse = favoriteService.addFavorite(userId, trendId);
        return new ResponseEntity<>(favoriteResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<String> deleteFavorite(@PathVariable Long favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return new ResponseEntity<>("Favorite has been deleted successfully", HttpStatus.OK);
    }
}
