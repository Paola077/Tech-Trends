package com.example.Tech_Trends.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createAt;

    @ManyToOne
    @JoinColumn(name = "trend_id", nullable = false)
    private Trend trend;

    public Tag(String name,Trend trend) {
        this.name = name;
        this.trend = trend;
    }
}
