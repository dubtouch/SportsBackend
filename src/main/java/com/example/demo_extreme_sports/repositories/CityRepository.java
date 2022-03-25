package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query("select ct from City ct join ct.region r join r.country c where ct.name=:city and r.name=:region and c.name=:country")
    public Optional<City> findCityByNameAndRegionAndCountry(String country, String region, String city);

    @Modifying
    @Query("update City c set c.name=:newName where id=:id")
    public void updateCity(Long id, String newName);

    @Query("select ct from City ct join ct.region r join r.country c where r.name=:region and c.name=:country")
    public List<City> findCities(String country, String region);
}
