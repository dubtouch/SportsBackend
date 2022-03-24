package com.example.demo_extreme_sports.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "Country")
@Table(name = "country")
public class Country {
    @Id
    private String name;
    @OneToMany(mappedBy = "country")
    private List<Region> regionList;

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
