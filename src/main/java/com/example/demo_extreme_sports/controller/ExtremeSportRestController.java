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

import javax.transaction.Transactional;
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
        Optional<Country> countryOptional = countryRepository.findById(country);
        if (countryOptional.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempCountry);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping("/{country}")
    public ResponseEntity<Country> getCountry(@PathVariable String country) {
        Optional<Country> result = countryRepository.findById(country);
        if (result.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @PatchMapping("/{country}")
    @Transactional
    public ResponseEntity<Country> updateCountry(@PathVariable String country, @RequestParam String newName) {

    }

    @DeleteMapping("/{country}")
    public ResponseEntity<Country> deleteCountry(@PathVariable String country) {
        Optional<Country> result = countryRepository.findById(country);
        if (result.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        countryRepository.delete(result.get());
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @GetMapping("/countries")
    public Iterable<Country> getCountries() {
        return countryRepository.findAll();
    }

    @PostMapping("/{country}/{region}")
    public ResponseEntity<Region> addRegion(@PathVariable String country, @PathVariable String region) {
        Optional<Country> countryResult = countryRepository.findById(country);
        if (countryResult.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
            Region tempRegion = new Region(region, tempCountry);
            regionRepository.save(tempRegion);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempRegion);
        }
        Optional<Region> regionResult = regionRepository.findById(region);
        if (regionResult.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else  {
            Region tempRegion = new Region(region, countryResult.get());
            regionRepository.save(tempRegion);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempRegion);
        }
    }

    @GetMapping("/{country}/{region}")
    public ResponseEntity<Region> getRegion(@PathVariable String country, @PathVariable String region) {
        Region regionResult = regionRepository.findRegionByIdAndCountryName(country, region);
        if (regionResult == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(regionResult);
    }

    @DeleteMapping("/{country}/{region}")
    public ResponseEntity<Region> deleteRegion(@PathVariable String country, @PathVariable String region) {
        Region regionResult = regionRepository.findRegionByIdAndCountryName(country, region);
        if (regionResult == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        regionRepository.delete(regionResult);
        return ResponseEntity.status(HttpStatus.OK).body(regionResult);
    }

    @PostMapping("/{country}/{region}/{city}")
    public ResponseEntity<City> addCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        Optional<Country> countryOptional = countryRepository.findById(country);
        if (countryOptional.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
            Region tempRegion = new Region(region, tempCountry);
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
        }
        Optional<Region> regionOptional = regionRepository.findById(region);
        if (regionOptional.isEmpty()) {
            Region tempRegion = new Region(region, countryOptional.get());
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
        }
        City cityOptional = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (cityOptional == null) {
            City tempCity = new City(city, regionOptional.get());
            cityRepository.save(tempCity);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping("/{country}/{region}/{city}")
    public ResponseEntity<City> getCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        City tempCity = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (tempCity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(tempCity);
    }

    @DeleteMapping("/{country}/{region}/{city}")
    public ResponseEntity<City> deleteCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        City tempCity = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (tempCity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        cityRepository.delete(tempCity);
        return ResponseEntity.status(HttpStatus.OK).body(tempCity);
    }

    @PostMapping("/{country}/{region}/{city}/sport")
    public ResponseEntity<ExtremeSport> addSport(@RequestBody ExtremeSport extremeSport, @PathVariable String country, @PathVariable String region, @PathVariable String city) {
        Optional<Country> countryOptional = countryRepository.findById(country);
        if (countryOptional.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
            Region tempRegion = new Region(region, tempCountry);
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(extremeSport);
        }
        Optional<Region> regionOptional = regionRepository.findById(region);
        if (regionOptional.isEmpty()) {
            Region tempRegion = new Region(region, countryOptional.get());
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(extremeSport);
        }
        City tempCity = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (tempCity == null) {
            tempCity = new City(city, regionOptional.get());
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(extremeSport);
        }
        ExtremeSport tempExtremeSport = extremeSportRepository.findExtremeSport(country, region, city, extremeSport.getName());
        if (tempExtremeSport == null) {
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(extremeSport);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
    @GetMapping("/{country}/{region}/{city}/{sport}")
    public ResponseEntity<ExtremeSport> getSport(@PathVariable String country, @PathVariable String region, @PathVariable String city, @PathVariable String sport) {
        ExtremeSport extremeSport = extremeSportRepository.findExtremeSport(country, region, city, sport);
        if (extremeSport == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(extremeSport);
    }

    @DeleteMapping("/{country}/{region}/{city}/{sport}")
    public ResponseEntity<ExtremeSport> deleteSport(@PathVariable String country, @PathVariable String region, @PathVariable String city, @PathVariable String sport) {
        ExtremeSport extremeSport = extremeSportRepository.findExtremeSport(country, region, city, sport);
        if (extremeSport == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        extremeSportRepository.delete(extremeSport);
        return ResponseEntity.status(HttpStatus.OK).body(extremeSport);
    }
}
