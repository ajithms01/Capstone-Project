package com.example.ClientService.service;

import com.example.ClientService.model.Vendor;
import com.example.ClientService.model.Venue;
import com.example.ClientService.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> getVenuesByLocationAndDate(Date date, String location) {
        return venueRepository.findVenuesByLocationAndDate(location, date);
    }

    public Venue addVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue getVenueById(Long venueId) {
        return venueRepository.findById(venueId).orElse(null);
    }

    public Venue addDate(Long venueId, Date date) {
        Venue venue= venueRepository.getById(venueId);
        venue.getBookedDates().add(date);
        return venueRepository.save(venue);
    }
}
