package com.example.demo_extreme_sports.repositories;

import com.example.demo_extreme_sports.model.ExtremeSport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExtremeSportRepository extends CrudRepository<ExtremeSport, Long> {

    @Query("select es from ExtremeSport es join es.city ct join ct.region r join r.country c where ct.name=:city and r.name=:region and c.name=:country and es.name=:sport")
    public Optional<ExtremeSport> findExtremeSport(String country, String region, String city, String sport);

    @Modifying
    @Query("update ExtremeSport es set es.name=:newName, es.availableFrom=:availableFrom, es.availableTill=:availableTill, es.costPerDay=:costPerDay where id=:id")
    public void updateExtremeSport(Long id, String newName, LocalDate availableFrom, LocalDate availableTill, BigDecimal costPerDay);

    @Query("select es from ExtremeSport es join es.city ct join ct.region r join r.country c where ct.name=:city and r.name=:region and c.name=:country")
    public List<ExtremeSport> findSports(String country, String region, String city);

    @Query("select es " +
            "from ExtremeSport es " +
            "where es.availableFrom<=:from and es.availableTill>=:till and es.name in (:sports)" +
            "order by es.costPerDay asc")
    public List<ExtremeSport> findBestLocations(LocalDate from, LocalDate till, Set<String> sports);

    @Query("select distinct es.name from ExtremeSport es")
    public List<String> findAllSportsAvailable();
}
