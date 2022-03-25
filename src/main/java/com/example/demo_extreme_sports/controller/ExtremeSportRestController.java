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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
                                                    @RequestParam(required = false) String availableTill,
                                                    @RequestParam(required = false) String costPerDay) {
        ExtremeSport result = extremeSportRepository.findExtremeSport(country, region, city, sport);
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        String passedName = newName == null ? result.getName() : newName;
        LocalDate passedAvailableFrom = availableFrom == null ? result.getAvailableFrom() : LocalDate.parse(availableFrom);
        LocalDate passedAvailableTill = availableTill == null ? result.getAvailableTill() : LocalDate.parse(availableTill);
        BigDecimal passedCostPerDay = costPerDay == null ? result.getCostPerDay() : new BigDecimal(costPerDay);
        extremeSportRepository.updateExtremeSport(result.getId(), passedName, passedAvailableFrom, passedAvailableTill, passedCostPerDay);
        return ResponseEntity.status(HttpStatus.OK).body(extremeSportRepository.findExtremeSport(country, region, city, sport));
    }

    @GetMapping("/{country}/{region}/{city}/sports")
    public ResponseEntity<List<ExtremeSport>> getSports(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        City result = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (region == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(extremeSportRepository.getSports(country, region, city));
    }

}
