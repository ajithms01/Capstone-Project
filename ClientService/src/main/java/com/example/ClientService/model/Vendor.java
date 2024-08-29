package com.example.ClientService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vendorId;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhone;
    private String vendorLocation;
    private String type;
    private List<String> provides;
    private Long eventId;
    private List<Date> bookedDates;

}
