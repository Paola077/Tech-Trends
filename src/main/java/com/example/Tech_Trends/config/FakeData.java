package com.example.Tech_Trends.config;

import com.example.Tech_Trends.entity.Favorite;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;
import com.example.Tech_Trends.enums.Category;
import com.example.Tech_Trends.repositories.FavoriteRepository;
import com.example.Tech_Trends.repositories.TrendRepository;
import com.example.Tech_Trends.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@Profile("!test")
public class FakeData {

    @Bean
    @Order(1)
    public CommandLineRunner initUserData(UserRepository userRepository) {
        return args -> {
            if(userRepository.count() == 0) {
                List<User> userList = List.of(
                        new User("Alice Wonder", "alice@example.com","12345678l"),
                        new User("Bob Builder","bob@example.com", "12345678l"),
                        new User("Charlie Brown","charlie@example.com", "12345678l"),
                        new User("Diana Prince","diana@example.com", "12345678l"),
                        new User("Edward Snow","edward@example.com", "12345678l"),
                        new User("Frank Castle","frank@example.com", "12345678l")
                );
                userRepository.saveAll(userList);
            }
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner initTrendData(TrendRepository trendRepository, UserRepository userRepository) {
        return args -> {
            if(trendRepository.count() == 0) {
                List<Trend> trendList = List.of(
                        new Trend("AI advances", "Exploring new frontiers in artificial intelligence.", Category.AI, "https://pixabay.com/static/frontend/3c346409d336d5f09a7f.svg", userRepository.findById(1L).orElseThrow()),
                        new Trend("Cloud Scalability", "Best practices for scalable cloud solutions.", Category.CLOUD, "https://365datacenters.com/wp-content/uploads/2022/05/Cloud-Solutions.png", userRepository.findById(2L). orElseThrow()),
                        new Trend("Cybersecurity Trends", "Latest in data protection and security.", Category.CYBERSECURITY, "https://www.technologysolutions.net/wp-content/uploads/2023/07/cyber-security-scaled-2560x1280.jpeg", userRepository.findById(3L). orElseThrow()),
                        new Trend("Big Data Analytics", "New tools for analyzing massive datasets.", Category.DATA_SCIENCE, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOdEVPOCgN9mYSWnjMMSMM6TVXKn9pm8VtXg&s", userRepository.findById(3L). orElseThrow()),
                        new Trend("DevOps Evolution", "How DevOps is reshaping software delivery.", Category.DEVOPS, "https://miro.medium.com/v2/resize:fit:1024/0*i7j8kjRnBPO4wjNO.png", userRepository.findById(4L). orElseThrow()),
                        new Trend("Advances in Robotics", "Robots transforming industries.", Category.ROBOTICS, "https://cthings.co/hubfs/Case_study_IDC.jpg", userRepository.findById(5L). orElseThrow())
                );
                trendRepository.saveAll(trendList);
            }
        };
    }
}
