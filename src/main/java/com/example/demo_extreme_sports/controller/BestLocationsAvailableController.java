package com.example.demo_extreme_sports.controller;


import com.example.demo_extreme_sports.repositories.ExtremeSportRepository;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    public List<Object[]> getLocations(@RequestBody List<String> sports, @RequestParam String from , @RequestParam String till) {
        return extremeSportRepository.findBestLocations().stream()
                .filter(x -> ((LocalDate)x[1]).compareTo(LocalDate.parse(from)) <= 0
                        && ((LocalDate)x[2]).compareTo(LocalDate.parse(till)) >= 0
                        && sports.contains((String) x[0]))
                .sorted(Comparator.comparing(x -> (BigDecimal)x[3]))
                .collect(Collectors.toList());
    }
}
