package com.example.Tech_Trends.repositories;

import com.example.Tech_Trends.entity.Trend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendRepository extends JpaRepository<Trend, Long> {
}
