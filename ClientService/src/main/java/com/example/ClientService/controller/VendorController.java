package com.example.ClientService.controller;

import com.example.ClientService.dtos.VendorRegistrationDto;
import com.example.ClientService.model.Vendor;
import com.example.ClientService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/information")
    public ResponseEntity<Vendor> addVendorInformation(@RequestBody VendorRegistrationDto vendorRegistrationDto){
        return ResponseEntity.ok(vendorService.addVendor(vendorRegistrationDto));
    }
}
