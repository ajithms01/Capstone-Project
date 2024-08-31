package com.example.Management.Feign;

import com.example.Management.Client.Vendor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "clientservice", url = "http://localhost:8082/vendor", fallback = VendorClientFallback.class)
public interface VendorClient {
    @GetMapping("/getVendor/{vendorId}")
    public ResponseEntity<Vendor> getVendorById(@RequestParam Long vendorId);

}
