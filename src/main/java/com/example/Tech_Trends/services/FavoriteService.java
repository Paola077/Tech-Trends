package com.example.Tech_Trends.services;

import com.example.Tech_Trends.dtos.FavoriteResponse;
import com.example.Tech_Trends.entity.Favorite;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;
import com.example.Tech_Trends.mappers.FavoriteMapper;
import com.example.Tech_Trends.repositories.FavoriteRepository;
import com.example.Tech_Trends.repositories.TrendRepository;
import com.example.Tech_Trends.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final TrendRepository trendRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, TrendRepository trendRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.trendRepository = trendRepository;
    }

    public List<FavoriteResponse> getFavoritesByUserId(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return favorites.stream()
                .map(FavoriteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public FavoriteResponse addFavorite(Long userId, Long trendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Trend trend = trendRepository.findById(trendId)
                .orElseThrow(() -> new RuntimeException("Trend not found"));

        Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndTrend(user, trend);
        if (existingFavorite.isPresent()) {
            throw new RuntimeException("Favorite already exists for this trend and user");
        }

        Favorite favorite = FavoriteMapper.toEntity(user, trend);
        Favorite savedFavorite = favoriteRepository.save(favorite);

        return FavoriteMapper.toResponse(savedFavorite);
    }

    public void deleteFavorite(Long favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        favoriteRepository.delete(favorite);
    }
}
