package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.Country;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, String> {

    @Query("update Country c set c.name=:newName where c.name =:country")
    @Modifying
    public void updateCountry(String country, String newName);

    public Optional<Country> findByName(String country);
}
