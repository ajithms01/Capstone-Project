package com.example.Management.Feign;

import com.example.Management.Client.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ClientService", url = "http://localhost:8082/user")
public interface UserClient {
    @GetMapping("/getUser/{userId}")
    public User getClient(@RequestParam Long userId);
}
