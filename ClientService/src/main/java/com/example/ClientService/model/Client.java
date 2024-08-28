package com.example.ClientService.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Long eventId;
}
