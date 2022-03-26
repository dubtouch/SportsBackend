package com.example.demo_extreme_sports.controller.view;

import com.example.demo_extreme_sports.model.ExtremeSport;

import java.math.BigDecimal;


public class BestSportResponse {
    private String name;
    private BigDecimal cost;
    private String country;
    private String region;
    private String city;

    public BestSportResponse(ExtremeSport extremeSport, long duration) {
        this.name = extremeSport.getName();
        this.cost = extremeSport.getCostPerDay().multiply(BigDecimal.valueOf(duration));
        this.country = extremeSport.getCity().getRegion().getCountry().getName();
        this.region = extremeSport.getCity().getRegion().getName();
        this.city = extremeSport.getCity().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
