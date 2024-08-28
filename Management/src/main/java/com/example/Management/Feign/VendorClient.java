package com.example.Management.Feign;

import com.example.Management.Client.Vendor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface VendorClient {
    @GetMapping("/approveVendor")
    public ResponseEntity<Vendor> getVendorById(Long vendorId);


}
