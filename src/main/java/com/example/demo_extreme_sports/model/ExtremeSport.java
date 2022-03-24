package com.example.demo_extreme_sports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "ExtremeSport")
@Table(name = "extreme_sport")
public class ExtremeSport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sport_name")
    private String name;
    private LocalDate availableFrom;
    private LocalDate availableTill;
    private BigDecimal costPerDay;

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "city_name")
    private City city;

    public City getCity() {
        return city;
    }

    public Long getId() {
        return id;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDate getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(LocalDate availableTill) {
        this.availableTill = availableTill;
    }
}
