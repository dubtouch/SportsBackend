package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.model.Region;
import com.example.demo_extreme_sports.service.RegionService;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class RegionRestController {
    private final RegionService regionService;

    public RegionRestController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/{country}/{region}")
    public void addRegion(@PathVariable String country, @PathVariable String region) {
        regionService.addRegion(country, region);
    }

    @GetMapping("/{country}/{region}")
    public Region findRegion(@PathVariable String country, @PathVariable String region) {
        return regionService.findRegion(country, region);
    }


    @GetMapping("/{country}/regions")
    public List<Region> getRegions(@PathVariable String country) {
        return regionService.findRegions(country);

    }

    @DeleteMapping("/{country}/{region}")
    public void deleteRegion(@PathVariable String country, @PathVariable String region) {
        regionService.deleteRegion(country, region);
    }

    @PatchMapping("/{country}/{region}")
    @Transactional
    public void updateRegion(@PathVariable String country, @PathVariable String region, @RequestParam String newName) {
        regionService.updateRegion(country, region, newName);
    }
}
