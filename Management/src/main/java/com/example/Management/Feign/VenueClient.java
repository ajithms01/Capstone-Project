package com.example.Management.Feign;

import com.example.Management.Client.Venue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client", url = "http://localhost:8082/venue")
public interface VenueClient {

    @GetMapping("/{venueId}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long venueId);
}
