package com.example.demo_extreme_sports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Region")
@Table(name = "region")
public class Region {
    @Id
    private String name;
    @OneToMany(mappedBy = "region")
    private List<City> cityList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void addCity(City city) {
        cityList.add(city);
        city.setRegion(this);
    }

}
