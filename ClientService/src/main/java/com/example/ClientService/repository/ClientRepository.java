package com.example.ClientService.repository;

import com.example.ClientService.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByUserNameAndPassword(String userName, String password);

    Optional<Client> findByUserName(String username);
}
