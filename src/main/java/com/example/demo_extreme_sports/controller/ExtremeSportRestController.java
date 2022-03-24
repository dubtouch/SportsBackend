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
import java.time.LocalDate;
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

    @PostMapping("/{country}/{region}")
    public ResponseEntity<Region> addRegion(@PathVariable String country, @PathVariable String region) {
        Country countryResult = countryRepository.findByName(country);
        if (countryResult == null) {
            countryResult = new Country(country);
            countryRepository.save(countryResult);
            Region tempRegion = new Region(region, countryResult);
            regionRepository.save(tempRegion);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempRegion);
        }
        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult == null) {
            regionResult = new Region(region, countryResult);
            regionRepository.save(regionResult);
            return ResponseEntity.status(HttpStatus.CREATED).body(regionResult);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping("/{country}/{region}")
    public ResponseEntity<Region> getRegion(@PathVariable String country, @PathVariable String region) {
        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(regionResult);
    }

    @DeleteMapping("/{country}/{region}")
    public ResponseEntity<Region> deleteRegion(@PathVariable String country, @PathVariable String region) {
        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        regionRepository.delete(regionResult);
        return ResponseEntity.status(HttpStatus.OK).body(regionResult);
    }

    @PatchMapping("/{country}/{region}")
    @Transactional
    public ResponseEntity<Region> updateRegion(@PathVariable String country, @PathVariable String region, @RequestParam String newName) {
        Region result = regionRepository.findRegionByNameAndCountry(country, region);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        regionRepository.updateRegion(result.getId(), newName);
        return ResponseEntity.status(HttpStatus.OK).body(regionRepository.findRegionByNameAndCountry(country, newName));
    }

    @PostMapping("/{country}/{region}/{city}")
    public ResponseEntity<City> addCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        Country countryResult = countryRepository.findByName(country);
        if (countryResult == null) {
            countryResult = new Country(country);
            countryRepository.save(countryResult);
            Region tempRegion = new Region(region, countryResult);
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
        }
        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult == null) {
            regionResult = new Region(region, countryResult);
            regionRepository.save(regionResult);
            City tempCity = new City(city, regionResult);
            cityRepository.save(tempCity);
            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
        }
        City cityResult = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (cityResult == null) {
            City tempCity = new City(city, regionResult);
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

    @PatchMapping("/{country}/{region}/{city}")
    @Transactional
    public ResponseEntity<City> updateRegion(@PathVariable String country, @PathVariable String region, @PathVariable String city, @RequestParam String newName) {
        City result = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        cityRepository.updateCity(result.getId(), newName);
        return ResponseEntity.status(HttpStatus.OK).body(cityRepository.findCityByNameAndRegionAndCountry(country, region, newName));
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
        Country countryResult = countryRepository.findByName(country);
        if (countryResult == null) {
            countryResult = new Country(country);
            countryRepository.save(countryResult);
            Region tempRegion = new Region(region, countryResult);
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(extremeSport);
        }
        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult == null) {
            regionResult = new Region(region, countryResult);
            regionRepository.save(regionResult);
            City tempCity = new City(city, regionResult);
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(extremeSport);
        }
        City tempCity = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (tempCity == null) {
            tempCity = new City(city, regionResult);
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

    @PatchMapping("/{country}/{region}/{city}/{sport}")
    @Transactional
    public ResponseEntity<ExtremeSport> updateSport(@PathVariable String country, @PathVariable String region, @PathVariable String city, @PathVariable String sport,
                                                    @RequestParam(required = false) String newName,
                                                    @RequestParam(required = false) String availableFrom,
                                                    @RequestParam(required = false) String availableTill) {
        ExtremeSport result = extremeSportRepository.findExtremeSport(country, region, city, sport);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        String passedName = newName == null ? result.getName() : newName;
        LocalDate passedAvailableFrom = availableFrom == null ? result.getAvailableFrom() : LocalDate.parse(availableFrom);
        LocalDate passedAvailableTill = availableTill == null ? result.getAvailableTill() : LocalDate.parse(availableTill);
        extremeSportRepository.updateExtremeSport(result.getId(), passedName, passedAvailableFrom, passedAvailableTill);
        return ResponseEntity.status(HttpStatus.OK).body(extremeSportRepository.findExtremeSport(country, region, city, sport));
    }
}
