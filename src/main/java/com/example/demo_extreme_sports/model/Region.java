package com.example.demo_extreme_sports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Region")
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "region_name")
    private String name;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<City> cityList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_name")
    @JsonIgnore
    private Country country;


    public Region(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Region() {}

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
