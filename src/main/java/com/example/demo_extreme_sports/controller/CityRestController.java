package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.City;
import com.example.demo_extreme_sports.service.CityService;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class CityRestController {
    private final CityService cityService;

    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/{country}/{region}/{city}")
    public void addCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        cityService.addCity(country, region, city);
    }

    @GetMapping("/{country}/{region}/{city}")
    public City findCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        return cityService.findCity(country, region, city);
    }

    @PatchMapping("/{country}/{region}/{city}")
    @Transactional
    public void updateRegion(@PathVariable String country, @PathVariable String region, @PathVariable String city, @RequestParam String newName) {
        cityService.updateCity(country, region, city, newName);
    }

    @DeleteMapping("/{country}/{region}/{city}")
    public void deleteCity(@PathVariable String country, @PathVariable String region, @PathVariable String city) {
        cityService.deleteCity(country, region, city);
    }

    @GetMapping("/{country}/{region}/cities")
    public List<City> getCities(@PathVariable String country, @PathVariable String region) {
        return cityService.findCities(country, region);
    }
}
