package com.example.Tech_Trends.repositories;

import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;
import com.example.Tech_Trends.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TrendRepository extends JpaRepository<Trend, Long> {
    Optional<Trend> findByTitleAndUser(String title, User user);
    List<Trend> findByCategory(Category category);
    List<Trend> findByTitleContainingIgnoreCase(String title);
    List<Trend> findByCategoryAndTitleContainingIgnoreCase(Category category, String title);
}
