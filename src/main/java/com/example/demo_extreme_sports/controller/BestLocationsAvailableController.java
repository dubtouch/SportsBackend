package com.example.demo_extreme_sports.controller;


import com.example.demo_extreme_sports.repositories.ExtremeSportRepository;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BestLocationsAvailableController {

    private final ExtremeSportRepository extremeSportRepository;

    public BestLocationsAvailableController(ExtremeSportRepository extremeSportRepository) {
        this.extremeSportRepository = extremeSportRepository;
    }

    @GetMapping("/bestlocation")
    public List<Object[]> getLocations(@RequestParam String from , @RequestParam String till) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate tillDate = LocalDate.parse(till);
        long duration = ChronoUnit.DAYS.between(fromDate, tillDate);
        return extremeSportRepository.findBestLocations(fromDate, tillDate, BigDecimal.valueOf(duration));
    }
}
