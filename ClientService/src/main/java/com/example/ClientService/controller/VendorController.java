package com.example.ClientService.controller;

import com.example.ClientService.dtos.VendorRegistrationDto;
import com.example.ClientService.model.Vendor;
import com.example.ClientService.model.Venue;
import com.example.ClientService.repository.VendorRepository;
import com.example.ClientService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;



    @PostMapping("/information")
    public ResponseEntity<Vendor> addVendorInformation(@RequestBody VendorRegistrationDto vendorRegistrationDto){
        return ResponseEntity.ok(vendorService.addVendor(vendorRegistrationDto));
    }

    @PostMapping("/create")
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor){
        return ResponseEntity.ok(vendorService.createVendor(vendor));

    }

    @GetMapping("/available")
    public ResponseEntity<List<Vendor>> getAvailableVendor(@RequestParam Date date){
        return ResponseEntity.ok(vendorService.getAvailableVendor(date));
    }

    @GetMapping("/getBytype")
    public ResponseEntity<List<Vendor>> getVendorByType(@RequestParam String type){
        return ResponseEntity.ok(vendorService.getVendorByType(type));
    }

    @GetMapping("/getVendorByChoice")
    public ResponseEntity<List<Vendor>> getVendorsByChoice(@RequestParam String location, @RequestParam Date date, @RequestParam(required = false) String type){
        return ResponseEntity.ok(vendorService.getVendorsByChoice(location,date,type));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Vendor>> getAllVendors(){
        return ResponseEntity.ok(vendorService.getAllVendors());
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteVendor(@RequestParam Long id){
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getVendor/{vendorId}")
    public ResponseEntity<Vendor> getVendorById(@RequestParam Long vendorId){
        return ResponseEntity.ok(vendorService.getVendorById(vendorId));
    }

    @PutMapping("/addDate")
    public ResponseEntity<Vendor> addDate(@RequestParam Long vendorId,@RequestParam Date date){
        return ResponseEntity.ok().body(vendorService.addDate(vendorId,date));
    }
}
