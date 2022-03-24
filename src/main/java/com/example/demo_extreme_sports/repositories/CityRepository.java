package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query("select ct from City ct join ct.region r join r.country c where ct.name=:city and r.name=:region and c.name=:country")
    public City findCityByNameAndRegionAndCountry(String country, String region, String city);
}
