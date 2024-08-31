package com.example.Management.Service;

import com.example.Management.Client.FullResponse;
import com.example.Management.Client.Vendor;
import com.example.Management.Feign.VendorClient;
import com.example.Management.Model.Event;
import com.example.Management.Model.EventStatus;
import com.example.Management.Repository.EventRepository;
import com.example.Management.dto.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrderNumberGenerator ong;

    @Autowired
    private VendorClient vendorClient;

//    @Autowired
//    private EntityToDto entityToDto;

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

    public Event addGuestToEvent(Long eventId, List guest) {
        Optional<Event> eventOptional= eventRepository.findById(eventId);
        if(eventOptional.isPresent()){
            Event event=eventOptional.get();
            event.getGuestList().addAll(guest);
            return eventRepository.save(event);
        }
        else{
            return null;
        }
    }

    public FullResponse eventDetails(Long eventId) {
        Optional<Event> eventOptional= eventRepository.findById(eventId);
        if(eventOptional.isPresent()){
            Event event=eventOptional.get();
            FullResponse response = EntityToDto.eventToResponse(event);
            if(event.getVendorIds()!=null){
                List<Vendor> newVendorList = new ArrayList<>();
                for(Long vendorId: event.getVendorIds()){
                    Vendor vendor = vendorClient.getVendorById(vendorId).getBody();
                    newVendorList.add(vendor);
                }
                response.setVendorList(newVendorList);
            }
//            response.setAddress(event.getAddress());



            response.setGuestList(event.getGuestList());
            return response;
        }
        else{
            return null;
        }
    }

    public FullResponse sendOrder(Long eventId, Long vendorId) {
        Optional<Event> eventOptional= eventRepository.findById(eventId);
        if(eventOptional.isPresent()){
            Event event=eventOptional.get();
            Vendor vendor = vendorClient.getVendorById(vendorId).getBody();
            FullResponse response=new FullResponse();
            response.setName(event.getName());
            response.setHost(event.getHost());
            response.setDate(event.getDate());
            response.setOrderId(ong.generateOrderNumber(eventId));
            response.setVendorList(Collections.singletonList(vendor));
//            response.setAddress(event.getAddress());
//            response.setVenue(event.getLocation());
            response.setGuestList(event.getGuestList());
            return response;
        }
        else{
            return null;
        }
    }
}
