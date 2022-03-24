package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.City;
import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.model.ExtremeSport;
import com.example.demo_extreme_sports.model.Region;
import com.example.demo_extreme_sports.repositories.CityRepository;
import com.example.demo_extreme_sports.repositories.CountryRepository;
import com.example.demo_extreme_sports.repositories.ExtremeSportRepository;
import com.example.demo_extreme_sports.repositories.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExtremeSportRestController {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;
    private final ExtremeSportRepository extremeSportRepository;

    public ExtremeSportRestController(CountryRepository countryRepository, RegionRepository regionRepository, CityRepository cityRepository, ExtremeSportRepository extremeSportRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
        this.extremeSportRepository = extremeSportRepository;
    }

    @PostMapping("/{country}")
    public ResponseEntity<Country> addCountry(@PathVariable String country) {
        Optional<Country> result = countryRepository.findById(country);
        if (result.isEmpty()) {
            Country temp = new Country();
            temp.setName(country);
            countryRepository.save(temp);
            return ResponseEntity.status(HttpStatus.CREATED).body(temp);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result.get());
    }

    @GetMapping("/{country}")
    public ResponseEntity<Country> getCountry(@PathVariable String country) {
        Optional<Country> result = countryRepository.findById(country);
        if (result.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @GetMapping("countries")
    public Iterable<Country> getCountries() {
        return countryRepository.findAll();
    }

    @PostMapping("/{country}/{region}")
    public void addRegion(@PathVariable String country, @PathVariable String region) {
        Country countryResult = countryRepository.findById(country).get();
        Optional<Region> regionResult = regionRepository.findById(region);
        if (regionResult.isPresent()) {
            countryResult.addRegion(regionResult.get());
            regionRepository.save(regionResult.get());
        } else  {
            Region temp = new Region();
            temp.setName(region);
            countryResult.addRegion(temp);
            regionRepository.save(temp);
        }

        countryRepository.save(countryResult);

    }


    public Region getRegion(@PathVariable String country, @PathVariable String region) {
        return regionRepository.findById(region).get();
    }

    @PostMapping("/{country}/{region}/{city}")
    public void addCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {

    }

    @GetMapping("/{country}/{region}/{city}")
    public City getCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        return null;
    }

    @PostMapping("/{country}/{region}/{city}/sport")
    public void addSport(@RequestBody ExtremeSport extremeSport, @PathVariable String country, @PathVariable String region, @PathVariable String city) {


    }


}
