package com.example.Management.Service;

import com.example.Management.Client.FullResponse;
import com.example.Management.Client.Vendor;
import com.example.Management.Client.Venue;
import com.example.Management.Feign.VendorClient;
import com.example.Management.Feign.VenueClient;
import com.example.Management.Model.Event;
import com.example.Management.Model.EventStatus;
import com.example.Management.Model.Order;
import com.example.Management.Repository.EventRepository;
import com.example.Management.Repository.OrderRepository;
import com.example.Management.dto.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrderNumberGenerator ong;

    @Autowired
    private VendorClient vendorClient;

    @Autowired
    private VenueClient venueClient;

    @Autowired
    private OrderRepository orderRepository;


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
        Event event1 = eventRepository.save(event);
        Order order = new Order();
        order.setOrderId(ong.generateOrderNumber(event1.getId()));
//        order.setEventName(event.getName());
        order.setEventId(event.getId());
//        order.setEventHost(event.getHost());
        orderRepository.save(order);
        return event1;
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
            Float budget = (float) 0;
            if(event.getVendorIds()!=null){
//                List<Vendor> newVendorList = new ArrayList<>();
                Map<String, Float> vendorMap = new HashMap<>();
                for(Long vendorId: event.getVendorIds()){
                    Vendor vendor = vendorClient.getVendorById(vendorId).getBody();
                    vendorMap.put(vendor.getVendorName(), vendor.getRate());
//                    newVendorList.add(vendor);
                    budget+= vendor.getRate();
                }
                response.setVendorMap(vendorMap);
//                response.setVendorList(newVendorList);
            }
            response.setType(event.getType());
            Venue venue = venueClient.getVenueById(event.getVenueId()).getBody();
            response.setAddress(venue.getAddress());
            response.setVenue(venue.getVenueName());
            response.setOrderId(orderRepository.findByEventId(event.getId()).getOrderId());
            budget+=venue.getRent();
            response.setBudget(budget);
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

            response.setOrderId(orderRepository.findByEventId(event.getId()).getOrderId()+"- 000" +vendorId);
            Venue venue = venueClient.getVenueById(event.getVenueId()).getBody();
            response.setAddress(venue.getAddress());
            response.setVenue(venue.getVenueName());
            response.setRate(vendor.getRate());
            return response;
        }
        else{
            return null;
        }
    }
}
