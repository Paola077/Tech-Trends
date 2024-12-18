package com.example.Tech_Trends.entity;

import com.example.Tech_Trends.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Category category;

    @Column(length = 255)
    private String imgUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "trend", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    public Trend(String title, String description, Category category, String imgUrl, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.imgUrl = imgUrl;
        this.user = user;
    }


    public Trend(Long id, String title, String description, Category category, String imgUrl, LocalDate createAt, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.imgUrl = imgUrl;
        this.createAt = createAt;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trend trend = (Trend) o;
        return Objects.equals(id, trend.id) && Objects.equals(title, trend.title) && Objects.equals(description, trend.description) && category == trend.category && Objects.equals(imgUrl, trend.imgUrl) && Objects.equals(createAt, trend.createAt) && Objects.equals(user, trend.user) && Objects.equals(favorites, trend.favorites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, imgUrl, createAt, user, favorites);
    }

    @Override
    public String toString() {
        return "Trend{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", imgUrl='" + imgUrl + '\'' +
                ", createAt=" + createAt +
                ", user=" + user +
                ", favorites=" + favorites +
                '}';
    }
}
