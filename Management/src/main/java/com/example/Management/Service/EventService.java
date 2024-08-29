package com.example.Management.Service;

import com.example.Management.Model.Event;
import com.example.Management.Model.EventStatus;
import com.example.Management.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventByHostName(String hostName) {
        Optional<Event> eventOptional= eventRepository.findByHost(hostName);
        return Collections.singletonList(eventOptional.orElse(null));
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

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEventsByType(String eventType) {
        return eventRepository.findAllByType(eventType);
    }

    public List<Event> getEventsByDate(LocalDate eventDate) {
        return eventRepository.findAllByDate(eventDate);
    }
}
