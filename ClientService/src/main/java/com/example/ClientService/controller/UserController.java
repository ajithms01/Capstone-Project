package com.example.ClientService.controller;

import com.example.ClientService.feign.EventClient;
import com.example.ClientService.model.User;
import com.example.ClientService.resources.Event;
import com.example.ClientService.resources.Guest;
import com.example.ClientService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventClient eventClient;


    @GetMapping("/profile")
    public ResponseEntity<User> getClientProfile(@RequestParam String username, @RequestParam String password){
        return ResponseEntity.ok().body(userService.getClientByUsernameAndPassword(username,password).orElse(null));
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam Long userId) {

        // Fetch client using username
        Optional<User> user = userService.getClientById(userId); // Assuming you have this method
        if (user.isPresent()) {
            String hostName = user.get().getName();
            List<Event> events = eventClient.getEventsByClientId(userId).getBody();
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }


    @PostMapping("/events")
    public ResponseEntity<User> createEvent(@RequestBody Event event, @RequestParam Long userId) {
        Optional<User> userOptional = userService.getClientById(userId);
        if (userOptional.isPresent()) {
            eventClient.createEvent(event);
//            User user = userOptional.get(); // Add the new event ID to the client's list
//            userService.saveClient(user); // Update the client with the new event ID
            return ResponseEntity.status(201).body(userOptional.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(@RequestParam Long clientId, @RequestBody User updatedClient) {
        User updatedProfile = userService.updateProfile(clientId, updatedClient);
        return ResponseEntity.ok(updatedProfile);
    }


    @GetMapping("/getUserById")
    public ResponseEntity<Optional<User>> getClient(@RequestParam Long userId){
        return ResponseEntity.ok().body(userService.getClientById(userId));
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addClient(@RequestBody User user){
        User client1=new User();
        client1.setName(user.getName());

        return ResponseEntity.ok().body(userService.saveClient(user));
    }


//    @PutMapping("/{eventId}/uploadGuests")
//    public ResponseEntity<String> uploadGuests(@PathVariable Long eventId, @RequestParam("file") MultipartFile file) {
//        try {
//            if (file.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uploaded file is empty.");
//            }
//
//            List<Guest> guests = userService.parseGuestsFromFile(file);
//
//            if (guests.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No guests were parsed from the file.");
//            }
//
//            eventClient.addGuestToEvent(eventId, guests);
//            return ResponseEntity.ok("Guests added successfully.");
//
//        } catch (Exception e) {
//            // Log the error
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
//        }
//    }

    @GetMapping("getUser")
    public ResponseEntity<User> getUserInfo(@RequestParam String username){
        return ResponseEntity.ok().body(userService.getUserInfo(username));
    }




}
