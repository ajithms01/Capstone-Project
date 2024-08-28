package com.example.ClientService.resources;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private String name;
    private Date date;
    private String type;
    private String host;
    private List<Guest> guestList;
    private Boolean paymentStatus;
    private EventStatus status;
}
