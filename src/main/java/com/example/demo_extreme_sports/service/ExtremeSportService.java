package com.example.demo_extreme_sports.service;

import com.example.demo_extreme_sports.controller.view.BestSportResponse;
import com.example.demo_extreme_sports.exception.AlreadyPresentException;
import com.example.demo_extreme_sports.exception.NotFoundException;
import com.example.demo_extreme_sports.model.*;
import com.example.demo_extreme_sports.repositories.CityRepository;
import com.example.demo_extreme_sports.repositories.CountryRepository;
import com.example.demo_extreme_sports.repositories.ExtremeSportRepository;
import com.example.demo_extreme_sports.repositories.RegionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExtremeSportService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;
    private final ExtremeSportRepository extremeSportRepository;

    public ExtremeSportService(CountryRepository countryRepository, RegionRepository regionRepository, CityRepository cityRepository, ExtremeSportRepository extremeSportRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
        this.extremeSportRepository = extremeSportRepository;
    }

    public List<BestSportResponse> findBestLocations(Set<String> sports, String from, String till) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate tillDate = LocalDate.parse(till);
        long duration = ChronoUnit.DAYS.between(fromDate, tillDate);
        List<ExtremeSport> extremeSportList = extremeSportRepository.findBestLocations(fromDate, tillDate, sports);
        return extremeSportList
                .stream()
                .map(extremeSport -> new BestSportResponse(extremeSport, duration))
                .collect(Collectors.toList());
    }

    public void addSport(ExtremeSport extremeSport, String country, String region, String city) {
        Optional<Country> countryResult = countryRepository.findByName(country);
        if (countryResult.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
            Region tempRegion = new Region(region, tempCountry);
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return;
        }
        Optional<Region> regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult.isEmpty()) {
            Region tempRegion = new Region(region, countryResult.get());
            regionRepository.save(tempRegion);
            City tempCity = new City(city, tempRegion);
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return;
        }
        Optional<City> cityResult = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (cityResult.isEmpty()) {
            City tempCity = new City(city, regionResult.get());
            cityRepository.save(tempCity);
            extremeSport.setCity(tempCity);
            extremeSportRepository.save(extremeSport);
            return;
        }
        Optional<ExtremeSport> extremeSportResult = extremeSportRepository.findExtremeSport(country, region, city, extremeSport.getName());
        if (extremeSportResult.isEmpty()) {
            extremeSport.setCity(cityResult.get());
            extremeSportRepository.save(extremeSport);
        } else {
            throw new AlreadyPresentException("Extreme sport " + extremeSport.getName());
        }
    }

    public ExtremeSport findSport(String country, String region, String city, String sport) {
        return extremeSportRepository.findExtremeSport(country, region, city, sport)
                .orElseThrow(() -> new NotFoundException("Extreme sport " + sport));
    }

    public void deleteSport(String country, String region, String city, String sport) {
        Optional<ExtremeSport> extremeSport = extremeSportRepository.findExtremeSport(country, region, city, sport);
        if (extremeSport.isEmpty()) throw new NotFoundException("Extreme sport " + sport);
        else extremeSportRepository.delete(extremeSport.get());
    }

    @Transactional
    public void updateSport(String country, String region, String city, String sport,
                            String newName, String availableFrom, String availableTill, String costPerDay) {
        Optional<ExtremeSport> result = extremeSportRepository.findExtremeSport(country, region, city, sport);
        if (result.isEmpty()) throw new NotFoundException("Extreme sport " + sport);
        else {
            String passedName = newName == null ? result.get().getName() : newName;
            LocalDate passedAvailableFrom = availableFrom == null ? result.get().getAvailableFrom() : LocalDate.parse(availableFrom);
            LocalDate passedAvailableTill = availableTill == null ? result.get().getAvailableTill() : LocalDate.parse(availableTill);
            BigDecimal passedCostPerDay = costPerDay == null ? result.get().getCostPerDay() : new BigDecimal(costPerDay);
            extremeSportRepository.updateExtremeSport(result.get().getId(), passedName, passedAvailableFrom, passedAvailableTill, passedCostPerDay);
        }
    }

    public List<ExtremeSport> findSports(String country, String region, String city) {
        Optional<City> result = cityRepository.findCityByNameAndRegionAndCountry(country, region, city);
        if (result.isEmpty()) throw new NotFoundException("City " + city);
        else return extremeSportRepository.findSports(country, region, city);
    }

    public List<String> findAllSportsAvailabe() {
        return extremeSportRepository.findAllSportsAvailable();
    }
}
