package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.service.CountryService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public Iterable<Country> findCountries() {
        return countryService.findCountries();
    }

    @PostMapping("/country/{country}")
    public void addCountry(@PathVariable String country) {
        countryService.addCountry(country);
    }

    @GetMapping("/country/{country}")
    public Country findCountry(@PathVariable String country) {
        return countryService.findCountry(country);
    }

    @PatchMapping("/country/{country}")
    public void updateCountry(@PathVariable String country, @RequestParam String newName) {
        countryService.updateCountry(country, newName);
    }

    @DeleteMapping("/country/{country}")
    public void deleteCountry(@PathVariable String country) {
        countryService.deleteCountry(country);
    }
}
