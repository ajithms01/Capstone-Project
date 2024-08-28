package com.example.ClientService.controller;

import com.example.ClientService.dtos.ClientLoginDto;
import com.example.ClientService.model.Client;
import com.example.ClientService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ClientLoginDto clientLoginDto) {
        Optional<Client> client = clientService.login(clientLoginDto);
        if (client.isPresent()) {
            // Generate token here
            String token = "dummy-token"; // Replace with actual token generation logic
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    @GetMapping("/events")
    public ResponseEntity<List<Client>> getAllEvents() {
        List<Client> events = clientService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/events")
    public ResponseEntity<Client> createEvent(@RequestBody Client client) {
        Client createdEvent = clientService.createEvent(client);
        return ResponseEntity.status(201).body(createdEvent);
    }

    @PutMapping("/profile")
    public ResponseEntity<Client> updateProfile(@RequestParam Long clientId, @RequestBody Client updatedClient) {
        Client updatedProfile = clientService.updateProfile(clientId, updatedClient);
        return ResponseEntity.ok(updatedProfile);
    }
}
