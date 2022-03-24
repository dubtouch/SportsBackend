package com.example.demo_extreme_sports.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "ExtremeSport")
@Table(name = "extreme_sport")
public class ExtremeSport {
    @Id
    @GeneratedValue
    private Long id;
    private ExtremeSportType sportType;
    private LocalDate availableFrom;
    private LocalDate availableTill;
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    public ExtremeSportType getSportType() {
        return sportType;
    }

    public void setSportType(ExtremeSportType sportType) {
        this.sportType = sportType;
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
