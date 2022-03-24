package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region, String> {

    @Query("select r from Region r inner join r.country c where r.name = :region and c.name =:country")
    public Region findRegionByIdAndCountryName(String country, String region);
}
