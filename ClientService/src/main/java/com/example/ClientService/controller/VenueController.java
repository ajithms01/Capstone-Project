package com.example.ClientService.controller;

import com.example.ClientService.model.Vendor;
import com.example.ClientService.model.Venue;
import com.example.ClientService.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;

    @PostMapping("/addvenue")
    public ResponseEntity<Venue> addVenue(@RequestBody Venue venue){
        return ResponseEntity.ok(venueService.addVenue(venue));
    }

    @GetMapping("/getByLocation")
    public ResponseEntity<List<Venue>> getVenueByLocation(@RequestParam Date date, @RequestParam String location){
        return ResponseEntity.ok(venueService.getVenuesByLocationAndDate(date,location));
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long venueId){
        return ResponseEntity.ok(venueService.getVenueById(venueId));
    }
}
