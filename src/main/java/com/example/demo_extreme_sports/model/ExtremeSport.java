package com.example.demo_extreme_sports.model;

import java.time.LocalDate;

public class ExtremeSport {
    private ExtremeSportType sportType;
    private LocalDate availableFrom;
    private LocalDate availableTill;
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
