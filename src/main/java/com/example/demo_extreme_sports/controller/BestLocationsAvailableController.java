package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.service.ExtremeSportService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class BestLocationsAvailableController {

    private final ExtremeSportService extremeSportService;

    public BestLocationsAvailableController(ExtremeSportService extremeSportService) {
        this.extremeSportService = extremeSportService;
    }

    @GetMapping("/allsports")
    public List<String> findAllSportsAvailable() {
        return extremeSportService.findAllSportsAvailabe();
    }

    @GetMapping("/bestlocation")
    public List<Object[]> findBestLocations(@RequestBody Set<String> sports,@RequestParam String from , @RequestParam String till) {
        return extremeSportService.findBestLocations(sports, from, till);
    }
}
