package com.example.demo_extreme_sports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "City")
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private Long id;
    @Column(name = "city_name")
    private String name;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<ExtremeSport> extremeSportList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "region_id")
    private Region region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExtremeSport> getExtremeSportList() {
        return extremeSportList;
    }

    public void setExtremeSportList(List<ExtremeSport> extremeSportList) {
        this.extremeSportList = extremeSportList;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public City(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public City() {};

    public void addExtremeSport(ExtremeSport extremeSport) {
        extremeSportList.add(extremeSport);
        extremeSport.setCity(this);
    }
}
