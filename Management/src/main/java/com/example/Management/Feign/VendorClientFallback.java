package com.example.Management.Feign;

import com.example.Management.Client.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class VendorClientFallback implements VendorClient{
    @Override
    public ResponseEntity<Vendor> getVendorById(@RequestParam Long vendorId){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
