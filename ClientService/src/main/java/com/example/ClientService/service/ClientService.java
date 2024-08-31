package com.example.ClientService.service;

import com.example.ClientService.dtos.ClientLoginDto;
import com.example.ClientService.model.Client;
import com.example.ClientService.repository.ClientRepository;
import com.example.ClientService.resources.Guest;
import com.opencsv.CSVReader;
import io.micrometer.core.instrument.MultiGauge;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            existingClient.setName(updatedClient.getName());
            existingClient.setEmail(updatedClient.getEmail());
            // Add more fields if needed
            return clientRepository.save(existingClient);
        } else {
            throw new RuntimeException("Client not found");
        }
    }

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }


    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getClientByUsername(String name) {
        return clientRepository.findByName(name);
    }


    public List<Guest> parseGuestsFromFile(MultipartFile file) throws Exception {
        // Determine file type (CSV or Excel) and parse accordingly
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".csv")) {
            return parseCsvFile(file.getInputStream());
        } else if (filename.endsWith(".xlsx")) {
            return parseExcelFile(file.getInputStream());
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }
    }

    private List<Guest> parseCsvFile(InputStream inputStream) throws Exception {
        List<Guest> guests = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                // Assuming CSV columns are: name, email
                String name = nextLine[0];
                String email = nextLine[1];
                String phone = nextLine[2];
                Guest guest = new Guest();
                guest.setGuestName(name);
                guest.setGuestEmail(email);
                guest.setGuestPhone(phone);
                guests.add(guest);
            }
        }
        return guests;
    }

    private List<Guest> parseExcelFile(InputStream inputStream) throws Exception {
        List<Guest> guests = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Assuming Excel columns are: name, email
                String name = row.getCell(0).getStringCellValue();
                String email = row.getCell(1).getStringCellValue();
                String phone = row.getCell(2).getStringCellValue();
                Guest guest = new Guest();
                guest.setGuestName(name);
                guest.setGuestEmail(email);
                guest.setGuestPhone(phone);
                guests.add(guest);
            }
        }
        return guests;
    }
}
