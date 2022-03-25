package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.model.Region;
import com.example.demo_extreme_sports.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

//@RestController
//public class RegionRestController {
//    private final RegionService regionService;
//
//    public RegionRestController(RegionService regionService) {
//        this.regionService = regionService;
//    }
//
//    @PostMapping("/{country}/{region}")
//    public ResponseEntity<Region> addRegion(@PathVariable String country, @PathVariable String region) {
//        Country countryResult = countryRepository.findByName(country);
//        if (countryResult == null) {
//            countryResult = new Country(country);
//            countryRepository.save(countryResult);
//            Region tempRegion = new Region(region, countryResult);
//            regionRepository.save(tempRegion);
//            return ResponseEntity.status(HttpStatus.CREATED).body(tempRegion);
//        }
//        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
//        if (regionResult == null) {
//            regionResult = new Region(region, countryResult);
//            regionRepository.save(regionResult);
//            return ResponseEntity.status(HttpStatus.CREATED).body(regionResult);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//    }
//
//    @GetMapping("/{country}/{region}")
//    public ResponseEntity<Region> getRegion(@PathVariable String country, @PathVariable String region) {
//        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
//        if (regionResult == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        return ResponseEntity.status(HttpStatus.OK).body(regionResult);
//    }
//
//
//    @GetMapping("/{country}/regions")
//    public ResponseEntity<List<Region>> getRegions(@PathVariable String country) {
//        Country result = countryRepository.findByName(country);
//        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        return ResponseEntity.status(HttpStatus.OK).body(regionRepository.getRegions(country));
//
//    }
//
//    @DeleteMapping("/{country}/{region}")
//    public ResponseEntity<Region> deleteRegion(@PathVariable String country, @PathVariable String region) {
//        Region regionResult = regionRepository.findRegionByNameAndCountry(country, region);
//        if (regionResult == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        regionRepository.delete(regionResult);
//        return ResponseEntity.status(HttpStatus.OK).body(regionResult);
//    }
//
//    @PatchMapping("/{country}/{region}")
//    @Transactional
//    public ResponseEntity<Region> updateRegion(@PathVariable String country, @PathVariable String region, @RequestParam String newName) {
//        Region result = regionRepository.findRegionByNameAndCountry(country, region);
//        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        regionRepository.updateRegion(result.getId(), newName);
//        return ResponseEntity.status(HttpStatus.OK).body(regionRepository.findRegionByNameAndCountry(country, newName));
//    }
//}
