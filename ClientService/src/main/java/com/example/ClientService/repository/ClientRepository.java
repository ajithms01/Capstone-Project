package com.example.ClientService.repository;

import com.example.ClientService.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
<<<<<<< HEAD
    Optional<Client> findByUserNameAndPassword(String userName, String password);
=======
    Optional<Client> findByUserNameAndPassword(String username, String password);
>>>>>>> 25f0b976dbf84f4c117f3f6f06598f55f555d68c

    Optional<Client> findByName(String name);
}
