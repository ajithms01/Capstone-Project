package com.example.ClientService.service;

import com.example.ClientService.dtos.ClientLoginDto;
import com.example.ClientService.model.Client;
import com.example.ClientService.repository.ClientRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> login(ClientLoginDto clientLoginDto) {
        return clientRepository.findByUserNameAndPassword(
                clientLoginDto.getUserName(),
                clientLoginDto.getPassword()
        );
    }

    public List<Client> getAllEvents() {
        return clientRepository.findAll();
    }

    public Client createEvent(Client client) {
        return clientRepository.save(client);
    }

    public Client updateProfile(Long clientId, Client updatedClient) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            Client existingClient = client.get();
            existingClient.setUserName(updatedClient.getUserName());
            existingClient.setEmail(updatedClient.getEmail());
            // Add more fields if needed
            return clientRepository.save(existingClient);
        } else {
            throw new RuntimeException("Client not found");
        }
    }
}
