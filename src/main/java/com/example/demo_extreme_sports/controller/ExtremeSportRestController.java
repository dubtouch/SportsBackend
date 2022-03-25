package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.ExtremeSport;
import com.example.demo_extreme_sports.service.ExtremeSportService;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/country/{country}/region/{region}/city/{city}")
public class ExtremeSportRestController {
    private final ExtremeSportService extremeSportService;

    public ExtremeSportRestController(ExtremeSportService extremeSportService) {
        this.extremeSportService = extremeSportService;
    }

    @PostMapping("/sport")
    public void addSport(@RequestBody ExtremeSport extremeSport, @PathVariable String country, @PathVariable String region, @PathVariable String city) {
        extremeSportService.addSport(extremeSport, country, region, city);
    }

    @GetMapping("/sport/{sport}")
    public ExtremeSport findSport(@PathVariable String country, @PathVariable String region, @PathVariable String city, @PathVariable String sport) {
        return extremeSportService.findSport(country, region, city, sport);
    }

    @DeleteMapping("/sport/{sport}")
    public void deleteSport(@PathVariable String country, @PathVariable String region, @PathVariable String city, @PathVariable String sport) {
        extremeSportService.deleteSport(country, region, city, sport);
    }

    @PatchMapping("/sport/{sport}")
    @Transactional
    public void updateSport(@PathVariable String country, @PathVariable String region, @PathVariable String city, @PathVariable String sport,
                                                    @RequestParam(required = false) String newName,
                                                    @RequestParam(required = false) String availableFrom,
                                                    @RequestParam(required = false) String availableTill,
                                                    @RequestParam(required = false) String costPerDay) {
        extremeSportService.updateSport(country, region, city, sport, newName, availableFrom, availableTill, costPerDay);
    }

    @GetMapping("/sports")
    public List<ExtremeSport> getSports(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        return extremeSportService.findSports(country, region, city);
    }
}
