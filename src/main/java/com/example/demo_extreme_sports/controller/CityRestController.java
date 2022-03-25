//package com.example.demo_extreme_sports.controller;
//
//import com.example.demo_extreme_sports.model.City;
//import com.example.demo_extreme_sports.model.Country;
//import com.example.demo_extreme_sports.model.Region;
//import com.example.demo_extreme_sports.repositories.CityRepository;
//import com.example.demo_extreme_sports.repositories.CountryRepository;
//import com.example.demo_extreme_sports.repositories.RegionRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@RestController
//public class CityRestController {
//    private final CountryRepository countryRepository;
//    private final RegionRepository regionRepository;
//    private final CityRepository cityRepository;
//
//    public CityRestController(CountryRepository countryRepository, RegionRepository regionRepository, CityRepository cityRepository) {
//        this.countryRepository = countryRepository;
//        this.regionRepository = regionRepository;
//        this.cityRepository = cityRepository;
//    }
//
//    @PostMapping("/{country}/{region}/{city}")
//    public ResponseEntity<City> addCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
//        Country countryResult = countryRepository.findByName(country);
//        if (countryResult == null) {
//            countryResult = new Country(country);
//            countryRepository.save(countryResult);
//            Region tempRegion = new Region(region, countryResult);
//            regionRepository.save(tempRegion);
//            City tempCity = new City(city, tempRegion);
//            cityRepository.save(tempCity);
//            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
//        }
//        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
//        if (regionResult == null) {
//            regionResult = new Region(region, countryResult);
//            regionRepository.save(regionResult);
//            City tempCity = new City(city, regionResult);
//            cityRepository.save(tempCity);
//            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
//        }
//        City cityResult = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
//        if (cityResult == null) {
//            City tempCity = new City(city, regionResult);
//            cityRepository.save(tempCity);
//            return ResponseEntity.status(HttpStatus.CREATED).body(tempCity);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//    }
//
//    @GetMapping("/{country}/{region}/{city}")
//    public ResponseEntity<City> getCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
//        City tempCity = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
//        if (tempCity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        return ResponseEntity.status(HttpStatus.OK).body(tempCity);
//    }
//
//    @PatchMapping("/{country}/{region}/{city}")
//    @Transactional
//    public ResponseEntity<City> updateRegion(@PathVariable String country, @PathVariable String region, @PathVariable String city, @RequestParam String newName) {
//        City result = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
//        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        cityRepository.updateCity(result.getId(), newName);
//        return ResponseEntity.status(HttpStatus.OK).body(cityRepository.findCityByNameAndRegionAndCountry(country, region, newName));
//    }
//
//    @DeleteMapping("/{country}/{region}/{city}")
//    public ResponseEntity<City> deleteCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
//        City tempCity = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
//        if (tempCity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        cityRepository.delete(tempCity);
//        return ResponseEntity.status(HttpStatus.OK).body(tempCity);
//    }
//
//    @GetMapping("/{country}/{region}/cities")
//    public ResponseEntity<List<City>> getCities(@PathVariable String country, @PathVariable String region) {
//        Region result = regionRepository.findRegionByNameAndCountry(country, region);
//        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        return ResponseEntity.status(HttpStatus.OK).body(cityRepository.getCities(country, region));
//    }
//}
