package com.example.Tech_Trends.services;

import com.example.Tech_Trends.cloudinary.CloudinaryProvider;
import com.example.Tech_Trends.dtos.TrendRequest;
import com.example.Tech_Trends.dtos.TrendResponse;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;
import com.example.Tech_Trends.exceptions.TechTrendExistingUserException;
import com.example.Tech_Trends.exceptions.TechTrendNotFoundException;
import com.example.Tech_Trends.mappers.TrendMapper;
import com.example.Tech_Trends.repositories.TrendRepository;
import com.example.Tech_Trends.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TrendService {

    private final TrendRepository trendRepository;
    private final UserRepository userRepository;

    public TrendService(TrendRepository trendRepository,
                        UserRepository userRepository) {
        this.trendRepository = trendRepository;
        this.userRepository = userRepository;
    }

    public TrendResponse createTrend(TrendRequest trendRequest) {
        Optional<User> optionalUser = userRepository.findById(trendRequest.userId());
        if(optionalUser.isEmpty()) {
            throw new TechTrendNotFoundException("There is no user with this id.");
        }

        User user = optionalUser.get();

        String imageUrl = "";
        if (trendRequest.imgUrl() != null && !trendRequest.imgUrl().trim().isEmpty()) {
            Map<String, Object> imageUpload = CloudinaryProvider.uploadImage(trendRequest.imgUrl());
            imageUrl = imageUpload.get("url").toString();
        }

        Optional<Trend> existingTrend = trendRepository.findByTitleAndUser(trendRequest.title(), user);
        if(existingTrend.isPresent()) {
            throw new TechTrendExistingUserException("Trend with this title already exists for the user.");
        }

        Trend trend = TrendMapper.toEntity(trendRequest, optionalUser.get(), imageUrl);
        Trend savedTrend = trendRepository.save(trend);
        return TrendMapper.toResponse(savedTrend);
    }

    public List<TrendResponse> getAllTrends() {
        List<Trend> trendList = trendRepository.findAll();
        return trendList.stream()
                .map(TrendMapper::toResponse).toList();
    }

    public TrendResponse findById(Long id) {
        Optional<Trend> optionalTrend = trendRepository.findById(id);

        if(optionalTrend.isEmpty()) {
            throw new TechTrendNotFoundException("The user with id " + id + " does not exist.");
        }
        Trend trend = optionalTrend.get();
        return TrendMapper.toResponse(trend);
    }

    public TrendResponse updateTrendById(Long id, TrendRequest trendRequest) {
        Optional<Trend> optionalTrend = trendRepository.findById(id);

        if(optionalTrend.isPresent()) {
            Trend trend = optionalTrend.get();

            trend.setTitle(trendRequest.title());
            trend.setDescription(trendRequest.description());
            trend.setImgUrl(trendRequest.imgUrl());

            Trend updatedTrend = trendRepository.save(trend);
            return TrendMapper.toResponse(updatedTrend);
        }
        throw new TechTrendNotFoundException("The trend with id " + id + " does not exist.");
    }

    public void deleteTrendById(Long id) {
            Optional<Trend> optionalTrend = trendRepository.findById(id);

            if (optionalTrend.isEmpty()) {
                throw new TechTrendNotFoundException("The trend with id " + id + " does not exist.");
            }
            trendRepository.deleteById(id);
        }
}

