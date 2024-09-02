package com.example.AuthenticationService.feign;

import com.example.AuthenticationService.Client.Client;
import com.example.AuthenticationService.model.UserCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "clientservice", url = "http://localhost:8082/client")
public interface ClientController {
    @PostMapping("/addUser")
    public ResponseEntity<Client> addClient(@RequestBody UserCredential client);
}
