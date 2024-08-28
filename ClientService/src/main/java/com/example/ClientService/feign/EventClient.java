package com.example.ClientService.feign;

import com.example.ClientService.resources.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Management", url = "http://localhost:8080/event")
public interface EventClient {

    @GetMapping("/host")
    List<Event> getEventsByHostName(@RequestParam String hostName);
    @PostMapping
    Event createEvent(Event event);

}