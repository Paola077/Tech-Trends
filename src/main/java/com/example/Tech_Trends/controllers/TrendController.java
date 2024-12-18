package com.example.Tech_Trends.controllers;

import com.example.Tech_Trends.dtos.TrendRequest;
import com.example.Tech_Trends.dtos.TrendResponse;
import com.example.Tech_Trends.services.TrendService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trends")
public class TrendController {

    private final TrendService trendService;

    public TrendController(TrendService trendService) {
        this.trendService = trendService;
    }

    @PostMapping
    public ResponseEntity<TrendResponse> addTrend(@RequestBody TrendRequest trendRequest) {
        TrendResponse trendResponse = trendService.createTrend(trendRequest);
        return new ResponseEntity<>(trendResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrendResponse>> getAll() {
        List<TrendResponse> trendList = trendService.getAllTrends();
        return new ResponseEntity<>(trendList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrendResponse> getTrendById (@PathVariable Long id) {
        TrendResponse trendResponse = trendService.findById(id);
        return new ResponseEntity<>(trendResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrendResponse> updateTrend(@PathVariable Long id, @RequestBody TrendRequest trendRequest) {
        TrendResponse trendResponse = trendService.updateTrendById(id, trendRequest);
        return new ResponseEntity<>(trendResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrend(@PathVariable Long id) {
        trendService.deleteTrendById(id);
        return new ResponseEntity<>("The Trend has been eliminated", HttpStatus.OK);
    }
}
