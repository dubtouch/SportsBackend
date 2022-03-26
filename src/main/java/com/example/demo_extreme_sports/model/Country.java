package com.example.demo_extreme_sports.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Country")
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;
    @Column(name = "country_name")
    private String name;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Region> regionList;

    public Country(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public void addRegion(Region region) {
        regionList.add(region);
        region.setCountry(this);
    }
}
