package com.example.Management.Service;

import com.example.Management.Model.Event;
import com.example.Management.Model.EventStatus;
import com.example.Management.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventByHostName(String hostName) {
        Optional<Event> eventOptional= eventRepository.findByHost(hostName);
        return eventOptional.orElse(null);
    }

    public Event getEventById(Long id) {
        Optional<Event> eventOptional= eventRepository.findById(id);
        return eventOptional.orElse(null);
    }

    public void deleteEvent(Long id) {
        Optional<Event> eventOptional= eventRepository.findById(id);
        eventOptional.ifPresent(event -> eventRepository.delete(event));
    }


    public Event approveEvent(Long eventId) {
        Optional<Event> eventOptional= eventRepository.findById(eventId);
        if(eventOptional.isPresent()){
            Event event=eventOptional.get();
            event.setStatus(EventStatus.CONFIRMED);
            return eventRepository.save(event);
        }
        else{
            return null;
        }
    }
}
