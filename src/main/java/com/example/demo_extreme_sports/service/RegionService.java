package com.example.demo_extreme_sports.service;

import com.example.demo_extreme_sports.exception.AlreadyPresentException;
import com.example.demo_extreme_sports.exception.NotFoundException;
import com.example.demo_extreme_sports.model.Country;
import com.example.demo_extreme_sports.model.Region;
import com.example.demo_extreme_sports.repositories.CountryRepository;
import com.example.demo_extreme_sports.repositories.RegionRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    public RegionService(CountryRepository countryRepository, RegionRepository regionRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
    }

    public void addRegion(String country, String region) {
        Optional<Country> countryResult = countryRepository.findByName(country);
        if (countryResult.isEmpty()) {
            Country tempCountry = new Country(country);
            countryRepository.save(tempCountry);
            Region tempRegion = new Region(region, tempCountry);
            regionRepository.save(tempRegion);
            return;
        }
        Optional<Region> regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult.isEmpty()) {
            Region tempRegion = new Region(region, countryResult.get());
            regionRepository.save(tempRegion);
            return;
        }
        throw new AlreadyPresentException("Region " + region);
    }

    public Region findRegion(String country, String region) {
        return regionRepository.findRegionByNameAndCountry(country, region)
                .orElseThrow(() -> new NotFoundException("Region " + region));
    }

    public List<Region> findRegions(String country) {
        Optional<Country> result = countryRepository.findByName(country);
        if (result.isEmpty()) throw new NotFoundException("Country " + country);
        else return regionRepository.findRegions(country);

    }

    public void deleteRegion(String country, String region) {
        Optional<Region> regionResult = regionRepository.findRegionByNameAndCountry(country, region);
        if (regionResult.isEmpty()) throw new NotFoundException("Region " + region);
        else regionRepository.delete(regionResult.get());
    }

    @Transactional
    public void updateRegion(String country, String region, String newName) {
        Optional<Region> result = regionRepository.findRegionByNameAndCountry(country, region);
        if (result.isEmpty()) throw new NotFoundException("Region " + region);
        else regionRepository.updateRegion(result.get().getId(), newName);
    }
}
