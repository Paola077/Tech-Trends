package com.example.Tech_Trends.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    private LocalDate createAt;

    @ManyToOne
    @JoinColumn(name = "trend_id", nullable = false)
    private Trend trend;
}
