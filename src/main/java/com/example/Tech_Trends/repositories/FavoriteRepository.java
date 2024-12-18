package com.example.Tech_Trends.repositories;

import com.example.Tech_Trends.entity.Favorite;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    Optional<Favorite> findByUserAndTrend(User user, Trend trend);
}
