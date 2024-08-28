package com.example.ClientService.model;

import jakarta.persistence.Entity;
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
    private Long vendorId;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhone;
    private String vendorLocation;
    private List<Date> bookedDates;
    private Long eventId;
    private String type;
    private List<String> provides;


}
