package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.ExtremeSport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExtremeSportRepository extends CrudRepository<ExtremeSport, Long> {

    @Query("select es from ExtremeSport es join es.city ct join ct.region r join r.country c where ct.name=:city and r.name=:region and c.name=:country and es.name=:sport")
    public ExtremeSport findExtremeSport(String country, String region, String city, String sport);
}
