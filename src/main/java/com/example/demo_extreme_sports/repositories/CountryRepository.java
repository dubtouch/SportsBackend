package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {
}
