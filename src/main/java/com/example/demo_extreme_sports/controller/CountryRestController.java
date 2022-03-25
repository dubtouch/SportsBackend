package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.service.CountryService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/{country}")
    public void addCountry(@PathVariable String country) {
        return countryService.addCountry(country);
    }

    @GetMapping("/{country}")
    public ResponseEntity<Country> findCountry(@PathVariable String country) {
        return countryService.findCountry(country);
    }

    @PatchMapping("/{country}")
    public ResponseEntity<Country> updateCountry(@PathVariable String country, @RequestParam String newName) {
        return countryService.updateCountry(country, newName);
    }

    @DeleteMapping("/{country}")
    public ResponseEntity<Country> deleteCountry(@PathVariable String country) {
        return countryService.deleteCountry(country);
    }


}
