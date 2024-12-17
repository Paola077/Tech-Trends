package com.example.Tech_Trends.repositories;

import com.example.Tech_Trends.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByTrendId(Long trendId);
}
