package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.Region;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<Region, String> {

    @Query("select r from Region r inner join r.country c where r.name = :region and c.name =:country")
    public Optional<Region> findRegionByNameAndCountry(String country, String region);

    @Modifying
    @Query("update Region r set r.name=:newName where id=:id")
    public void updateRegion(Long id, String newName);

    @Query("select r from Region r inner join r.country c where c.name=:country")
    public List<Region> findRegions(String country);
}
