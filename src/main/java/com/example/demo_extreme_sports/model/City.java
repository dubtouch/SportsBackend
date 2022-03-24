package com.example.demo_extreme_sports.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "City")
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "city")
    private List<ExtremeSport> sportList;
    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExtremeSport> getSportList() {
        return sportList;
    }

    public void setSportList(List<ExtremeSport> sportList) {
        this.sportList = sportList;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
