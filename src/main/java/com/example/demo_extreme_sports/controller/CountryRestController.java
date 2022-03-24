package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.repositories.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class CountryRestController {
    private final CountryRepository countryRepository;

    public CountryRestController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping("/{country}")
    public ResponseEntity<Country> addCountry(@PathVariable String country) {
        Country result = countryRepository.findByName(country);
        if (result == null) {
            result = new Country(country);
            countryRepository.save(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping("/{country}")
    public ResponseEntity<Country> getCountry(@PathVariable String country) {
        Country result = countryRepository.findByName(country);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{country}")
    @Transactional
    public ResponseEntity<Country> updateCountry(@PathVariable String country, @RequestParam String newName) {
        Country result = countryRepository.findByName(country);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        countryRepository.updateCountry(country, newName);
        return ResponseEntity.status(HttpStatus.OK).body(countryRepository.findByName(newName));
    }

    @DeleteMapping("/{country}")
    public ResponseEntity<Country> deleteCountry(@PathVariable String country) {
        Country result = countryRepository.findByName(country);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        countryRepository.delete(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/countries")
    public Iterable<Country> getCountries() {
        return countryRepository.findAll();
    }
}
