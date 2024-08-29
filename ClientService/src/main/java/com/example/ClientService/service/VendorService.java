package com.example.ClientService.service;

import com.example.ClientService.dtos.VendorRegistrationDto;
import com.example.ClientService.model.Vendor;
import com.example.ClientService.repository.VendorRepository;
import com.example.ClientService.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}
