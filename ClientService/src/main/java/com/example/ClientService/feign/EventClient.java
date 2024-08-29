package com.example.ClientService.feign;

import com.example.ClientService.resources.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "managementservice", url = "http://localhost:8081/api/event", fallback = EventClientFallback.class)
public interface EventClient {

    @GetMapping("/host")
    public ResponseEntity<List<Event>> getEventByHostName(@RequestParam String hostName);

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event);

}