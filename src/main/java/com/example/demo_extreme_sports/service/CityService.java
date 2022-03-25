package com.example.demo_extreme_sports.service;

import com.example.demo_extreme_sports.exception.CityAlreadyPresentException;
import com.example.demo_extreme_sports.exception.CityNotFoundException;
import com.example.demo_extreme_sports.exception.RegionNotFoundException;
import com.example.demo_extreme_sports.model.City;
import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.model.Region;
import com.example.demo_extreme_sports.repositories.CityRepository;
import com.example.demo_extreme_sports.repositories.CountryRepository;
import com.example.demo_extreme_sports.repositories.RegionRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;

    public CityService(CountryRepository countryRepository, RegionRepository regionRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
    }

    public void addCity(String country, String region, String city) {
        Optional<Country> countryResult = countryRepository.findByName(country);
        if (countryResult.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
            Region tempRegion = new Region(region, tempCountry);
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            return;
        }
        Optional<Region> regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult.isEmpty()) {
            Region tempRegion = new Region(region, countryResult.get());
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            return;
        }
        Optional<City> cityResult = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (cityResult.isEmpty()) {
            City tempCity = new City(city, regionResult.get());
            cityRepository.save(tempCity);
            return;
        }
        throw new CityAlreadyPresentException(city);
    }

    public City findCity(String country, String region, String city) {
        return cityRepository.findCityByNameAndRegionAndCountry(country, region, city)
                .orElseThrow(() -> new CityNotFoundException(city));
    }

    @Transactional
    public void updateCity(String country, String region, String city, String newName) {
        Optional<City> result = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (result.isEmpty()) throw new CityNotFoundException(city);
        else cityRepository.updateCity(result.get().getId(), newName);
    }

    public void deleteCity(String country, String region, String city) {
        Optional<City> tempCity = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (tempCity.isEmpty()) throw new CityNotFoundException(city);
        else cityRepository.delete(tempCity.get());
    }

    public List<City> findCities(String country, String region) {
        Optional<Region> result = regionRepository.findRegionByNameAndCountry(country, region);
        if (result.isEmpty()) throw new RegionNotFoundException(region);
        else return cityRepository.findCities(country, region);
    }
}

