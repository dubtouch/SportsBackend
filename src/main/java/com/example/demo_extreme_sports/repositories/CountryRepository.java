package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.Country;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {

    @Query("update Country c set c.name=:newName where c.name =:country")
    @Modifying
    public void updateCountry(String country, String newName);

    @Query("select c from Country c where c.name=:country")
    public Country findByName(String country);
}
