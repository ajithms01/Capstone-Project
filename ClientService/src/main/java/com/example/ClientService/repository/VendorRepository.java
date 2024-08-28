package com.example.ClientService.repository;

import com.example.ClientService.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
