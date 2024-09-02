package com.example.ClientService.controller;

import com.example.ClientService.dtos.ClientLoginDto;
import com.example.ClientService.feign.EventClient;
import com.example.ClientService.model.Client;
import com.example.ClientService.resources.Event;
import com.example.ClientService.resources.Guest;
import com.example.ClientService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private EventClient eventClient;


    @GetMapping("/profile")
    public ResponseEntity<Client> getClientProfile(@RequestParam String username, @RequestParam String password){
        return ResponseEntity.ok().body(clientService.getClientByUsernameAndPassword(username,password).orElse(null));
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam Long clientId) {

        // Fetch client using username
        Optional<Client> client = clientService.getClientById(clientId); // Assuming you have this method
        if (client.isPresent()) {
            String hostName = client.get().getName();
            List<Event> events = eventClient.getEventByHostName(hostName).getBody();
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }


    @PostMapping("/events")
    public ResponseEntity<Client> createEvent(@RequestBody Event event, @RequestParam Long clientId) {
        Optional<Client> clientOptional = clientService.getClientById(clientId);
        if (clientOptional.isPresent()) {
            Event createdEvent = eventClient.createEvent(event).getBody();
            Client client = clientOptional.get(); // Add the new event ID to the client's list
            clientService.saveClient(client); // Update the client with the new event ID
            return ResponseEntity.status(201).body(client);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Client> updateProfile(@RequestParam Long clientId, @RequestBody Client updatedClient) {
        Client updatedProfile = clientService.updateProfile(clientId, updatedClient);
        return ResponseEntity.ok(updatedProfile);
    }


    @GetMapping("/getUser/{clientId}")
    public ResponseEntity<Optional<Client>> getClient(@RequestParam Long clientId){
        return ResponseEntity.ok().body(clientService.getClientById(clientId));
    }

    @PostMapping("/addUser")
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client client1=new Client();
        client1.setName(client.getName());

        return ResponseEntity.ok().body(clientService.saveClient(client));
    }


    @PutMapping("/{eventId}/uploadGuests")
    public ResponseEntity<String> uploadGuests(@PathVariable Long eventId, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uploaded file is empty.");
            }

            List<Guest> guests = clientService.parseGuestsFromFile(file);

            if (guests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No guests were parsed from the file.");
            }

            eventClient.addGuestToEvent(eventId, guests);
            return ResponseEntity.ok("Guests added successfully.");

        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("getUser")
    public ResponseEntity<Client> getUserInfo(@RequestParam String username){
        return ResponseEntity.ok().body(clientService.getUserInfo(username));
    }




}
