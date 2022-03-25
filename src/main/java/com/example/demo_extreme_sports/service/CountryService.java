package com.example.demo_extreme_sports.service;

import com.example.demo_extreme_sports.exception.CountryAlreadyPresentException;
import com.example.demo_extreme_sports.exception.CountryNotFoundException;
import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.repositories.CountryRepository;
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
        Optional<Country> result = countryRepository.findByName(country);
        if (result.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
        } else throw new CountryAlreadyPresentException(country);
    }

    public Country findCountry(String country) {
        return countryRepository.findByName(country).
                orElseThrow(() -> new CountryNotFoundException(country));
    }

    @Transactional
    public void updateCountry(String country, String newName) {
        Optional<Country> result = countryRepository.findByName(country);
        if (result.isPresent()) countryRepository.updateCountry(country, newName);
        else throw new CountryNotFoundException(country);
    }

    public void deleteCountry(String country) {
        Optional<Country> result = countryRepository.findByName(country);
        if (result.isPresent()) countryRepository.delete(result.get());
        else throw new CountryNotFoundException(country);
    }

    public Iterable<Country> findCountries() {
        return countryRepository.findAll();
    }
}
