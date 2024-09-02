package com.example.ClientService.service;

import com.example.ClientService.dtos.VendorRegistrationDto;
import com.example.ClientService.model.Vendor;
import com.example.ClientService.repository.VendorRepository;
import com.example.ClientService.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private AppUtils appUtils;
    @Autowired
    private VendorRepository vendorRepository;


    public Vendor addVendor(VendorRegistrationDto vendorRegistrationDto) {
        Vendor newVendor=appUtils.mapToVendor(vendorRegistrationDto);
        return vendorRepository.save(newVendor);
    }

    public List<Vendor> getAvailableVendor(Date date) {
        return vendorRepository.findAvailableVendorsByDate(date);
    }

    public List<Vendor> getVendorByType(String type) {
        return vendorRepository.findVendorsByType(type);
    }

    public List<Vendor> getVendorsByChoice(String location, Date date, String type) {
        if(type == null){
            return vendorRepository.findVendorsByLocationAndDate(location, date);
        }
        return vendorRepository.findVendorsByLocationAndDateAndType(location, date, type);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    public Vendor getVendorById(Long vendorId) {
        return vendorRepository.findById(vendorId).orElse(null);
    }

    public Vendor addDate(Long vendorId, Date date) {
        Vendor vendor= vendorRepository.getById(vendorId);
        vendor.getBookedDates().add(date);
        return vendorRepository.save(vendor);
    }
}
