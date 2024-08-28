package com.example.Management.Controller;

import com.example.Management.Model.Event;
import com.example.Management.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

    @GetMapping("/host")
    public ResponseEntity<Event> getEventByHostName(@RequestParam String hostName) {
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
}
