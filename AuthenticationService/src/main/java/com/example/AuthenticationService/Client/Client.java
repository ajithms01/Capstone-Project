package com.example.AuthenticationService.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String userName;
    private String name;
    private String password;
    private String email;
}
