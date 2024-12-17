package com.example.Tech_Trends.repositories;

import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrendRepository extends JpaRepository<Trend, Long> {
    Optional<Trend> findByTitleAndUser(String title, User user);
}
