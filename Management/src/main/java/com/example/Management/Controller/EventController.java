package com.example.Management.Controller;

import com.example.Management.Client.FullResponse;
import com.example.Management.Model.Event;
import com.example.Management.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

    @GetMapping("/host")
    public ResponseEntity<List<Event>> getEventByHostName(@RequestParam String hostName) {
        return ResponseEntity.ok().body(eventService.getEventByHostName(hostName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(Long id) {
        return ResponseEntity.ok().body(eventService.getEventById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok().body(eventService.createEvent(event));
    }

    @GetMapping("/eventType")
    public ResponseEntity<List<Event>> getEventsByType(@RequestParam String eventType) {
        return ResponseEntity.ok().body(eventService.getEventsByType(eventType));
    }

    @GetMapping("/eventDate")
    public ResponseEntity<List<Event>> getEventsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok().body(eventService.getEventsByDate(date));
    }

    @PutMapping("addGuest")
    public ResponseEntity<Event> addGuestToEvent(@RequestParam Long eventId, @RequestParam List guest) {
        return ResponseEntity.ok().body(eventService.addGuestToEvent(eventId, guest));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<FullResponse> eventDetails(@RequestParam Long eventId){
        return ResponseEntity.ok().body(eventService.eventDetails(eventId));
    }

    @GetMapping("/{eventId}/{vendorId}")
    public ResponseEntity<FullResponse> sendOrder(@RequestParam Long eventId, @RequestParam Long vendorId){
        return ResponseEntity.ok().body(eventService.sendOrder(eventId, vendorId));
    }

}
