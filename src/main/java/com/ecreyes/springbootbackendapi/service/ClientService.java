package com.ecreyes.springbootbackendapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecreyes.springbootbackendapi.model.Client;
import com.ecreyes.springbootbackendapi.repository.ClientRepository;

@Service
public class ClientService implements IClientService {
    private final int PAGE_SIZE = 10;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private IFileService fileService;

    @Override
    public List<Client> findAll() {
        try {
            List<Client> clients = (List<Client>) this.clientRepository.findAll();
            return clients;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Page<Client> findAll(Integer page) {
        return this.clientRepository.findAll(PageRequest.of(page, PAGE_SIZE));
    }

    @Override
    public Client findById(Long id) {
        try {
            Client client = this.clientRepository.findById(id).orElse(null);
            return client;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Client save(Client client) {
        try {
            Client savedClient = this.clientRepository.save(client);
            return savedClient;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Boolean delete(Long id) {
        try {
            Client client = this.findById(id);
            this.deleteClientImage(client);
            this.clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public Client update(Client client, Long id) {
        try {
            Client clientDb = this.findById(id);
            if (clientDb == null) {
                return null;
            }
            clientDb.setEmail(client.getEmail());
            clientDb.setFirstName(client.getFirstName());
            clientDb.setLastName(client.getLastName());
            return this.save(clientDb);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Boolean uploadFile(MultipartFile file, Long id) {
        try {
            if (!file.isEmpty()) {
                Client client = this.findById(id);
                this.deleteClientImage(client);
                String fileName = this.fileService.create(file);
                client.setImage(fileName);
                this.save(client);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void deleteClientImage(Client client) {
        String clientImage = client.getImage();
        if (clientImage != null && clientImage.length() > 0) {
            this.fileService.delete(clientImage);
        }
    }

}
