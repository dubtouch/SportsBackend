package com.example.demo_extreme_sports.service;

import com.example.demo_extreme_sports.exception.CountryNotFoundException;
import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.repositories.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void addCountry(String country) {
        countryRepository.findByName(country).ifPresent(() -> throw new RuntimeException());
        if (result == null) {
            result = new Country(country);
            countryRepository.save(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    public Country findCountry(String country) {
        return countryRepository.findByName(country).
                orElseThrow(CountryNotFoundException::new);
    }

    @Transactional
    public ResponseEntity<Country> updateCountry(String country, String newName) {
        Country result = countryRepository.findByName(country);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        countryRepository.updateCountry(country, newName);
        return ResponseEntity.status(HttpStatus.OK).body(countryRepository.findByName(newName));
    }

    public ResponseEntity<Country> deleteCountry(String country) {
        Country result = countryRepository.findByName(country);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        countryRepository.delete(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public Iterable<Country> findCountries() {
        Optional<Country> country = countryRepository.findById("dd");
        country.if
        return countryRepository.findAll();
    }
}
